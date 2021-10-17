from socket import socket

### SETTING UP AND MAKING USING EASIER
def send(s: socket, msg: str):
    print(msg, end='') 
    s.send(msg.encode())

def get(s: socket) -> str:
    data = s.recv(4096).decode()
    print(data, end='')
    return data

def server() -> socket:
    s = socket()
    s.connect((ip, port))
    return s

### CONFIGURATION
ip = "code.deadface.io" 
port = 50000 
s = server()

### RESULT
while True:
    data = get(s)

    if data:
        words = data.split()
        word = words[-1].lower()
        
        answ = sum([ord(l) - ord('a') for l in word])
        send(s, str(answ))