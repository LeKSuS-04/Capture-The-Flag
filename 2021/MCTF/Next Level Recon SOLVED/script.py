from hashlib import md5
from base64 import b64encode

with open('favicon.ico', 'rb') as f:
    data = f.read()

h = md5(b64encode(data)).hexdigest()
print(h)