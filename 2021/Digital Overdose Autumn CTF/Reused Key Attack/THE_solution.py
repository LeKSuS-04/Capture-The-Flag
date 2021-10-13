from challenge import hint, enc
from Crypto.Cipher import AES 
from Crypto.Util.Padding import unpad
import hashlib

iv = bytes.fromhex(enc['iv'])
enc = bytes.fromhex(enc['enc'])

key = ...


sha1 = hashlib.sha1()
sha1.update(str(key).encode('ascii'))
aes_key = sha1.digest()[:16]

cipher = AES.new(aes_key, AES.MODE_CBC, iv)
flag = unpad(cipher.decrypt(enc), 16)
print(flag)