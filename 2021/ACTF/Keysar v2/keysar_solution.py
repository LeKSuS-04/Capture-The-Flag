from string import ascii_lowercase

hide_unknown = True

letters = ascii_lowercase
flag_enc = open("message.txt", "r").read()

known_symbols = {
    "q" : "a",
    "u" : "c",
    "f" : "t",
    "x" : "f",
    "z" : "h",
    "d" : "s",
    "t" : "o",
    "i" : "l",
    "b" : "i",
    "y" : "g",
    "c" : "r",
    "v" : "d",
    "m" : "n",
    "o" : "y",
    "w" : "e",
    "k" : "w",
    "g" : "u",
    "a" : "k",
    "r" : "m",
    "j" : "v",
    "s" : "b",
}

flag = ""
for char in flag_enc:
    if char in known_symbols:
        flag += known_symbols[char]
    elif char in ascii_lowercase and hide_unknown:
        flag += "*"
    else:
        flag += char

print(flag)