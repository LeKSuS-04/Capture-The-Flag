from Crypto.Cipher import AES
import base64

arr = [5, 18, 61, 44, 125, 34, 247, 90, 155, 149, 103, 142, 219, 199, 5, 231]
enc = base64.b64decode('hlS4MbOmA+kQX71xXwPs7CsCWp9jQxCPa/oMk2o2bZr+jgweD4b8u80z5LVoBqC7')
key = iv = bytes(arr)

cipher = AES.new(key=key, mode=AES.MODE_CBC, iv=iv)
dec = cipher.decrypt(enc)

print(dec)