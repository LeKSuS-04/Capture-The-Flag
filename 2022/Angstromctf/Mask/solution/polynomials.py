from __future__ import annotations
from log_antilog_tables import NUM_TO_A, A_TO_NUM
from coollogs import log
from Crypto.Util.number import long_to_bytes as l2b, bytes_to_long as b2l


class GFPolynomial:
    def __init__(self, coefs: list[int]):
        assert all(c < 256 for c in coefs)
        self.a_coefs = [NUM_TO_A[c] for c in coefs]

    @property
    def num_coefs(self) -> list[int]:
        return [A_TO_NUM[c] for c in self.a_coefs]

    def _mul_poly(self, other: GFPolynomial) -> GFPolynomial:
        new_coefs = [0] * (len(self.a_coefs) + len(other.a_coefs) - 1)
        for i, a in enumerate(self.a_coefs):
            for j, b in enumerate(other.a_coefs):
                new_coefs[i + j] ^= A_TO_NUM[(a + b) % 255]

        return GFPolynomial(new_coefs)

    def _mul_int(self, other: int) -> GFPolynomial:
        new_a_coefs = []
        for a_coef in self.a_coefs:
            new_a_coefs.append((a_coef + NUM_TO_A[other]) % 255)
        return GFPolynomial([A_TO_NUM[c] for c in new_a_coefs])

    def __mul__(self, other: GFPolynomial | int) -> GFPolynomial:
        if isinstance(other, GFPolynomial):
            return self._mul_poly(other)
        else:
            return self._mul_int(other)

    def __xor__(self, other: GFPolynomial) -> GFPolynomial:
        a = self.num_coefs.copy()
        b = other.num_coefs.copy()
        if len(a) < len(b):
            a, b = b, a
        while len(a) > len(b):
            b = [0] + b

        new_coefs = []
        for ai, bi in zip(a, b):
            new_coefs.append(ai ^ bi)

        while len(new_coefs) > 0 and new_coefs[-1] == 0:
            new_coefs.pop()

        return GFPolynomial(new_coefs)

    def __str__(self):
        return ' '.join(map(str, self.num_coefs[::-1]))


def get_generator_polynomial(length: int) -> GFPolynomial:
    poly = GFPolynomial([1, 1])
    for alpha_pow in range(1, length):
        next_poly = GFPolynomial([A_TO_NUM[alpha_pow], 1])
        poly *= next_poly
    return poly


def qr_pad(data: bytes) -> bytes:
    assert len(data) == 17
    
    data_n = b2l(data)
    result = (0x411 << (17*8 + 4)) + (data_n << 4)
    return l2b(result)


def encode(data: bytes, cw_per_block: int = 7) -> bytes:
    codewords_n = len(data)
    A = GFPolynomial(list(data)[::-1])
    for i in range(codewords_n):
        B = get_generator_polynomial(cw_per_block)
        B *= A.num_coefs[-1]
        A ^= B
    return bytes(A.num_coefs)[::-1]
