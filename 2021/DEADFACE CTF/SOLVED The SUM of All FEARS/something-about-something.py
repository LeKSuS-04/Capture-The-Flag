from hashlib import md5

with open('lytton-crypt.exe', 'wb') as exefile:
    for i in range(1, 7):
        with open(f'exe/exe{i}.bin', 'rb') as binpiece:
            exefile.write(binpiece.read())

with open('lytton-crypt.bin', 'wb') as binfile:
    for i in range(1, 4):
        with open(f'bin/bin{i}.bin', 'rb') as binpiece:
            binfile.write(binpiece.read())


with open('lytton-crypt.exe', 'rb') as exefile, open('lytton-crypt.bin', 'rb') as binfile:
    exehash = md5(exefile.read()).hexdigest()
    binhash = md5(binfile.read()).hexdigest()

print('flag{' + exehash + '|' + binhash + '}')