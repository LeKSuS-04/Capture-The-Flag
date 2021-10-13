from challenge import N, hint, enc as c
from Crypto.Util.number import *


def crack_xor():
    print(bin(hint))
    exit()


def crack_rsa(p, q):
    phi = (p - 1) * (q - 1)
    e = 1337
    d = pow(e, -1, N)

    m = pow(c, d, N)
    msg = long_to_bytes(m).decode()

    return msg


if __name__ == "__main__":
    p, q = crack_xor()
    flag = crack_rsa(p, q)

    print(f"{flag = !r}")
