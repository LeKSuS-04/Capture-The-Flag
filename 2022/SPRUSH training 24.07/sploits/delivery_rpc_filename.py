#!/usr/bin/env python3
import sys
import httpx
import re
from rpcpy.client import Client

host = sys.argv[1]

app = Client(httpx.Client(), base_url=f"http://{host}:65432/")

@app.remote_call
def get_delivery(id: int) -> str: ...


@app.remote_call
def delivery_exist(id: str) -> list: ...

for i in range(10):
    files = delivery_exist(str(i))
    for file in files:
        fileid = re.findall(r'([0123456789]+)', file)
        if fileid:
            print(get_delivery(fileid[0]), flush=True)