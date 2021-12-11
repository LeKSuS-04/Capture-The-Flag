from __future__ import annotations
from collections import deque
from collections.abc import MutableSequence
import pathlib
from re import S
from pwn import *
from pwnlib import timeout


class Vector(MutableSequence):
    def __init__(self, numbers):
        self.numbers = numbers

    def idx(self, idx: tuple[int, int]):
        return idx[0] * 200 + idx[1]

    def __getitem__(self, idx: tuple[int, int]):
        return self.numbers[self.idx(idx)]

    def __len__(self):
        return len(self.numbers)

    def insert(self, val):
        self.numbers.append(val)
    
    def __delitem__(self, idx):
        pass

    def __setitem__(self, idx, val):
        self.numbers[self.idx(idx)] = val

    def check_idx(self, idx: tuple[int, int]):
        return 0 <= idx[0] and 0 <= idx[1] and self.idx(idx) < len(self.numbers)


def extract_path(memo: Vector, cell: tuple[int, int]):
    path = [cell]
    while cell != (0, 1):
        cell = memo[cell]
        path.append(cell)
    return path[::-1]


def path_to_letters(path: list[tuple[int, int]]):
    letters = ''
    for i in range(1, len(path)):
        dy, dx = path[i - 1][0] - path[i][0], path[i - 1][1] - path[i][1]
        
        if dx == +1:
            letters += 'L'
        elif dx == -1:
            letters += 'R'
        elif dy == -1:
            letters += 'D'
        else:
            letters += 'U'
    return letters


def calc_cell(path: str):
    v4, v5 = 0, 1
    for letter in path:
        if letter == 'D':
            v4 += 1
        if letter == 'L':
            v5 -= 1
        if letter == 'R':
            v5 += 1
        if letter == 'U':
            v4 -= 1
    return v4, v5


def bfs(vect: Vector):
    EMPTY = (-1, -1)
    memo = Vector([EMPTY] * len(vect))

    q = deque()
    q.append((0, 1))
    directions = [(0, 1), (1, 0), (-1, 0), (0, -1)]

    while len(q) > 0:
        u = q.popleft()

        for d in directions:
            v = u[0] + d[0], u[1] + d[1]

            if vect.check_idx(v):
                if vect[v] == 1 and memo[v] == EMPTY:
                    q.append(v)
                    memo[v] = u
    
    try:
        path = extract_path(memo, (199, 198))
        if len(path) > 415:
            raise Exception
    except Exception:
        print('NO CELL FOUND :(')
        exit(-1)

    print(f'Found short path. Size: {len(path)}')
    letters = path_to_letters(path)
    print(f'Path check: final cell is {calc_cell(letters)}')
    print(f'Full path: {letters}')

    return letters


def send_path(path: str):
    sckt = remote('84.252.132.223', 42069, timeout=3)
    
    cntr = 0
    try:
        for letter in path:
            sckt.send(letter.encode() + b'\n')
            cntr += 1
    except Exception as e:
        print(e)
        print(f'Failed after {cntr} letters')
        exit(-1)

    print(sckt.recv().decode().replace('\\n', '\n'))


def parse_exported_hex():
    with open('export_results.txt', 'r') as f:
        data = bytes.fromhex(f.read())

    numbers = []
    for i in range(0, len(data), 8):
        piece = data[i:i + 8]
        n = int.from_bytes(piece, byteorder='little')
        numbers.append(n)

    print(f'Numbers size: {len(numbers)}')
    print(f'Export converter sanity check: {numbers[:5]}')
    with open('numbers.txt', 'w') as f:
        for n in numbers:
            f.write(str(n))
            f.write('\n')


def main():
    with open('good_bad.txt') as f:
        numbers = [int(x) for x in f.read().split()]
        vect = Vector(numbers)

    path = bfs(vect)
    send_path(path)
    
if __name__ == '__main__':
    # parse_exported_hex()
    main()
