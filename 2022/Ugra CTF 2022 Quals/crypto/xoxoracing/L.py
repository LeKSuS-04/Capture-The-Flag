from multiprocessing.connection import answer_challenge
import re
import ast
import asyncio
import websockets

async def send_data(ws, key, value):
    out = '{' + f'"{key}":"{value}"' + '}'
    print(f'> {out}')
    await ws.send('{' + f'"{key}":"{value}"' + '}')

    data = await ws.recv()
    print(f'< {data}')

    try:
        return ast.literal_eval(data)
    except Exception:
        return data

async def main():
    with open('crypto/xoxoracing/constitution.txt', 'r') as f:
        constitution = f.read()
    
    for c in ',.;:()':
        constitution = constitution.replace(c, '')

    async with websockets.connect('wss://xoxoracing.q.2022.ugractf.ru/10fae196a54ba69b/ws') as ws:
        while True:
            await ws.recv()
            data = await send_data(ws, "key", "a" * 40)

            regexp = ''
            for c in data['ciphertext']:
                if c == '\u0416':
                    regexp += '[a-z0-9]'
                else:
                    regexp += chr(ord(c) ^ ord('a'))
            print(f'REGEXP: r"{regexp}"')

            answer = re.findall(regexp, constitution)
            print(f'FOUND PATTERNS: {answer}')
            if not answer:
                await send_data(ws, "text", '')
            else:
                await send_data(ws, "text", answer[0])

            print()


if __name__ == '__main__':
    asyncio.run(main())
