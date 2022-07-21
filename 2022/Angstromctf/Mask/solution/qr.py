from pwn import *
from copy import deepcopy

context.log_level = 'critical'


def receive_qr(bts_to_xor: bytes) -> str:
    conn = remote('challs.actf.co', 31501)
    conn.recv()
    conn.send(bts_to_xor.hex().encode() + b'\n')
    return conn.recv().decode()[:-22]


def qr_str_to_arr(qr: str) -> list[list[int]]:
    out = [[0] * 21 for _ in range(21)]
    lines = qr.split('\n')
    for i, line in enumerate(lines):
        for j, char in enumerate(line):
            if i != len(lines) - 1:
                out[i*2][j] = int(ord(char) in [9608, 9600])
                out[i*2+1][j] = int(ord(char) in [9608, 9604])
            else:
                out[i*2][j] = int(ord(char) in [9608, 9600])
    return out


def qr_arr_to_str(qr_orig: list[list[int]]) -> str:
    qr = deepcopy(qr_orig)
    qr.append([0] * 21)
    line_pairs = [(qr[i], qr[i + 1]) for i in range(0, len(qr), 2)]
    out = ''
    for i, (upper, lower) in enumerate(line_pairs):
        for j in range(21):
            if upper[j] == 1 and lower[j] == 1:
                out += chr(9608)
            if upper[j] == 1 and lower[j] == 0:
                out += chr(9600)
            if upper[j] == 0 and lower[j] == 1:
                out += chr(9604)
            if upper[j] == 0 and lower[j] == 0:
                out += chr(160)
        
        if i != len(line_pairs) - 1:
            out += '\n'
    return out

def show_qr(qr: list[list[int]]):
    qr_str = qr_arr_to_str(qr)
    print('\n')
    for line in qr_str.split('\n'):
        print('    ' + line)
    print('\n')


def apply_mask(qr_orig: list[list[int]]) -> list[list[int]]:
    qr = deepcopy(qr_orig)

    mask_mapping = [
        lambda i, j: (i + j) % 2 == 0,           # 000
        lambda i, j: i % 2 == 0,                 # 001
        lambda i, j: j % 3 == 0,                 # 010
        lambda i, j: (i + j) % 3 == 0,           # 011
        lambda i, j: (i // 2 + j // 3) % 2 == 0, # 100
        lambda i, j: i*j % 2 + i*j % 3 == 0,     # 101 
        lambda i, j: (i*j%3+ i*j) % 2 == 0,      # 110 
        lambda i, j: ((i*j)%3 + i + j) % 2 == 0, # 111
    ]
    mask_n = ((qr[8][2] << 2) + (qr[8][3] << 1) + qr[8][4]) ^ 0b101
    mask = mask_mapping[mask_n]

    for i in range(21):
        for j in range(21):
            if ((i != 6 and j != 6) # detection liness
                and not (i < 9 and j < 9)  # Top left detection block + info lines
                and not (i < 9 and j > 12) # Top right detection block + info lines
                and not (i > 12 and j < 9) # Bottom left detection block + info lines
            ):
                qr[i][j] ^= mask(i, j)
    return qr


def fix_known_parts(qr_orig: list[list[int]]):
    qr = deepcopy(qr_orig)

    # Unmask, so known info will be correct
    qr = apply_mask(qr)

    # Top right detection block
    for i in range(0, 7):
        qr[0][21 - i - 1] = qr[6][21 - i - 1] = qr[i][14] = qr[i][20] = 1
    for i in range(2, 5):
        for j in range(16, 19):
            qr[i][j] = 1

    # Horizontal detection line
    qr[6][12] = 1

    # horizontal error correction
    qr[8][13] = qr[7][8]
    for i in range(14, 21):
        qr[8][i] = qr[21 - i - 1][8]

    # Encoding; set to bytes
    qr[20][19] = 1
    qr[19][20] = qr[19][19] = qr[20][20] = 0

    # Length of message: 17
    qr[15][19] = qr[17][19] = 1
    qr[15][20] = qr[16][20] = qr[17][20] = qr[18][20] = qr[16][19] = qr[18][19] = 0

    # Mask back
    return apply_mask(qr)


def read_data(qr_orig: list[list[int]]) -> tuple[bytes, bytes]:
    qr = deepcopy(qr_orig)

    def bits_to_byte(bits: list[int]) -> bytes:
        return bytes([
            (bits[0] << 7)
            + (bits[1] << 6)
            + (bits[2] << 5)
            + (bits[3] << 4)
            + (bits[4] << 3)
            + (bits[5] << 2)
            + (bits[6] << 1)
            + bits[7]
        ])

    def read_up(i: int, j: int) -> list[int]:
        return [
            qr[i][j], qr[i][j - 1],
            qr[i - 1][j], qr[i - 1][j - 1],
            qr[i - 2][j], qr[i - 2][j - 1],
            qr[i - 3][j], qr[i - 3][j - 1],
        ]

    def read_down(i: int, j: int) -> list[int]:
        return [
            qr[i][j], qr[i][j - 1],
            qr[i + 1][j], qr[i + 1][j - 1],
            qr[i + 2][j], qr[i + 2][j - 1],
            qr[i + 3][j], qr[i + 3][j - 1],
        ]

    def read_left_top(i: int, j: int) -> list[int]:
        return [
            qr[i][j], qr[i][j - 1],
            qr[i - 1][j], qr[i - 1][j - 1],
            qr[i - 1][j - 2], qr[i - 1][j - 3],
            qr[i][j - 2], qr[i][j - 3],
        ]

    def read_left_bottom(i: int, j: int) -> list[int]:
        return [
            qr[i][j], qr[i][j - 1],
            qr[i + 1][j], qr[i + 1][j - 1],
            qr[i + 1][j - 2], qr[i + 1][j - 3],
            qr[i][j - 2], qr[i][j - 3],
        ]

    data = b''.join(
        map(
            bits_to_byte,
            [
                read_up(14, 20),
                read_left_top(10, 20),
                read_down(11, 18),
                read_down(15, 18),
                read_left_bottom(19, 18),
                read_up(18, 16),
                read_up(14, 16),
                read_left_top(10, 16),
                read_down(11, 14),
                read_down(15, 14),
                read_left_bottom(19, 14),
                read_up(18, 12),
                read_up(14, 12),
                read_up(10, 12),
                read_up(5, 12),
                read_left_top(1, 12),
                read_down(2, 10),
                read_down(9, 10),
                read_down(13, 10),
                read_down(17, 10),
                read_up(12, 8),
                read_down(9, 5),
                read_up(12, 3),
                read_down(9, 1),
            ]
        )
    )

    return data[:17], data[17:]
