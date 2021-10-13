from base64 import b64decode

file = open("output.txt", "r").read()
flag = "".join(file.split())

while "CHTB{" not in flag:
    flag = b64decode(flag.encode('ascii')).decode('ascii')

print(flag)