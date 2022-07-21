#!/usr/bin/env python3
import pydub
import numpy as np
from secret import flag
from Crypto.Cipher import DES3

key = b'&\xc8\xba\xda\xc7\x9b\xea\xba\x97\xd3@\x13/\xdf%8g\xf7# \x85\x8fp&'

def read(f, normalized=False):
    a = pydub.AudioSegment.from_mp3(f)
    y = np.array(a.get_array_of_samples())
    y = y.reshape((-1, 2))
    return a.frame_rate, y

def write(f, sr, x, normalized=False):
    channels = 2 if (x.ndim == 2 and x.shape[1] == 2) else 1
    y = np.int16(x)
    song = pydub.AudioSegment(y.tobytes(), frame_rate=sr, sample_width=2, channels=channels)
    song.export(f, format="wav", bitrate="320k")

sr, x = read('track.mp3')
cipher = DES3.new(key, DES3.MODE_CFB)
encrypted = cipher.iv + cipher.encrypt(flag.encode())
iters = np.shape(x)[0] // len(encrypted)

for i in range(iters):
    for j in range(i * len(encrypted), i * len(encrypted)+len(encrypted)):
        x[j][j % 2] += encrypted[j % len(encrypted)]

write('track1.wav', sr, x)
