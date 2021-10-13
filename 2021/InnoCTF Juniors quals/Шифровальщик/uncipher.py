c = "ih uelaguyptoa sUeNstihn  Ur efGifaoer   n.o cnirfGonNs tdcdtsrehe eoeehi avt rteho gietdrp siatrtavgcy tnnet un-rgoosb an eiav eenl rntigetsriicx oleep   f oh ctnme,gfi snurtaohd pceget Oe  outo nrelrsfe (eRcsndeT SuctPioOe e oA EtiRt,dto)l naceh is   noh mswe ekdt el(shisfn)tir nd  atlorfefasrnw ooefr,aopu itto, i etoco epra hm hpi tienavsd oifnn eiot nshn ntelxe me nffts. man-I iuo,t ri e o gi fietcds mpidi.ssdcv`a'sa  nTmsdeh."

from string import ascii_letters
enc = "ih uelaguyptoa sUeNstihn  Ur efGifaoer   n.o cnirfGonNs tdcdtsrehe eoeehi avt rteho gietdrp siatrtavgcy tnnet un-rgoosb an eiav eenl rntigetsriicx oleep   f oh ctnme,gfi snurtaohd pceget Oe  outo nrelrsfe (eRcsndeT SuctPioOe e oA EtiRt,dto)l naceh is   noh mswe ekdt el(shisfn)tir nd  atlorfefasrnw ooefr,aopu itto, i etoco epra hm hpi tienavsd oifnn eiot nshn ntelxe me nffts. man-I iuo,t ri e o gi fietcds mpidi.ssdcv`a'sa  nTmsdeh."

alpha = list(range(434))

shuffled = [39, 1, 4, 8, 15, 10, 9, 14, 20, 82, 12, 24, 18, 13, 16, 25, 33, 36, 32, 38, 27, 57, 66, 23, 42, 30, 54, 76, 34, 29, 44, 31, 75, 46, 63, 40, 62, 37, 45, 55, 60, 41, 50, 
43, 51, 78, 48, 47, 85, 56, 52, 80, 58, 53, 68, 69, 70, 74, 65, 59, 84, 61, 64, 93, 102, 77, 88, 67, 90, 86, 72, 71, 105, 73, 100, 106, 123, 95, 81, 79, 87, 140, 91, 83, 104, 121, 99, 92, 94, 89, 119, 98, 110, 115, 96, 114, 161, 97, 112, 129, 143, 101, 126, 103, 111, 122, 108, 107, 134, 109, 132, 116, 118, 113, 117, 120, 162, 155, 125, 
142, 133, 147, 124, 128, 135, 130, 145, 127, 166, 146, 158, 131, 138, 152, 136, 170, 186, 137, 141, 139, 154, 184, 144, 156, 182, 150, 148, 171, 165, 149, 153, 151, 159, 172, 189, 160, 202, 157, 176, 164, 209, 168, 187, 163, 195, 178, 175, 167, 174, 169, 201, 228, 198, 173, 177, 180, 183, 194, 185, 179, 206, 181, 215, 188, 220, 190, 196, 236, 200, 262, 192, 191, 242, 193, 203, 205, 243, 197, 212, 199, 207, 218, 204, 210, 221, 217, 208, 237, 214, 222, 213, 211, 219, 235, 216, 238, 253, 224, 225, 231, 226, 252, 230, 223, 288, 258, 247, 227, 234, 229, 232, 244, 245, 233, 248, 240, 282, 254, 265, 239, 246, 241, 264, 280, 255, 250, 249, 273, 260, 290, 259, 251, 278, 267, 256, 279, 322, 257, 261, 266, 287, 301, 275, 263, 286, 270, 268, 272, 274, 269, 284, 271, 289, 295, 276, 314, 329, 277, 285, 309, 297, 281, 304, 283, 291, 302, 345, 294, 
327, 325, 292, 296, 298, 293, 315, 300, 303, 335, 305, 299, 306, 308, 323, 319, 330, 310, 342, 307, 320, 326, 312, 311, 318, 313, 316, 357, 374, 317, 321, 333, 358, 338, 324, 346, 355, 341, 328, 332, 334, 336, 352, 331, 339, 399, 343, 340, 361, 337, 366, 344, 392, 354, 356, 350, 351, 362, 348, 347, 368, 349, 363, 381, 395, 353, 364, 360, 402, 390, 365, 359, 386, 396, 371, 376, 375, 370, 369, 367, 380, 22, 372, 378, 385, 373, 391, 406, 382, 377, 5, 379, 387, 398, 384, 383, 423, 429, 388, 417, 394, 389, 
393, 414, 418, 415, 407, 400, 422, 397, 405, 404, 413, 401, 410, 403, 411, 49, 408, 425, 430, 409, 412, 416, 2, 420, 428, 427, 17, 19, 35, 419, 426, 421, 424, 6, 11, 26, 7, 0, 21, 3, 432, 431, 28, 433]

flag = ''
for c in alpha:
    flag += enc[shuffled.index(c)]

print(flag)