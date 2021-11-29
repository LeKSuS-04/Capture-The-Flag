from pwn import *

conn = remote('51.250.2.75', 5555)
conn.recv()
conn.send('2\n')
print(conn.recvline())
print(conn.recvline())

while True:
    data = conn.recvuntil(b'\r\r\n')
    print(data)
    leet = data.split()[-1].decode()
    word = leet.replace('@', 'a').replace('5', 's').replace('3', 'e').replace('0', 'o').replace('!', 'i')
    print(word)
    conn.send((word + '\n').encode())
    print(conn.recvline())
    print(conn.recvline())
    print(conn.recvline())
    print(conn.recvline())