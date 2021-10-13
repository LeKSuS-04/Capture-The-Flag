import os
import hashlib
import random
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
from Crypto.Random.random import getrandbits
# from secret import flag
flag = b'### SECRET ### '

class LFSR:
    def __init__(self, key, taps):
        d = max(taps)
        assert len(key) == d, "Error: key of wrong size."
        self._s = key
        self._t = [d - t for t in taps]

    def _sum(self, L):
        s = 0
        for x in L:
            s ^= x
        return s

    def _clock(self):
        b = self._s[0]
        self._s = self._s[1:] + [self._sum(self._s[p] for p in self._t)]
        return b

    def bit(self):
        return self._clock()


class PRNG:
    def __init__(self, key, p, g):
        assert key.bit_length() <= 39
        key = [int(i) for i in list("{:039b}".format(key))]

        self.LFSR = [
            LFSR(key[:13], [13, 3, 1]),     # 0, 10, 12
            LFSR(key[13:26], [13, 9, 3]),   # 0,  4, 10
            LFSR(key[26:], [13, 9, 1]),     # 0,  4, 12
        ]

        self.p = 8322374842981260438697208405030249462879

    def coin(self):
        b = [lfsr.bit() for lfsr in self.LFSR]
        return b[1] if b[0] else b[2]

    def next(self):
        lfsr = self.coin()
        a, b, c = (random.randint(2, self.p) for _ in range(3))

        final_1 = pow(33, 2*a + lfsr, self.p)
        final_2 = pow( 3,        b*c, self.p)

        result = (final_1 * final_2) % self.p
        return result

def encrypt(key: int, flag: bytes):
    sha1 = hashlib.sha1()
    sha1.update(str(key).encode('ascii'))
    key = sha1.digest()[:16]
    iv = os.urandom(16)
    cipher = AES.new(key, AES.MODE_CBC, iv)
    ciphertext = cipher.encrypt(pad(flag, 16))
    data = {
        "iv": iv.hex(),
        "enc": ciphertext.hex()
    }
    return data

key: int = getrandbits(39)
prng = PRNG(key, 8322374842981260438697208405030249462879, 3)
hint = [prng.next() for _ in range(133)]
print("hint = {}".format(hint))
print("enc = {}".format(encrypt(key, flag)))