import csv

customers = []
with open('customers.csv', 'r') as f:
    csvreader = csv.reader(f, delimiter=',')
    for line in csvreader:
        customers.append(line)

cntr = 0
for c in customers:
    birthdate = c[-1]
    birthyear = int(birthdate.split('/')[-1])

    if 1946 <= birthyear <= 1964:
        cntr += 1

print(cntr)