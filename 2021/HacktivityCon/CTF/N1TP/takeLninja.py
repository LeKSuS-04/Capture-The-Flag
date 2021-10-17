text = 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'
text_enc = '2396a497762066b8835d8cf16c97ad5e058807243693c3b1dae5d0336175e7e238b0f7bfff162396a497762066b8835d8cf16c97ad5e058807243693c3b1dae5d0336175e7e238b0f7bfff162396a497762066b8835d8cf16c97ad5e058807243693c3b1dae5d0336175e7e238b0f7bfff162396a497762066b8835d8cf16c'

key = [ord(a) ^ b for a, b in zip(text, bytes.fromhex(text_enc))]

flag_enc = '249ba4916c7835eed45f89f23ac0ad0c008d5027669497e288b6816b6370bfe069b0a7effc0a'
flag = [chr(a ^ b) for a, b in zip(key, bytes.fromhex(flag_enc))]
print(''.join(flag))