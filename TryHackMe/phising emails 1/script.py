import base64

with open('b64.dat', 'rb') as fin, open('file.pdf', 'wb') as fout:
    fout.write(base64.b64decode(fin.read()))