from binascii import unhexlify

class Msg:
    def __init__(self):
        self.alpha = b'4142434445464748494a4b4c4d4e4f505152535455565758595a6162636465666768696a6b6c6d6e6f70717273747' \
                b'5767778797a313233343536373839305f2d7b7d'

    def prnt(self, message):
        msg = str()
        for i in message:
            msg += u(self.alpha)[i]

        return msg

def u(n):
    return unhexlify(n).decode('utf-8')

b = Msg()

print(b.prnt([31, 37, 26, 32, 64] + [2, 26, 13, 19, 62, 28, 33, 54, 55, 45, 62, 29, 54, 55, 45, 33, 65])) 