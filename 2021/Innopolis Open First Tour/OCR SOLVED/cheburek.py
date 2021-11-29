import io
import base64
from PIL import Image
from pytesseract import pytesseract
from pwn import *

path_to_tesseract = r"C:\Program Files\Tesseract-OCR\tesseract.exe"
pytesseract.tesseract_cmd = path_to_tesseract

conn = remote('51.250.2.75', 5550)

while True:
    print(conn.recvline().strip())
    print(conn.recvline().strip())
    print(conn.recvline().strip())
    print(conn.recvline().strip())

    data = conn.recvline().strip()[2:-1]
    b64 = base64.b64decode(data)
    img = Image.open(io.BytesIO(b64))

    text = pytesseract.image_to_string(img).strip()
    print(f'{text}\n')
    conn.send(f'{text}\n'.encode())
    print(conn.recvline().strip())
