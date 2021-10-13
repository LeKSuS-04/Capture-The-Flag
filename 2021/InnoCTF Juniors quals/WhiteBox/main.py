from random import choice, randint

def gen_key():
    return ''.join(choice(['a','b','c','d','f','e','g','k','i']) for i in range(randint(10,15)))

def xor(data, key):
    return bytearray((data[i] ^ key[i % len(key)]) for i in range(0,len(data)))

open('image.png', 'w').write(
    xor(
        bytearray(open('source.png', 'rb').read()), 
        bytearray(gen_key())
    )
)