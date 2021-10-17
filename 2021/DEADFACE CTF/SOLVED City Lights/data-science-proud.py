import csv

employees = []
with open('employees.csv', 'r') as f:
    reader = csv.reader(f, delimiter=',')
    for row in reader:
        employees.append(row)

unique = set()
for e in employees:
    unique.add(e[5])

print(len(unique))