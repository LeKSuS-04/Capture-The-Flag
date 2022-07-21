import pickle

class PickleRce(object):
    pass

p = pickle.dumps(PickleRce()).hex()
print(p)
pickle.loads(bytes.fromhex(p));
