from pwn import *

conn = remote('51.250.9.174', 1337)
print(conn.recvline().strip())
print(conn.recvline().strip())
print(conn.recvline().strip())
print(conn.recvline().strip())
print(conn.recvline().strip())
print(conn.recvline().strip())

while True:
    data = conn.recvline().strip()
    print(data)
    l = int(data.split()[-3])
    r = int(data.split()[-1])
    while l != r - 1:
        m = (l + r) // 2
        print(l, r, f'>{m}')
        conn.send(f'>{m}\n'.encode())
        resp = conn.recvline().strip()
        print(resp)

        if resp == b'yes':
            l = m
        else:
            r = m

    print(l, r, f'>={r}\n')
    conn.send(f'={r}\n'.encode())
    resp = conn.recvline().strip()
    print(resp)
    print(conn.recvline().strip())
