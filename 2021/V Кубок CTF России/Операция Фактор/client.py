#!/bin/python3

import socket
import elliptic
import random
import protocol
import base64

domain = ("84.201.172.73", 40999)
socket = socket.socket()
socket.connect(domain)

# get curve params
params = socket.recv(1024)
G = elliptic.EllipticPoint(int(params.hex()[:32],16), int(params.hex()[32:64],16))
curve = elliptic.EllipticCurve(int(params.hex()[64:96],16), int(params.hex()[96:128],16), int(params.hex()[128:160],16))
print("Domain Params", G, curve)

# gen&send pubkey
priv = random.randint(curve.p//2, curve.p)
pub = elliptic.EllipticUtil.multiplicatePoint(curve, G, priv)
socket.send(pub.pack())
print("Server Public Key", pub)

# get&gen crypto key
key_packed = socket.recv(1024)
server_Pub = elliptic.EllipticPoint(int(key_packed.hex()[:32],16), int(key_packed.hex()[32:64],16))

cryptokey = elliptic.EllipticUtil.multiplicatePoint(curve, server_Pub, priv)
print("Cryptography key", cryptokey)
encryptor = protocol.Secret(cryptokey)
print(hex(encryptor.crypto_key))

# get & decrypt data
data = socket.recv(1024).decode("latin1")
print("Encrypted data", base64.b64encode(data.encode("latin1")))
print(encryptor.encrypt(data))

socket.close()