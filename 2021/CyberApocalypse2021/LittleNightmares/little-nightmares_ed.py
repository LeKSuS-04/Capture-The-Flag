from Crypto.Util.number import getPrime, bytes_to_long
from random import randint

FLAG = b'AB'
flag = bytes_to_long(FLAG)

def keygen():
    print("Generating keys...")
    p, q = 157, 199 #getPrime(8), getPrime(8)
    N = p*q
    g, r1, r2 = [randint(1, N) for _ in range(3)] # This could be equal to 1, 1, 1...
    r1 = r2 = 0
    g1, g2 = pow(g, r1 * (p-1), N), pow(g, r2 * (q-1), N) # Some garbage in pow of p - 1 and q - 1; And that (surprisingly) makes it work

    print(f"p: {p}; q: {q}; N: {p * q}")
    print(f"g: {g}; r1: {r1}; r2: {r2}")
    print(f"g1: {g1}; g2: {g2}\n")
    return [N, g1, g2], [p, q]

def encrypt(m, public):
    print("Encrypting...")
    N, g1, g2 = public
    assert m < N, "Message is too long"
    s1, s2 = randint(1,N), randint(1,N) # Again, this could be equal to 1, 1...
    c1 = m * pow(g1,s1,N) % N # ... So this could be equal to m * g1 % N or even m % N or just m...
    c2 = m * pow(g2,s2,N) % N # ... And this could be equal to m * g2 % N or even m % N or just m
    # !!!  But they'te not equal !!!
    # Actually, it doesn't matter, ciphertext is gonna be decrypted anyway

    print(f"s1: {s1}; s2: {s2}")
    print(f"c1: {c1}; c2: {c2}\n")
    return [c1, c2]

def decrypt(enc, private, public):
    print("Decrypting...")
    N, g1, g2 = public
    c1, c2 = enc
    p, q = private
    m1 = c1 * pow(q, -1, p) * q
    m2 = c2 * pow(p, -1, q) * p

    # So, basicaly, m = m * (pow(r1, p - 1) * pow(q, -1, p) * q + pow(r2, q - 1) * pow(p, -1, q) * p)       (mod p * q)
    # Or            m = c1 * pow(q, -1, p) * q + c2* pow(p, -1, q) * p      (mod p * q)
    # Or            m = m * (pow(g1, r1) * pow(q, -1, p) * q + pow(g2, r2) * pow(p, -1, q) * p)     (mod p * q)
    #
    # N = p * q
    # g1 = pow(r ** r1, p - 1, N)
    # g2 = pow(r ** r2, q - 1, N);
    # c1 = m * (pow(r, r1 * (p - 1), N) ** r3) % N = m * pow(r, r1 * r3 * (p - 1)) % N
    # c2 = m * (pow(r, r2 * (q - 1), N) ** r4) % N = m * pow(r, r2 * r4 * (q - 1)) % N
    
    print(f'pow(q, -1, p): {pow(q, -1, p)}')
    print(f'pow(q, -1, p) * q: {pow(q, -1, p) * q}\n')
    print(f'pow(p, -1, q): {pow(p, -1, q)}')
    print(f'pow(p, -1, q) * p: {pow(p, -1, q) * p}\n')
    print(f'm1: {m1}')
    print(f'm2: {m2}')
    print(f'm1 + m2: {m1 + m2}')
    print(f'm: {(m1 + m2) % (p * q)}\n')

    return (m1 + m2) % (p * q)

public, private = keygen()
enc = encrypt(flag, public)
assert flag == decrypt(enc, private, public)

print("Known information...")
print(f'N: {public[0]}')
print(f'g1: {public[1]}')
print(f'g2: {public[2]}')
print(f'c1: {enc[0]}')
print(f'c2: {enc[1]}')

"""print(f'Public key: {public}')
print(f'Encrypted Flag: {enc}')"""

