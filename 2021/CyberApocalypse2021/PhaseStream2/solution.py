lines = open("output.txt", "r").readlines()
flag = ""

for line in lines:
    for i in range(256):
        flag = "".join([chr(a ^ i) for a in bytes.fromhex(line)])

        if "CHTB{" in flag:
            print(flag)
            exit()