from Crypto.Util.number import getPrime, long_to_bytes
from math import factorial
from pwn import *
import random, binascii


def v(p, k):
    ans = 0
    while k % p == 0:
        k /= p
        ans += 1
    return ans


p = getPrime(64)
n = getPrime(2048)

print(f"p = {p}")
print(f"n = {n}")
N = factorial(n)

key = long_to_bytes(v(p, N))

with open("flag.txt", "rb") as f:
    flag = f.read()

print(binascii.hexlify(xor(flag, key)))
