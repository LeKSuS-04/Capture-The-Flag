from itertools import cycle
with open('cipher_text.txt', 'rb') as c, open('file.txt', 'rb') as f:
    cipher = c.read()
    data = f.read().decode()

xor = lambda a, b: bytes(ai ^ bi for ai, bi in zip(a, cycle(b)))

x = cipher.decode()

print(len(cipher))
