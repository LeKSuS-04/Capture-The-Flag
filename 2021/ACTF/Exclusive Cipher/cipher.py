### Clam decided to return to classic cryptography and revisit the XOR cipher! Here's some hex encoded ciphertext:
cipher = bytes.fromhex("ae27eb3a148c3cf031079921ea3315cd27eb7d02882bf724169921eb3a469920e07d0b883bf63c018869a5090e8868e331078a68ec2e468c2bf13b1d9a20ea0208882de12e398c2df60211852deb021f823dda35079b2dda25099f35ab7d218227e17d0a982bee7d098368f13503cd27f135039f68e62f1f9d3cea7c")
### The key is 5 bytes long and the flag is somewhere in the message.
from itertools import cycle

def GetAllVariants(text):
    cycled = []
    while(text not in cycled):
        cycled.append(text)
        text = text[1:] + text[0]
    return cycled

def xor(long, short):
    res = []
    for a, b in zip(long, cycle(short)):
        res.append(a ^ b)
    return res

encoding = "utf-8"
flag_beginnings = GetAllVariants("actf{")
key_length = 5

xor_with_flag = []
for next_beginning in flag_beginnings:
    xor_with_flag.append(xor(cipher, next_beginning.encode(encoding)))

possible_answers = []
for possible_xor in xor_with_flag:
    for i in range(len(possible_xor) - key_length):
        key = possible_xor[i:i + key_length]
        xored = "".join([chr(a) for a in xor(cipher, key)])
        if("actf{" in xored and "}" in xored):
            possible_answers.append(xored)

print("Here are your possible variants. Choose one that seems good!")
for answer in possible_answers:
    print(answer)

