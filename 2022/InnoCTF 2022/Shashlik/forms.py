from flask_wtf import FlaskForm
from app import app
from app.models import User, Post, Thread
from wtforms import StringField, PasswordField, BooleanField, SubmitField, FileField
from wtforms.validators import ValidationError, DataRequired, Email, EqualTo
from wtforms.widgets import TextArea
from flask_wtf.file import FileAllowed, FileRequired
from time import time
import hashlib
import urllib
import urllib.error
import urllib.request


class LoginForm(FlaskForm):
    username = StringField('Username', validators=[DataRequired()])
    password = PasswordField('Password', validators=[DataRequired()])
    submit = SubmitField('Sign In')

    def validate_password(self, username):
        user = User.query.filter_by(username=username.data).first()
        if user is None or not user.check_password(self.password.data):
            raise ValidationError('Incorrect username or password!')


class RegistrationForm(FlaskForm):
    username = StringField('Username', validators=[DataRequired()])
    password = PasswordField('Password', validators=[DataRequired()])
    password2 = PasswordField('Repeat Password', validators=[DataRequired(), EqualTo('password')])
    submit = SubmitField('Register')

    def validate_password(self, password):
        if len(password.data) > 512:
            raise ValidationError('Password too long.')

    def validate_username(self, username):
        if len(username.data) > 32:
            raise ValidationError('Username too long.')
        user = User.query.filter_by(username=username.data).first()
        if user is not None:
            raise ValidationError('Please use a different username.')


class ThemeCreateForm(FlaskForm):
    theme_name = StringField('Theme name', validators=[DataRequired()])
    body = StringField(u'Text', validators=[DataRequired()], widget=TextArea())
    is_private = BooleanField('is private')
    url = StringField('image url')
    file = FileField()
    submit = SubmitField('Create theme')

    def validate_theme_name(self, theme_name):
        if len(theme_name.data) > 256:
            raise ValidationError('Theme name too long.')

    def validate_body(self, body):
        if len(body.data) > 4096:
            raise ValidationError('Body too long.')

    def validate_url(self, url):
        url = url.data
        if url == '':
            return
        ext = url.split('.')[-1]
        if ext not in app.config['ALLOWED_EXTENSIONS']:
            raise ValidationError('Upload only images!')
        filename = app.config['UPLOAD_FOLDER'] + hashlib.sha256(url.encode()).hexdigest() + '.' + ext
        try:
            urllib.request.urlretrieve(url, filename)
        except urllib.error.HTTPError as e:
            with open(filename, 'wb') as f:
                f.write(e.read())
        except Exception as e:
            raise ValidationError(f'Cannot download image! {url}')

    def validate_file(self, file):
        if (file.data.filename != '') and (file.data.filename.split('.')[-1] not in app.config['ALLOWED_EXTENSIONS']):
            raise ValidationError('Upload only images!')


class DeleteForm(FlaskForm):
    submit1 = SubmitField('Delete thread')


class CommentForm(FlaskForm):
    body = StringField(u'Text', validators=[DataRequired()], widget=TextArea())
    submit2 = SubmitField('Comment')
    
    def validate_body(self, body):
        if len(body.data) > 4096:
            raise ValidationError('Body too long.')

