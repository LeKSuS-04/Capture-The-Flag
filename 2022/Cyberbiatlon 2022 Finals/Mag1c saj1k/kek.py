from itertools import cycle


data = bytes.fromhex('65 00 76 0d 4f 2a 5d 3c 4e 2b 0b 7f 17 72 52 18 79 1b 79 1c 6e 19 76 15 7e 52 72 1f 66 46 35 5a 34 15 68')


xor = lambda a, b: bytes(ai ^ bi for ai, bi in zip(a, cycle(b)))
key = xor(b'f', data)

print(xor(data, key))
