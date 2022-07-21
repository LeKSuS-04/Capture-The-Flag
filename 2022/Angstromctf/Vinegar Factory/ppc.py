# type: ignore
import re
from pwn import *
context.log_level = 'error'

FIND_FLAG = re.compile(r'[a-z]{4}\{[a-z_]{10,50}\}[a-z]{4}')

alpha = string.ascii_lowercase

def decrypt(msg, key):
    ret = ""
    i = 0
    for c in msg:
        if c in alpha:
            ret += alpha[(alpha.index(key[i]) - alpha.index(c)) % len(alpha)]
            i = (i + 1) % len(key)
        else:
            ret += c
    return ret

def encrypt(msg, key):
    ret = ""
    i = 0
    for c in msg:
        if c in alpha:
            ret += alpha[(alpha.index(key[i]) + alpha.index(c)) % len(alpha)]
            i = (i + 1) % len(key)
        else:
            ret += c
    return ret

inner = alpha + "_"
noise = inner + "{}"

conn = remote('challs.actf.co', 31333)
conn.recvuntil(b'\n')

for i in range(100):
    chall = conn.recvuntil(b': ')
    cipher = conn.recvuntil(b'\n')[:-1].decode()
    conn.recvuntil(b'> ')

    found = FIND_FLAG.findall(cipher)
    real_ones = []
    for fleg in found:
        actf = fleg[:4]
        key = decrypt(actf, 'actf')
        msg = encrypt(fleg, key)
        if msg.endswith('fleg'):
            real_ones.append(msg)
    flag = real_ones[0][:-4]
    print(flag)
    conn.send((flag + '\n').encode())
