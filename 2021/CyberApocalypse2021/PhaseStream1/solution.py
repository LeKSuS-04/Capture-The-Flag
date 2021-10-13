from itertools import cycle

encoded = bytes.fromhex("2e313f2702184c5a0b1e321205550e03261b094d5c171f56011904")
key = [a ^ b for a, b in zip(b"CHTB{", encoded[:5])]
flag = "".join([chr(a ^ b) for a, b in zip(cycle(key), encoded)])

print(flag)