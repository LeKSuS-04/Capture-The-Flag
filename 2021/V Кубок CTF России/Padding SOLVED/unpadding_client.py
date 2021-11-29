from socket import socket
from random import randbytes
from time import sleep

ip = "185.66.86.53" 
port = 1688
timeout = 1

s = socket()
s.connect((ip, port))
s.settimeout(timeout)


def send(msg: str):
    s.send((msg + '\n').encode())

def get() -> str:
    return s.recv(4096).decode()

def safe_get() -> str:
    data = ''
    while True:
        try:
            data = get()
        except:
            return data

def get_flag_iv():
    send('1')
    data = get().split("\n")
    enc = data[0].split("', '")
    flag = enc[0][2:]
    iv = enc[1][:-4]
    return flag, iv

def try_padding(flag, iv):
    send('2')
    safe_get()
    send(f'{flag}, {iv}')
    print(safe_get())
    print(safe_get())


if __name__ == '__main__':
    safe_get()
    enc_flag, iv = get_flag_iv()
    print(f'Recieved {enc_flag = } and {iv = }. Starting hack...')

    c1, c2 = enc_flag[:32], enc_flag[32:]

    try_padding(enc_flag, iv)

    # for b in range(256):
    #     rand_c1 = randbytes(15) + bytes([b])


