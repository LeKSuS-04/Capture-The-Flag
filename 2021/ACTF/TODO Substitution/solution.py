### All the different outputs for inputs from 0 to 691
outputs = open('numbers.txt', 'r').read().replace('>', '').split()

print(outputs)

for i in range(len(outputs)):
    for j in range(i + 1, len(outputs)):
        if outputs[i] == outputs[j]:
            print("you've failed: outputs[" + str(i) + "] = outputs[" + str(j) + "] = " + str(outputs[i]))