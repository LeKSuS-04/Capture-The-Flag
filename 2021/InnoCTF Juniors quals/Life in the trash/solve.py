with open('Life in the trash/t.txt', 'r') as f:
    data = f.read().split()

def columns_to_rows(data):
    swapped = [''] * len(data[0])
    for l in data:
        for i, c in enumerate(l):
            swapped[i] += c
    return swapped

for l in data:
    if 'ftc' in l.lower():
        print(l[::-1])