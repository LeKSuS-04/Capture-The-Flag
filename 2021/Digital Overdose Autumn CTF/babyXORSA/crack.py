from challenge import N, hint, enc as c
from Crypto.Util.number import long_to_bytes


def crack_xor():
    known = 999
    bit_combs = [(0, 0), (0, 1), (1, 0), (1, 1)]
    candidates = [(0, 0)]

    for i in range(known):
        mod = 1 << (i + 1)
        prod = N & (mod - 1)
        xor = hint & (mod - 1)

        new_candidates = []

        for p, q in candidates:
            for bp, bq in bit_combs:
                p1 = p | (bp << i) 
                q1 = q | (bq << i)

                prod1 = (p1 * q1) & (mod - 1)
                xor1 = p1 ^ q1

                if prod1 == prod and xor1 == xor:
                    new_candidates.append((p1, q1))
        
        del candidates
        candidates = new_candidates

    print(candidates)
    exit(0)

def crack_rsa(p, q):
    phi = (p - 1) * (q - 1)
    e = 1337
    d = pow(e, -1, phi)

    m = pow(c, d, N)
    msg = long_to_bytes(m).decode()

    return msg


if __name__ == "__main__":
    p, q = crack_xor()
    flag = crack_rsa(p, q)

    print(f"{flag = !r}")
