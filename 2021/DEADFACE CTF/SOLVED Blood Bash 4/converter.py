with open('hexdump.hex', 'r') as hex_in, open('flag.pdf', 'wb') as pdf_out:
    hexed = hex_in.read().replace('\n', '')
    pdf_out.write(bytes.fromhex(hexed))