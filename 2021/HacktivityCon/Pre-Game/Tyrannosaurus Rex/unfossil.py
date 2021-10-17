import base64

c = '37151032694744553d12220a0f584315517477520e2b3c226b5b1e150f5549120e5540230202360f0d20220a376c0067'
z = list(bytes.fromhex(c))

first = chr(base64.b64encode(b'flag')[0])
e = [first] * len(z)

for i in reversed(range(len(z))):
    e[i] = chr(z[i] ^ ord(e[(i + 1) % len(z)]))

f = base64.b64decode(''.join(e))
print(f.decode())