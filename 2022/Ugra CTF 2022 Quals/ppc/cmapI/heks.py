from base64 import b32decode
import re
import zlib
from PIL import Image

pdf = open("ppc/cmapI/flag.pdf", "rb").read()
stream = re.compile(rb'.*?FlateDecode.*?stream(.*?)endstream', re.S)
bts = re.compile(rb'\[(.*)\]')

mapper = b'=MFW7hozR4r8c5DHq2PLpSsNBygU0ZtwmhCTKQ1vXfxYeijnEbVa3dOl9IJ_6GuA'

data = b''
for s in stream.findall(pdf):
    s = s.strip(b'\r\n')
    try:
        decompressed = zlib.decompress(s)
        for b in bts.findall(decompressed):
            b = b.replace(b'<', b'').split(b'>')[:-1]
            for a in b:
                data += bytes([mapper[int(a.decode(), 16)]])

    except:
        pass

with open('ppc/cmapI/b32.txt', 'wb') as f:
    f.write(data)

with open('ppc/cmapI/file.png', 'wb') as f:
    f.write(b32decode(data))

img = Image.open('ppc/cmapI/file.png')
