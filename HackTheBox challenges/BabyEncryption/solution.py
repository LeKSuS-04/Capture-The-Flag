with open('msg.enc', 'r') as f:
    enc = bytes.fromhex(f.readline())

m = ""
for b in enc:
    for i in range(0, 256):
        if (i * 123 + 18) % 256 == b:
            m += chr(i)

print(m)
