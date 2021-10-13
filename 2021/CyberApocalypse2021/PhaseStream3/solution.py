text = b"No right of private conversation was enumerated in the Constitution. I don't suppose it occurred to anyone at the time that it could be prevented."
text_enc, flag_enc = open("output.txt", "r").read().split()

aes_block_result = [a ^ b for a, b in zip(bytes.fromhex(text_enc), text)]
flag = "".join([chr(a ^ b) for a, b in zip(bytes.fromhex(flag_enc), aes_block_result)])

print(flag)