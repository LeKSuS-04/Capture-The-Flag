from base64 import b64decode

with open('good_luck.dat', 'r') as f:
    data = f.readlines()

for i, line in enumerate(data):
    data[i] = line.split('|')[1]

b64answ = ''.join(data)
with open('base64_1.txt', 'wb') as f:
    f.write(b64decode(b64answ))



with open('base64_1.txt', 'r') as f:
    data = f.read().split()

for i, piece in enumerate(data):
    data[i] = b64decode(piece).decode()

answ = ' '.join(data)
with open('base64_2.txt', 'w') as f:
    f.write(answ)

another_answ = bytes([int(a) for a in answ.split()])
with open('bytes.txt', 'wb') as f:
    f.write(another_answ)