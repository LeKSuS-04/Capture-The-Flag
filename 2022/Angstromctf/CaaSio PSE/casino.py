from pwn import *
context.log_level = 'critical'

conn = remote('challs.actf.co', 31337, timeout=2)
print(conn.recv().decode(), end='')

calc = '1+2'
conn.send((calc + '\n').encode())
print(calc)

print(conn.recv().decode(), end='')
print(conn.recv().decode(), end='')

'''
blacklisted:
.
[]
{}
;
`
'
"
\
_
<
>
?
:
'''
