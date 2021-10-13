from math import sqrt

num1 = 2701493992062234
num2 = 7753623622352400
num3 = 99059327331040

def GetDivs(num):
    divs = []

    for i in range(10 ** 7, 10 ** 9):
        if(num % i == 0):
            a = min(i, num // i)
            b = max(i, num // i)
            if [a, b] not in divs:
                divs.append([a, b])

    return divs

def IsPerfectSquare(num):
    return (int(sqrt(num)) == sqrt(num))

def GetPossibleSquares(num):
    num_str = str(num).rjust(8, "0")
    squares = []

    for pre_a in range(10):
        for pre_b in range(10):
            for pre_c in range(10):
                for pre_d in range(10):
                    for post_a in range(10):
                        for post_b in range(10):
                            for post_c in range(10):
                                for post_d in range(10):
                                    res_num = int(str(pre_a) + str(pre_b) + str(pre_c) + str(pre_d) + num_str + str(post_a) + str(post_b) + str(post_c) + str(post_d))

                                    if(IsPerfectSquare(res_num)):
                                        squares.append(int(sqrt(res_num)))

    return squares

def GetNextSeed(num):
    return int(str(num ** 2).rjust(16, "0")[4:12])


a, b = 1633760, 60632729
print(GetNextSeed(GetNextSeed(1633760)) * GetNextSeed(GetNextSeed(60632729)))
print(GetNextSeed(a) * GetNextSeed(b))
print(a * b)

divs3 = GetDivs(num3)
print("Found all divs for num3:", divs3)

for div_pair in divs3:
    print()
    print("Wdorking with pair:", div_pair)

    squares1 = GetPossibleSquares(div_pair[0])
    print("Found all squares for " + str(div_pair[0]) + ":", squares1)

    if len(squares1) > 0:
        squares2 = GetPossibleSquares(div_pair[1])
        print("Found all squares for " + str(div_pair[1]) + ":", squares2)


        for sqr1 in squares1:
            for sqr2 in squares2:
                if sqr1 * sqr2 == num2:
                    print("CONGRATULATIONS!!!", sqr1, sqr2)
                    print("Next number should be", GetNextSeed(div_pair[0]) * GetNextSeed(div_pair[1]))
                    print("And next is", GetNextSeed(GetNextSeed(div_pair[0])) * GetNextSeed(GetNextSeed(div_pair[1])))
    else:
        print("Going to the next pair because no squares for first number")
