import re
import base64
import string
import requests
from Crypto.Cipher import AES

ALPHA = string.ascii_letters + string.digits + '_'
ENC_IV = base64.b64decode('LKog1mVrKXz5LbvMH5/xuA==')
ENC_MSG = base64.b64decode('3DG98lUPJlBbF82C4e+eGaLAvMT4hV8aPOFFPXtQJcXgYwCYcGiZoQ+3mThW7XzL')


last_request_regexp = re.compile(r'\<li\>\<strong\>admin\:\<\/strong\> \<code\>(.*)\<\/code\>\<\/li\>')
def try_text(text: str) -> tuple[bytes, bytes]:
    # Post new text
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
    'referer': 'https://webdept.q.2022.ugractf.ru/2238d2cb7745d8b713a0a67b8d803415/dashboard/helpdesk?ok',
    'accept-language': 'en-US,en;q=0.9,ru;q=0.8',
    'cookie': 'AIOHTTP_SESSION="gAAAAABiHSx1K628RWKSqty4q8roQMqSmFC4XiHl1peOXmoVvN-ZrF9td4XMgQOwLzL2Ppx4wjIO12kkw0rguZ55DR-fYFSsKsGCkXRiRP0Vc1vZ07X11e4adX5hppngz-7RisavTLacy4O6AjAz3FFHMrve19gbtw=="',
    }
    params = (('ok', ''),)
    data = {'text': text}
    _ = requests.post('https://webdept.q.2022.ugractf.ru/2238d2cb7745d8b713a0a67b8d803415/dashboard/helpdesk', headers=headers, params=params, data=data)

    # Get result
    response = requests.get('https://webdept.q.2022.ugractf.ru/2238d2cb7745d8b713a0a67b8d803415/dashboard/helpdesk', headers=headers, params=params, data=data)
    last_request = last_request_regexp.search(response.text).group(1)
    iv_enc, msg_enc = last_request.split('|')
    iv = base64.b64decode(iv_enc)
    msg = base64.b64decode(msg_enc)
    return iv, msg

def main():
    # iv, msg = try_text('a' * 16)
    # print(f'IV: {iv}')
    # print(f'MSG: {msg}')
    # print(len(iv), len(msg))
    # print(len(ENC_MSG))
    cipher = AES.new(key=ENC_IV, mode=AES.MODE_CBC)
    print(cipher.decrypt(ENC_MSG))
    

if __name__ == '__main__':
    main()
