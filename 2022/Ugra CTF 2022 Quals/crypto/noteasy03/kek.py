with open('crypto/noteasy03/ciphertext.txt', 'r') as f:
    data = f.read()
    print(data)

points = {
    'A': (0, 0),
    'B': (5, 10),
    'C': (5, -10),
    'D': (6, 0),
    'E': (8, 10),
    'F': (8, -10),
    'G': (9, 8),
    'H': (9, -8),
    'I': (10, 12),
    'J': (10, -12),
    'K': (11, 6),
    'L': (11, -6),
    'M': (12, 5),
    'N': (12, -5),
    'O': (14, 15),
    'P': (14, -15),
    'Q': (15, 13),
    'R': (15, -13),
    'S': (18, 10),
    'T': (18, -10),
    'U': (24, 8),
    'V': (24, -8),
    'W': (25, 0),
    'X': (27, 7),
    'Y': (27, -7),
    'Z': (28, 9),
    '[': (28, -9),
   '\\': (29, 8),
    ']': (29, -8),
    '^': (30, 2),
    '_': (30, -2),
}
for key in points.keys():
    x, y = points[key]
    points[key] = (x, y + 17)


def mult(p, n):
    a = 14
    mod = 31
    x, y = p

    for _ in range(n - 1):
        m = (3*x*x + a) * pow(2*y, -1, mod) % mod
        new_x = (m*m - 2*x) % mod
        new_y = (y + m*(new_x - x)) % mod
        x, y = new_x, new_y
    return x, y
    

def get_letter(p: tuple[int, int]) -> str:
    for letter, point in points.items():
        if p == point:
            return letter
    return '?'

# rzua]o^]tahf]ie]kiho^z]niru]ha^ogn]doak]i[]g[uff]ejsaa__ztsldsl[tll^[e
# ugra_in_task_of_losing_your_sanity_dial_o?_t?rkk_f??aa__gt??d???t??n?f
# ugra_  _na  _  _     g_    _ a    _d a _  _z    _   aa  gn  d         
# ugra_  _ a  _  _     g_    _ a    _d a _  _z    _   aa  g   d         

# (15, -13) -> (24, 8)
# (28, 9)   -> (9, 8)
# (24, 8)   -> (15, -13)
# (0, 0)    -> (0, 0)
# (29, -8)  -> (30, -2)

letters = 'abcdefghijklmnopqrstuvwxyz[\]^_'
flag = ''

for i, c in enumerate(data):
    x, y = points[c.upper()]
    rot = x * 300 + y
    idx = letters.index(c)
    new_idx = (idx + rot) % 31
    flag += letters[new_idx]

print(flag)
