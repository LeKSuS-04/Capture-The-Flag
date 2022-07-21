with open('book.txt', 'r') as book_f, \
    open('cipher_text.txt', 'r') as cipher_f:
    book = book_f.read()
    cipher = cipher_f.read()

nums = [int(x) for x in cipher.split('/') if x]

for n in nums:
    print(book[n], end='')