from Crypto.PublicKey import RSA


def int_to_bytes(x: int) -> bytes:
    return x.to_bytes((x.bit_length() + 7) // 8, 'big')

key = RSA.importKey(open('id_rsa', 'rb').read())
n, d = key.n, key.d

c = 0x2085f3d3573cd709fad84bed9fe8dde419fb7c8e96aa95ec4651a3bc07b5552f321e03404943744d931a4a51a817cf190880a5efbf94aa828c45da5b31dcdefc
m = pow(c, d, n)
msg = int_to_bytes(m).decode()

print(msg[::-1])