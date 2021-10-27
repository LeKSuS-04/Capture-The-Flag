from socket import socket
from hashlib import md5
from base64 import b64decode

def send(s: socket, msg: str):
	s.send(msg.encode())

def get(s: socket) -> str:
    return s.recv(4096).decode()

def server() -> socket:
    s = socket()
    s.connect((ip, port))
    s.settimeout(2)
    return s

ip = "quiz.tasks.mctf.online" 
port = 18661
s = server()

i = 0
while True:
    data = ''
    try:
        while True:
            data += get(s)
    except Exception:
        pass

    i += 1
    filename = str(i) + '.png'
    with open('nums/' + filename, 'wb') as f:
        f.write(b64decode(data))
    send(s, 'M*CTF\n')