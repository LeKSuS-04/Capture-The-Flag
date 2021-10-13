flag_start = b"CHTB{"
text_start = b"I alone cannot change the world, but I can cast a stone across the water to create many ripples."
text_enc, flag_enc = open("output.txt", "r").read().split()

if len(flag_start) > len(text_start):
    aes_block_result = [a ^ b for a, b in zip(bytes.fromhex(flag_enc), flag_start)]
    text = "".join([chr(a ^ b) for a, b in zip(bytes.fromhex(text_enc), aes_block_result)])
    print("flag: " + text)
else:
    aes_block_result = [a ^ b for a, b in zip(bytes.fromhex(text_enc), text_start)]
    flag = "".join([chr(a ^ b) for a, b in zip(bytes.fromhex(flag_enc), aes_block_result)])
    print("flag: " + flag)