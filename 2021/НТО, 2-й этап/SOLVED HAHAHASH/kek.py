from Crypto.Util.number import long_to_bytes as l2b
from itertools import product

MOD = 0x100 ** 8
CONST = 0x6e65706f6e2e7079

def mod_div(a, c):
    inv = pow(a, -1, MOD)
    return inv * c % MOD

def rol(numeric, shift):
    maxed = 0x100 ** 8
    shift1 = 8 * 8 - shift
    return (numeric >> shift) + ((numeric << shift1) % maxed)

k = 0x712e241608d44a98

for _ in range(17):
    k = mod_div(CONST, k)
    k = rol(k, 13)

k ^= 0x7370727573686564

bts = l2b(k)
print(bts)
alphabet = range(97, 123)

xors = [
    ('b', 'c'),   # 1
    ('a', 'c'),   # 2
    ('a', 'e'),  # 4
    ('a', 'i'),  # 8
    ('a', 'q'),  # 16
]

# 011
# 110
# 100
# 101
# 100

key = ''.join([
    'vbnoa' + 'aaa',
    'vbnoa' + 'aqq',

    'vbnoa' + 'aaa',
    'vbnoa' + 'iia',

    'vbnoa' + 'aaa',
    'vbnoa' + 'eaa',

    'vbnoa' + 'aaa',
    'vbnoa' + 'cac',

    'vbnoa' + 'bbb',
    'vbnoa' + 'cbb',

    'vbnoa'
])

print(key)