
def straight(data):
    s = 0
    for c in data:
        s += ord(c)
    return s % 260


def reverse(data):
    s = 0
    for c in data[::-1]:
        s += ord(c)
    return s % 260

if __name__ == "__main__":
    data = input("Enter magic word:")
    if not (straight(data) is reverse(data)):
        with open("flag.txt") as fp:
            print(fp.read())
    else:
        print("Wrong!")
