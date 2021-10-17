with open('customers.txt', 'r') as f:
    data = f.read().split('),(')

print(data[0], data[1], data[2], len(data), sep='\n\n')