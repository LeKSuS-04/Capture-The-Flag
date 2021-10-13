with open('GrayBox/cipher', 'rb') as f:
    enc = f.read()

def get_key(seed=40):
    size = 40
    s = b''
    for n in range(size):
        key = seed % 25
        s += bytes([65 + key])
        seed = (6 * seed - 3) % 300
    return s

def xor(data, key):
    return bytes([data[i] ^ key[i % len(key)] for i in range(0, len(data))])


key = get_key()
print(xor(enc, key))