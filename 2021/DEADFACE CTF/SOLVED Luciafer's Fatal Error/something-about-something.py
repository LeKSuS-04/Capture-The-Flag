from hashlib import md5


with open('secret_decoder.bin', 'rb') as file:
    md5hash = md5(file.read()).hexdigest()

print('flag{' + md5hash + '}')