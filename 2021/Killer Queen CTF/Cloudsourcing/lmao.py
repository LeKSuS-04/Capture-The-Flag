from Crypto.PublicKey import RSA
from Crypto.Util.number import bytes_to_long, long_to_bytes
from base64 import b64decode

with open('mystery.txt', 'r') as mystery, \
        open('key.pub', 'r') as key:
    c = bytes_to_long(b64decode(mystery.read()))
    pub = RSA.import_key(key.read())
    n, e = pub.n, pub.e

print(...)