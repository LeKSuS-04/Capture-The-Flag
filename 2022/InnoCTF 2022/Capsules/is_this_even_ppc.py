# from scapy.all import *

# # Gets loaded dynamically
# HTTPRequest = ...
# load_layer('http')

# traffic = rdpcap('traf.pcapng')
# http_traf = [pck for pck in traffic if pck.haslayer(HTTPRequest)]

from base64 import b64decode

smth = 'C5jb20sdW1hYy0xMjgtZXRtQG9wZW5zc2guY29tLGhtYWMtc2hhMi0yNTYtZXRtQG9wZW5zc2guY29tLGhtYWMtc2hhMi01MTItZXRtQG9wZW5zc2guY29tLGhtYWMtc2hhMS1ldG1Ab3BlbnNzaC5jb20sdW1hYy02NEBvcGVuc3NoLmNvbSx1bWFjLTEyOEBvcGVuc3NoLmNvbSxobWFjLXNoYTItMjU2LGhtYWMtc2hhMi01MTIsaG1hYy1zaGExAAAA1XVtYWMtNjQtZXRtQG9wZW5zc2guY29tLHVtYWMtMTI4LWV0bUBvcGVuc3NoLmNvbSxobWFjLXNoYTItMjU2LWV0bUBvcGVuc3NoLmNvbSxobWFjLXNoYTItNTEyLWV0bUBvcGVuc3NoLmNvbSxobWFjLXNoYTEtZXRtQG9wZW5zc2guY29tLHVtYWMtNjRAb3BlbnNzaC5jb20sdW1hYy0xMjhAb3BlbnNzaC5jb20saG1hYy1zaGEyLTI1NixobWFjLXNoYTItNTEyLGhtYWMtc2hhMQAAABpub25lLHpsaWJAb3BlbnNzaC5jb20semxpYgAAABpub25lLHpsaWJAb3BlbnNzaC5jb20semxpYgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALAYeAAAAIJToTXwqspo0azoFMmSgvhwzJj5L1YUF/7gE0TNHABZxAAAAAAAA='
b = b64decode(smth)

print(b)
