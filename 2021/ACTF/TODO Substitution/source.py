from functools import reduce

""" ========== Original key-get thingy ==========
with open("flag", "r") as f:
    key = [ord(x) for x in f.read().strip()]
"""

secret = "some text"
key = [ord(x) for x in secret]

def substitute(value):
    return (reduce(lambda x, y: x*value+y, key))%691

print("Enter a number and it will be returned with our super secret synthetic substitution technique")
while True:
    try:
        value = input("> ")
        if value == 'quit':
            quit()
        value = int(value)
        enc = substitute(value)
        print(">> ", end="")
        print(enc)
    except ValueError:
        print("Invalid input. ")