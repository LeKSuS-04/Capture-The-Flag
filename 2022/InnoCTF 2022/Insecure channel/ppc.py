import time
from tqdm import tqdm
from coollogs import log
from socket import socket
from Crypto.Cipher import AES


class Randomizer:
    def srand(self, seed):
        self.s = seed

    def rand(self):
        self.s = (0x10001 * self.s) % (2 ** 31)
        return self.s

def get_next():
    log.debug(s.recv(1024))
    data = s.recv(1024)
    log.debug(data)
    iv, enc = data[:16], data[16:]

    log.debug(s.recv(1024))
    k_new = s.recv(1024)
    seed = int(time.time()) - 1 # seed is generated right before k_new is sent
    log.debug(k_new)

    return k_new, seed, iv, enc

def get_r(rand_subseq_of_8):
    r = []
    for next_rand in rand_subseq_of_8:
        new = (next_rand >> 16) & 0xffff
        r.append((new >> 8) & 0xff)
        r.append(new & 0xff)
    return r

unpad = lambda s: s[:-s[len(s) - 1]]

addr = ('51.250.81.57', 8083)
s = socket()
s.connect(addr)

# Skip first message
log.debug(s.recv(1024))

# First iteration; get seed for new key
_, seed, _, _ = get_next()
log.info(f'Seed from time: {seed}')

# Second iteration; get encrypted message and IV
_, _, iv, enc = get_next()
log.info(f'iv: {iv}')
log.info(f'enc: {enc}')

# Generate sequence from randomizer
rand = Randomizer()
rand.srand(seed)
rand_seq = [rand.rand() for _ in range(1_000_008)]

# Bruteforce for key
for i in tqdm(range(1_000_000)):
    key = bytes(get_r(rand_seq[i:i+8]))

    cipher = AES.new(key, AES.MODE_CBC, iv)
    flag = cipher.decrypt(enc)
    
    if b'Ararat' in flag:
        log.success(flag)
        exit()
