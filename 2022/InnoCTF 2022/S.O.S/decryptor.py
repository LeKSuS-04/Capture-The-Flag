import pydub
import numpy as np
from Crypto.Cipher import DES3
from coollogs import log

# Import file
song = pydub.AudioSegment.from_wav('./extracted/track1.wav')
data = np.array(song.get_array_of_samples())
data = data.reshape((-1, 2))

# Show pairs
len_enc = 40
for i in range(len_enc):
    log.debug(f'{i:02}: {data[i]}')

# Parse encrypted text
enc = []
for i in range(len_enc):
    enc.append(data[i][i%2])
enc = bytes(enc)
iv, flag_enc = enc[:8], enc[8:]

# Decrypt message
key = b'&\xc8\xba\xda\xc7\x9b\xea\xba\x97\xd3@\x13/\xdf%8g\xf7# \x85\x8fp&'
cipher = DES3.new(key, DES3.MODE_CFB, iv)
flag = cipher.decrypt(flag_enc)
log.success(flag)
