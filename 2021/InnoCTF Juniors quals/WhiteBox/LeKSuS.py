def xor(data, key):
    return bytes([data[i] ^ key[i % len(key)] for i in range(0, len(data))])


with open('WhiteBox/image.png', 'rb') as f:
    enc = f.read()

magic = bytes.fromhex('89 50 4E 47 0D 0A 1A 0A 00 00 00 0D 49 48 44'.replace(' ', '')) 

key = xor(enc[:len(magic)], magic)
print(key, len(key))

with open('WhiteBox/flag.png', 'wb') as f:
    f.write(xor(enc, key))