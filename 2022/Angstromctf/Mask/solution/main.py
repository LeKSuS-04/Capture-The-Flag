from qr import *
from polynomials import *

qr = qr_str_to_arr(receive_qr(b'\x00' * 16 +b'\x00'))
fixed = fix_known_parts(qr)
unmasked = apply_mask(fixed)
msg, ecd = read_data(unmasked)
show_qr(fixed)
