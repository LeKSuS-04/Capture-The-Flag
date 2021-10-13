data = "1f8b080842534d610003706c61696e2e74787400cbcd2f4a4fcd2bce28492dca030010c38ebe0c000000"
steps = [3,11,2,13,6,4,9,5,10,8,25,11,7,3,19,4,18,40,23]
data = list(data)

prev = 0
for i in steps:
    for j in range(len(data)):
        if j % i == 0 and j - prev >= 0:
            data = data[:j-prev] + data[j:] + data[j-prev:j]
    prev = i

print((data))