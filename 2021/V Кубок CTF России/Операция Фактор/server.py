#!/bin/python3

import socket
import elliptic
import random
import protocol
import base64

curve = elliptic.EllipticCurve(1, 1910, 7566955095017822821)
G = elliptic.EllipticPoint(7317788832517166810, 4561443774544013382)

domain = ("0.0.0.0", 40999)

socket = socket.socket()
socket.bind(domain)
socket.listen(1)

cs, cd = socket.accept()
print("Connect from " + str(cd))

# send curve params
params = G.pack() + curve.pack()
cs.send(params)

# get client pub key
key_packed = cs.recv(1024)
client_Pub = elliptic.EllipticPoint(int(key_packed.hex()[:32],16), int(key_packed.hex()[32:64],16))
print("Client Public Key", client_Pub)

# gen&send pubkey
priv = random.randint(curve.p//2, curve.p)
pub = elliptic.EllipticUtil.multiplicatePoint(curve, G, priv)
cs.send(pub.pack())
# gen cryptokey
cryptokey = elliptic.EllipticUtil.multiplicatePoint(curve, client_Pub, priv)
print("Cryptography Key", cryptokey)

encryptor = protocol.Secret(cryptokey)

s = "Hello its me - elliptic-mario! There will be your flag!"

data = encryptor.encrypt(s)
print("Encrypted data", base64.b64encode(data.encode("latin1")))
cs.send(data.encode("latin1"))

cs.close()
socket.close()