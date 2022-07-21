with open('crypto/noteasy03/ciphertext.txt', 'r') as f:
    data = f.read()
    print(data)

mapping = {
    'r': 'u',
    'u': 'r',

    'a': 'a',
    
    'i': 'o',
    'o': 'i',

    ']': '_',
    '_': ']',

    'z': 'g',
    'g': 'z',
    
    'z': 'g',
    ']': '_',
    'n': 'y',
    'h': 's',#
    '^': 'n',
    'g': 't',#
    'k': 'l',
    't': 't',
    'f': 'k',
    'e': 'f',
    # '[':'',
    # 'j':'',
    # 's':'',
    # 'l':'',
    # 'd':'',
    # '_':'',

}

flag = ''
for i in data:
    if i in mapping:
        flag += mapping[i]
    else:
        flag += '?'
print(flag)