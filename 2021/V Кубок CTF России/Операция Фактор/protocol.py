import elliptic
import base64

class Secret:
    def __init__(self, key: elliptic.EllipticPoint) -> None:
        self.key = key
        self.crypto_key = (self.key.X + self.key.Y) & 0xFFFFFFFFFFFFFFFF
    
    def encrypt(self, text: str) -> bytes:
        key = self.crypto_key.to_bytes(8, byteorder='big')
        print("Cryptography Applied Key", base64.b64encode(key))
        res = ''
        for c in range(len(text)):
            res += chr(ord(text[c]) ^ key[c % len(key)])
        return res



    
