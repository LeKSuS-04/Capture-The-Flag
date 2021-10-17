with open('Charles_Freeman_Geschickter.png', 'wb') as pngfile:
    for i in range(1, 5):
        with open(f'{i}.bin', 'rb') as binpiece:
            pngfile.write(binpiece.read())