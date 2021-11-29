#!/usr/bin/env python3

# This script makes use of another module: common.py, which can be
# found on GitHub:
#
#  https://github.com/andreacorbellini/ecc/blob/master/logs/common.py
#
# You must place that module on the same directory of this script
# prior to running it.

import math
import random

import elliptic


def log(p, q):
    assert elliptic.EllipticUtil.isPointOnCurve(curve, p)
    assert elliptic.EllipticUtil.isPointOnCurve(curve, q)

    sqrt_n = int(math.sqrt(curve.p)) + 1

    r = None
    precomputed = {None: 0}

    for a in range(1, sqrt_n):
        r = curve.add(r, p)
        precomputed[r] = a

    r = q
    s = elliptic.EllipticUtil.multiplicatePoint(sqrt_n, p)

    for b in range(sqrt_n):
        try:
            a = precomputed[r]
        except KeyError:
            pass
        else:
            steps = sqrt_n + b
            logarithm = a + sqrt_n * b
            return logarithm, steps

        r = curve.add(r, s)

    raise AssertionError('logarithm not found')


curve = elliptic.EllipticCurve(1, 1910, 7566955095017822821)
p = elliptic.EllipticPoint(7317788832517166810, 4561443774544013382)
q = (6951774986749879631, 1117799957104038859)

print('p = (0x{:x}, 0x{:x})'.format(*p))
print('q = (0x{:x}, 0x{:x})'.format(*q))

y, steps = log(p, q)
print('log(p, q) =', y)
print('Took', steps, 'steps')
