for n in [2, 3]:
    with open(f'ic_launcher_background{n}.rsp', 'rb') as fin, open(f'ic_launcher_background{n}_xored.rsp', 'wb') as fout:
        data = fin.read()
        data = bytes([a ^ 112 for a in data])
        fout.write(data)
