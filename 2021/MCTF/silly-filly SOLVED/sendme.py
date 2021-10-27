import requests

url = 'https://silly-filly.tasks.mctf.online/registration'
data = {
    'username': 'PePeRoNi',
    'nickname': 'lmao',
    'password': '12345',
    'passwordConfirm': '12345'
}

r = requests.post(url, params=data)
print(r.text)