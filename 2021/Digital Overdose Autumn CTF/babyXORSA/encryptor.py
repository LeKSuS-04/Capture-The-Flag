from Crypto.Util.number import *
# from secret import flag
flag = b'### SECRET ###'

p, q = getPrime(1333), getPrime(1333)
assert (p-1) % 1333 != 0 and (q-1) % 1333 != 0

mod = 1 << (3 * 333)
hint = (p % mod) ^ (q % mod)

N = p * q
e = 1333
m = bytes_to_long(flag)
enc = pow(m, e, N)

print("N = {}".format(N))
print("hint = {}".format(hint))
print("enc = {}".format(enc))