from pwn import *
from math import radians, degrees, sin, cos, asin, acos, sqrt, floor

MORSE_CODE_DICT = {
    "A": ".-",
    "B": "-...",
    "C": "-.-.",
    "D": "-..",
    "E": ".",
    "F": "..-.",
    "G": "--.",
    "H": "....",
    "I": "..",
    "J": ".---",
    "K": "-.-",
    "L": ".-..",
    "M": "--",
    "N": "-.",
    "O": "---",
    "P": ".--.",
    "Q": "--.-",
    "R": ".-.",
    "S": "...",
    "T": "-",
    "U": "..-",
    "V": "...-",
    "W": ".--",
    "X": "-..-",
    "Y": "-.--",
    "Z": "--..",
    "1": ".----",
    "2": "..---",
    "3": "...--",
    "4": "....-",
    "5": ".....",
    "6": "-....",
    "7": "--...",
    "8": "---..",
    "9": "----.",
    "0": "-----",
    ", ": "--..--",
    ".": ".-.-.-",
    "?": "..--..",
    "/": "-..-.",
    "-": "-....-",
    "(": "-.--.",
    ")": "-.--.-",
    "#": ".......",
}


def decryption(message):
    message += " "
    decipher = ""
    mycitext = ""
    for myletter in message:
        # checks for space
        if myletter != " ":
            i = 0
            mycitext += myletter
        else:
            i += 1
            if i == 2:
                decipher += " "
            else:
                decipher += list(MORSE_CODE_DICT.keys())[
                    list(MORSE_CODE_DICT.values()).index(mycitext)
                ]
                mycitext = ""
    return decipher


def great_circle(lon1, lat1, lon2, lat2):
    lon1 = float(lon1)
    lon2 = float(lon2)
    lat1 = float(lat1)
    lat2 = float(lat2)
    lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])
    return 6371 * (
        acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(lon1 - lon2))
    )

sock = remote("dot-dash.tasks.2021.ctf.cs.msu.ru", 20009)
print(sock.recv().decode())
sock.send(b"y" + b"\n")
code = sock.recv().decode().split()
code = " ".join(code[2:])

while True:
    coords = decryption(code)
    coords = coords.split("#")
    print(code)
    print(decryption(code))
    for i in range(len(coords)):
        if coords[i][0] == "S":
            coords[i] = "-" + coords[i][1:]
        elif coords[i][0] == "W":
            coords[i] = "-" + coords[i][1:]
        else:
            coords[i] = coords[i][1:]

    print(coords)
    ans = round(great_circle(
        coords[0], coords[1], coords[2], coords[3]), 5
    )
    print(ans)
    sock.send(str(ans).encode() + b"\n")
    code = sock.recv()
    print(code)
    code = code.decode().split('\n')[-2]
