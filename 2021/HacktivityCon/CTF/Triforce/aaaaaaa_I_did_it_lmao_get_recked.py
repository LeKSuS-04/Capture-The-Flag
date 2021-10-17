import socket
import binascii

ip = 'challenge.ctf.games'
port = 31462
s = socket.socket()
s.connect((ip, port))

def send(msg):
    print(msg)
    s.send((msg + "\n").encode())

skippable = [
    '= = =  T R I F O R C E = = =',
    'WHICH PIECE OF THE TRIFORCE WOULD YOU LIKE? (1,2,3)',
    '1: ENCRYPT A SACRED SAYING',
    '2: DECRYPT A SACRED SAYING',
    '3: SELECT A NEW TRIFORCE PIECE',
    '4: RETURN TO YOUR ADVENTURE',
    'PLEASE ENTER YOUR SACRED SAYING IN HEXADECIMAL: '
]

def recieve():
    data = s.recv(4096).decode()
    print(data , end='')
    return data

def skip(times=1):
    for i in range(times):
        recieve()


def get_cyphertext(plaintext: str) -> list[bytes]:
    send(binascii.hexlify(plaintext.encode()).decode())
    skip()
    hexadecimal = recieve().split()[0]
    c_str = binascii.unhexlify(hexadecimal.encode())
    c = [c_str[i:i+16] for i in range(0, len(c_str), 16)]
    return c

def get_plaintext(cyphertext: bytes) -> list[bytes]:
    send(binascii.hexlify(cyphertext).decode())
    skip()
    hexadecimal = recieve().split()[0]
    p_str = binascii.unhexlify(hexadecimal.encode())
    p = [p_str[i:i+16] for i in range(0, len(p_str), 16)]
    return p


def sovle_triforce(triforce_n):
    send('3')           # Go to 'select triforce' menu
    skip(2)

    send(triforce_n)    # Select specified triforce
    skip(2)
    send('1')           # Choose encrypt
    skip(2)

    c = get_cyphertext('0' * 29)
    
    send('2')           # Choose decrypt
    skip(2)
    p_mod = get_plaintext(c[0] + c[0])

    Dc0 = [a ^ b for a, b in zip(c[0], p_mod[1])]
    iv = [a ^ b for a, b in zip(p_mod[0], Dc0)]

    return ''.join(chr(a) for a in iv)


if __name__ == '__main__':
    skip(2)
    send('1')
    skip(2)
    flag = ''

    for triforce_n in ['1', '2', '3']:
        flag += sovle_triforce(triforce_n)

    print('\n=======================================')
    print(f'Flag is "{flag}"')