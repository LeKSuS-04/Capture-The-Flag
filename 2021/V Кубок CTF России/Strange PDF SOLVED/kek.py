with open('test.pdf_', 'rb') as f:
    data = f.read()

new_data = b''
alpha = '0123456789abcdef'
i = 0
while i < len(data):
    if data[i] == ord('#') and chr(data[i + 1]) in alpha and chr(data[i + 2]) in alpha:
        bts = chr(data[i + 1]) + chr(data[i + 2])
        i += 2
        new_data += bytes([int(bts, 16)])
    else:
        new_data += bytes([data[i]])

    i += 1

with open('pdf.pdf', 'wb') as f:
    f.write(new_data)