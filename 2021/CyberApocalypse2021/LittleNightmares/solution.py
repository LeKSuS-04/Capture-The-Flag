N, g1, g2, c1, c2 = list(map(int, open("output.txt", "r").readlines()))

def string_from_int(m: int) -> bytes:
    return m.to_bytes((m.bit_length() + 7) // 8, 'big').decode('utf-8')

flag = string_from_int((c1 + c2) % N)
print(flag)
