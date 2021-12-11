import Crypto.Util.number

flag = "***REDACTED***"

n = 3237040383625162901     # Prime, 61 bit. n % 8 = 5

enc = ''.join(format(ord(x), 'b').zfill(7) for x in flag)
singles = []

for e in range(len(enc)):
    s = int(enc[e])

    a = Crypto.Util.number.getPrime(59) % n
    a = pow(a, 2, n)

    single = (3 ** s) * a % n
    singles.append(single)

print("n:", n)
print("singles:", singles)
