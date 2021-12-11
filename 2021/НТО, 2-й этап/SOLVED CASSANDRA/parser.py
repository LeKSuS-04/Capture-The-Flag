import re

with open('arrays.txt', 'r') as f:
    data = f.read().split('\n')

arrays = []
for line in data:
    array = []
    for e in line.split():
        e = e.replace("'", '').replace('\\', '').replace(',', '').replace('u', '')
        array += [int(e, 16)]
    arrays.append(array)

with open('cases.txt', 'r') as f:
    data = f.read().split('\n')

keys = {}

for line in data:
    line = line.strip()
    if 'System.out.println(decrypt(' in line:
        things = re.split(r'System\.out\.println\(decrypt\(Main\.array|\, |\)\);', line)
        keys[int(things[1])] = int(things[2])

def decrypt(arr, key):
    return bytes([a ^ key for a in arr])

for i, array in enumerate(arrays):
    if b'flag{' in decrypt(array, keys[i]):
        print(decrypt(array, keys[i]))
