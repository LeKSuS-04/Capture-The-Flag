from socket import socket
from textwrap import fill
from itertools import product

def send(s, msg):
	enc = msg.encode()
	s.send(enc)

def get(s):
    enc = s.recv(4096)
    return enc.decode()

def getSplitString(u, p):
    s = f"logged_username={u}&password={p}"
    return "|".join(fill(s, 16).split("\n"))


### CONFIG

ip = "188.166.169.77"
port = 31439

### STEP 1: GETTING FIRST 32 BYTES

s = socket()
s.connect((ip, port))

username = "admin"
password = "g0ld3n_b0x"
first32 = ""

print(f"[LOG]: ================================ LAUNCHING FIRST STEP ================================")
while True:
    data = get(s)

    if data:
        print(data)

    if data.startswith("username"):
        send(s, username)
        print(f"[LOG]: {username} was sent as username")
    elif data.startswith(username):
        send(s, password)
        print(f"[LOG]: {password} was sent as password")
        print(f"[LOG]: Whole encoded sentnce should be {getSplitString(username, password)} now")
    elif data.startswith("Leaked ciphertext"):
        c = data.split()[2]
        first32 = c[:64]

        print(f"[LOG]: Got first 32 bytes: {first32}; length = {len(first32)}")
        s.close()
        break

print("\n")

### STEP 2: GETTING STRING TO ADD TO FIRST 32 BYTES TO FIGURE OUT LAST 16

username = "admin"

good_chars = [chr(i) for i in range(128)]
def containsOnlyGoodChars(bts):
    return all(0 <= b <= 127 for b in bts)

postfix_str = ''
postfix_cipher = b''

i = 0
done = 0
for permutation in product(good_chars, repeat=16):
    if i < done:
        i += 1
        continue
    elif i == done:
        print(f'[LOG]: Skipped first {i} permutation; continuing from {i + 1}th:')

    password = "g" + "".join(permutation)

    s = socket()
    s.connect((ip, port))

    while True:
        data = get(s)

        if data.startswith("username"):
            send(s, username)
        elif data.startswith(username):
            send(s, password)
        elif data.startswith("Leaked ciphertext"):
            c = data.split()[2]
            postfix_cipher = bytes.fromhex(c[-32:])

            s.close()
            break

    i += 1
    if containsOnlyGoodChars(postfix_cipher):
        postfix_str = password
        print(f'[LOG]: Found right postfix after {i} attempts; ending loop...')
        break
    else:
        print(f'[LOG]: Found {i}th wrong postfix ({permutation}: {postfix_cipher});')


# STEP 3: GETTING LAST 16 BYTES

from16to32_str = "admin&password=g"
from0to16_cipher = bytes.fromhex(first32[:32])
secondAESinput = [ord(a) ^ b for a, b in zip(from16to32_str, from0to16_cipher)]

AESinputToCreateIVForPostfix = "".join([chr(a ^ b) for a, b in zip(postfix_cipher, secondAESinput)])
print(f"[LOG]: Generated AES input to create IV for postfix: {AESinputToCreateIVForPostfix}; length = {len(AESinputToCreateIVForPostfix)}")

username = "admin"
password = "g" + postfix_str + AESinputToCreateIVForPostfix + "0ld3n_b0y"
last16 = ""

print("[LOG]: ================================ LAUNCHING THIRD STEP ================================")
while True:
    data = get(s)

    if data:
        print(data)

    if data.startswith("username"):
        send(s, username)
        print(f"[LOG]: {username} was sent as username")
    elif data.startswith(username):
        send(s, password)
        print(f"[LOG]: {password} was sent as password")
        print(f"[LOG]: Whole encoded sentnce should be {getSplitString(username, password)} now")
    elif data.startswith("Leaked ciphertext"):
        c = data.split()[2]
        last16 = c[-32:]

        print(f"[LOG]: Got last 16 bytes: {last16}; length = {len(last16)}")
        s.close()
        break

### STEP 4: RECIEVING FLAG

s = socket()
s.connect((ip, port))

answer = first32 + last16
print(f"[LOG]: Answer determined: {answer}; length = {len(answer)}")

print("[LOG]: ================================ LAUNCHING FOURTH STEP ================================")

username = "leksus"
password = "leksus"
flag = ""

while True:
    data = get(s)

    if data:
        print(data)

    if data.startswith("username"):
        send(s, username)
        print(f"[LOG]: {username} was sent as username")
    elif data.startswith(username):
        send(s, password)
        print(f"[LOG]: {password} was sent as password")
        print(f"[LOG]: Whole encoded sentnce should be {getSplitString(username, password)} now")
    elif data.startswith("Leaked ciphertext"):
        send(s, answer)
        print(f"[LOG]: {answer} was sent as an answer")