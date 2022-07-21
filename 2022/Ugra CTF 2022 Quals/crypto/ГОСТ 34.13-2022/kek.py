import re
import requests
import base64
import tqdm


with open('crypto/ГОСТ 34.13-2022/flag.enc', 'rb') as f:
    enc = f.read()

SECRET = 174807

class Stream:
    value = 59827

    @classmethod
    def bit(cls) -> int:
        b = 0
        i = 0

        while SECRET > 2 ** i:
            b ^= (SECRET >> i) & (cls.value >> i) & 1
            i += 1
        
        cls.value = ((b << i) | cls.value) >> 1

        return b

    @classmethod
    def byte(cls) -> int:
        for _ in range(8):
            cls.bit()

        return cls.value & 255


def encrypt(plaintext: bytes) -> bytes:
    return bytes(
        byte ^ Stream.byte()
        for byte in plaintext
    )
    

required = bytes(x ^ y for x, y in zip(b'ugra', enc[:4]))
for i in range(1, 0xffffff):
    Stream.value = i
    if encrypt(b'\x00' * 4) == required:
        Stream.value = i
        print(encrypt(enc))
