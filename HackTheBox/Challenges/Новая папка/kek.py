import requests

url = 'http://178.62.12.221:32676/api/submit'
r = requests.post(url)

print(r.text)