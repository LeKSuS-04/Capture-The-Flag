import matplotlib.pyplot as plt 
from Crypto.Util.number import bytes_to_long as btl 

x = []
y = []

with open('a2b_contents.dat', 'rb') as f:
    data = f.read()

for i in range(len(data) - 8):
    if data[i:i+4] == b'DMMV':
        x.append(btl(data[i+4:i+6]))
        y.append(btl(data[i+6:i+8]))

plt.scatter(x, y)
plt.plot(x, y)
plt.show()