from socket import socket

def send(s: socket, msg: str):
	s.send(msg.encode())

def get(s: socket) -> str:
    return s.recv(4096).decode()

def server() -> socket:
    s = socket()
    s.connect((ip, port))
    return s

### CONFIGURATION
ip = "185.66.86.53" 
port = 1537
s = server()

### RESULT
while True:
    ...