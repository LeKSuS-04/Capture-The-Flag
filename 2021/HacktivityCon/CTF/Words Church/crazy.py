import socket
from search import search_grid

ip = 'challenge.ctf.games'
port = 30325
s = socket.socket()
s.connect((ip, port))

def send(msg):
    print(msg)
    s.send((msg + '\n').encode())

def recieve():
    data = s.recv(4096).decode()
    print(data, end='')
    return data

def save_recieve():
    data = recieve()
    while not data.endswith(': > '):
        data += recieve()
    return data

def skip(times=1):
    for i in range(times):
        recieve()



def parse(task):
    if task.startswith('Onto'):
        task = '\n'.join(task.split('\n')[3:])

    lines: list[str] = task.split('\n')
    word = lines[-1][:lines[-1].find(':')]

    lines = lines[2:-3]
    grid = []
    for line in lines:
        line = line.split()
        grid.append(line[2:])
    return grid, word

def solve_grid():
    skip()

    task = save_recieve()
    grid, word = parse(task)

    ans = search_grid(grid, word)
    send(str(ans))
    for _ in range(4):
        word = recieve().split()[0][:-1]

        skip()
        ans = search_grid(grid, word)
        send(str(ans))



if __name__ == '__main__':
    skip(2)
    send('play')

    for i in range(30):
        solve_grid()
    
    skip(5)