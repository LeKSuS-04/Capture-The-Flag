import elliptic
import protocol
from itertools import product, cycle

with open('data/curve_params', 'rb') as curve_params_file, \
    open('data/pubkey', 'rb') as pubkey_file, \
    open('data/cryptokey', 'rb') as cryptokey_file, \
    open('data/data_enc', 'rb') as data_enc_file:
    curve_params = curve_params_file.read()
    pubkey = pubkey_file.read()
    cryptokey = cryptokey_file.read()
    data_enc = data_enc_file.read().decode('latin1')
    print(data_enc)

# Curve params
curve = elliptic.EllipticCurve(1, 1910, 7566955095017822821)
G = elliptic.EllipticPoint(7317788832517166810, 4561443774544013382)

# Public key
cli_pub = elliptic.EllipticPoint(int(pubkey.hex()[:32],16), int(pubkey.hex()[32:64],16))
ser_pub = elliptic.EllipticPoint(int(cryptokey.hex()[:32],16), int(cryptokey.hex()[32:64],16))

print(cli_pub.X, cli_pub.Y)
print(ser_pub.X, ser_pub.Y)
# cli_priv = random.randint(curve.p//2, curve.p)
# ser_priv = random.randint(curve.p//2, curve.p)

for i in range(89):
    flag_beg = b'CUP{'
    key_beg = bytes([a ^ b for a, b in zip(flag_beg, data_enc[i:])])

    for bts in product(range(256), repeat=4):
        key = key_beg + bytes(bts)
        
        dec_data = bytes([a ^ b for a, b in zip(data_enc, cycle(key))])

        if sum([32 <= a <= 127 for a in dec_data]) >= 90:
            with open('flags.txt', 'ab') as f:
                f.write(dec_data + b'\n')


exit()
cryptokey = ...
encryptor = protocol.Secret(cryptokey)

data = data_enc.decode('latin1')
dec_data = encryptor.encrypt(data)
print()