import ast
import asyncio
import websockets
from PIL import Image


async def move(ws, dir):
    await ws.send(dir)
    data = await ws.recv()
    info = f'{dir}: {data}'
    print(info)

    try:
        return ast.literal_eval(data)
    except Exception as e:
        print(f'Unable to parse responce: {e}')


async def main():
    with open('ppc/Разборчивая змейка/matrix.txt', 'r') as f:
        data = f.read()
        if data:
            matrix = [list(map(int, line.split())) for line in data.split('\n')]
        else:
            matrix = [[0] * 42 for _ in range(42)]
            matrix[7][7] = 1

    while True:
        for _ in range(10):
            try:
                async with websockets.connect('wss://snekpeek.q.2022.ugractf.ru/817473b1bb43e07e/ws') as ws:
                    # Go from starting position to right corner
                    await ws.recv()
                    data = await move(ws, 'UL' * 21)
                    x, y = data.get('target')
                    matrix[x][y] = 1
            except Exception:
                pass

        with open('ppc/Разборчивая змейка/matrix.txt', 'w') as f:
            for line in matrix:
                f.write(' '.join(map(str, line)) + '\n')

        img = Image.new('RGB', (42*10, 42*10), (255, 255, 255))
        for i, line in enumerate(matrix):
            for j, x in enumerate(line):
                if x == 1:
                    for x in range(j*10, j*10+10):
                        for y in range(i*10, i*10+10):
                            img.putpixel((x, y), (0, 0, 0))
        img.save('ppc/Разборчивая змейка/qr.bmp')

if __name__ == '__main__':
    asyncio.run(main())
