>>>>>>>>>>>>>>-->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< // Set mem[14] = 254 and mem[50] = 255; end on mem[0]

[password]<<<<<<<<<<<<<<    // write password to mem[0-13]; move to mem[0]

[->>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<] // Move first char of password to mem[15] and mem[30]; end on mem[0]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> // Go to mem[30]
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Move from mem[30] to mem[0]; end on mem[30]
// Now mem[0-13] are passwd, mem[15] = mem[0], other mem[i] = 0


<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< // Go to mem[0]
>>                             // Go to mem[2]
[->>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<] // Move third char of password to mem[17] and mem[32]; end on mem[2]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> // Go to mem[32]
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Move from mem[32] to mem[2]; end on mem[32]
// Now mem[0-13] are passwd, mem[15] = mem[0], mem[17] = mem[2], other mem[i] = 0

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< // Go to mem[2]
>>                             // Go to mem[4]
[->>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<] // Move fifth char of password to mem[19] and mem[34]; end on mem[4]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> // Go to mem[34]
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Move from mem[34] to mem[4]; end on mem[34]
// Now mem[0-13] are passwd, mem[15] = mem[0], mem[17] = mem[2], mem[19] = mem[4] other mem[i] = 0

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>
[->>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>]    // Copy mem[6] to mem[21]; end on mem[36]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>
[->>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>]    // Copy mem[8] to mem[23]; end on mem[38]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>
[->>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>]    // Copy mem[10] to mem[25]; end on mem[40]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>
[->>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>]    // Copy mem[12] to mem[27]; end on mem[42]

// Now mem[0-13] are passwd; for i in [0:6] memo[2*i] = memo[2*i + 15] (mem[0] = mem[15], mem[2] = mem[17], ...)
// Carret on mem[42]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< // Go to mem[12]
>>                             // Go to mem[14]
<<<<<<<<<<<<<<                 // Go to mem[0]
>                              // Go to mem[1]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<] // Move mem[1] to mem[30] and mem[59]; end on mem[1]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  // Go to mem[59]
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Move mem[59] to mem[1]; end on mem[59]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< // Go to mem[1]
>>                                                         // Go to mem[3]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Copy mem[3] to mem[32]; end on mem[61]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Copy mem[5] to mem[34]; end on mem[63]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Copy mem[7] to mem[36]; end on mem[65]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Copy mem[9] to mem[38]; end on mem[67]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>] // Copy mem[11] to mem[40]; end on mem[69]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
>>
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<+>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>]  // Copy mem[13] to mem[42]; end on mem[71]

// Now mem[0-13] are passwd; 
// mem[0] = mem[15]
// mem[1] = mem[30]
// mem[2] = mem[17]
// mem[3] = mem[32]
// mem[4] = mem[19]
// mem[5] = mem[34]
// mem[6] = mem[21]
// mem[7] = mem[36]
// mem[8] = mem[23]
// mem[9] = mem[38]
// mem[10] = mem[25]
// mem[11] = mem[40]
// mem[12] = mem[27]
// mem[13] = mem[42]
// Carret on mem[71]

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< // Go to mem[13]
>                                                          // Go to mem[14]
<<<<<<<<<<<<<<                                             // Go to mem[0]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<] // Move mem[0] to mem[43], end on mem[0]
>                                                                                          // Go to mem[1]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]   // Add mem[1] to mem[43], end on mem[1]
>                                                                                          // Go to mem[2]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]     // Add mem[2] to mem[43], end on mem[2]
>                                                                                          // Go to mem[3]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]       // Add mem[3] to mem[43], end on mem[3]
>                                                                                          // Go to mem[4]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]         // Add mem[4] to mem[43], end on mem[4]
>                                                                                          // Go to mem[5]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]           // Add mem[5] to mem[43], end on mem[5]
>                                                                                          // Go to mem[6]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]             // Add mem[6] to mem[43], end on mem[6]
>                                                                                          // Go to mem[7]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]        
>                                                                                          // Go to mem[8]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                                                          // Go to mem[9]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                                                          // Go to mem[10]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                                                          // Go to mem[11]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                                                          // Go to mem[12]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                                                          // Go to mem[13]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<] // Sum up mem[0-13] in mem[43], end on mem[13]

// Now mem[43] = sum(passwd) mod 256;
// mem[15] = paswd[0] 
// mem[30] = paswd[1] 
// mem[17] = paswd[2] 
// mem[32] = paswd[3] 
// mem[19] = paswd[4] 
// mem[34] = paswd[5] 
// mem[21] = paswd[6] 
// mem[36] = paswd[7] 
// mem[23] = paswd[8] 
// mem[38] = paswd[9] 
// mem[25] = paswd[10] 
// mem[40] = paswd[11] 
// mem[27] = paswd[12] 
// mem[42] = paswd[13] 
// Carret on mem[13]

>> // Go to mem[15]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<<]  // Move mem[15] to mem[44], end on mem[15]
>                                                               // Go to mem[16]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[17]
[->>>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[18]
[->>>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[19]
[->>>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[20]
[->>>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[21]
[->>>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[22]
[->>>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[23]
[->>>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[24]
[->>>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[25]
[->>>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[26]
[->>>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<<]                        
>                                                               // Go to mem[27]
[->>>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<<]
>                                                               // Go to mem[28]
[->>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<]                            // mem[44] = sum(mem[16-28]) = sum(passwd[EVEN])
>                                                               // Go to mem[29]
[->>>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<<]                            // Move mem[29] to mem[45]
>                                                               // Go to mem[30]
[->>>>>>>>>>>>>>>+<<<<<<<<<<<<<<<]
>                                                               // Go to mem[31]
[->>>>>>>>>>>>>>+<<<<<<<<<<<<<<]
>                                                               // Go to mem[32]
[->>>>>>>>>>>>>+<<<<<<<<<<<<<]
>                                                               // Go to mem[33]
[->>>>>>>>>>>>+<<<<<<<<<<<<]
>                                                               // Go to mem[34]
[->>>>>>>>>>>+<<<<<<<<<<<]
>                                                               // Go to mem[35]
[->>>>>>>>>>+<<<<<<<<<<]
>                                                               // Go to mem[36]
[->>>>>>>>>+<<<<<<<<<]
>                                                               // Go to mem[37]
[->>>>>>>>+<<<<<<<<]
>                                                               // Go to mem[37]
[->>>>>>>+<<<<<<<]
>                                                               // Go to mem[39]
[->>>>>>+<<<<<<]
>                                                               // Go to mem[40]
[->>>>>+<<<<<]
>                                                               // Go to mem[41]
[->>>>+<<<<]
>                                                               // Go to mem[42]
[->>>+<<<]
>                                                               // Carret on mem[43] 
// mem[43] = sum(passwd)
// mem[44] = sum(passwd[EVEN])
// mem[45] = sum(passwd[ODD])
// Carret on mem[43]

------------------------------------------------------------------------------------------------------------
// mem[43] -= 108

> // Go to mem[44]
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// mem[44] -= 191

> // Go to mem[45]
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// mem[45] -= 173


<<  // Go to mem[43]
>>> // Go to mem[46]
+   // mem[46] = 1
<<< // Go to mem[43]

[[-]>>>-<<<] // mem[46] = (mem[43] == 0 ? 1 : 0)
>>>          // Go to mem[46]
[>+<-]       // mem[47] += mem[46], mem[46] = 0

<<< // Go to mem[43]
>>> // Go to mem[46]
+   // mem[46] = 1
<<  // Go to mem[44]
[[-]>> - <<] // mem[46] = (mem[44] == 0 ? 1 : 0)

>>  // Go to mem[46]
[>+< -] // mem[47] += mem[46], mem[46] = 0

<<  // Go to mem[44]
>>  // Go to mem[46]
+   // mem[46] = 1
<   // Go to mem[45]
[[-]> - <] // mem[46] = (mem[45] == 0 ? 1 : 0)

>   // Go to mem[46]
[>+< -] // mem[47] += mem[46], mem[46] = 0

<   // Go to mem[45]
>>  // Go to mem[47]
--- // mem[47] -= 3
>   // Go to mem[48]
+   // mem[48] = 1
<   // Go to mem[47]
[[-]> - <] // mem[48] = (mem[47] == 0 ? 1 : 0)
>   // Go to mem[48]
[>+++++++++++++++++++++++++++++++++++++++++++< -] mem[49] = 43 * mem[48]
<   // Go to mem[47]
// Finish...