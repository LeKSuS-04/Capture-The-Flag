from enigma.machine import EnigmaMachine

ciphertext = 'mccy{dbo-yvdl-dezb-iyxo-visdtn-ai-u-nrxg-unsr-sfag-fdjqqt!}'

rings = ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII']
for rotor1 in rings:
    for rotor2 in rings:
        for rotor3 in rings:
            for ring1 in range(26):
                for ring2 in range(26):
                    for ring3 in range(26):
                        machine = EnigmaMachine.from_key_sheet(
                            rotors = f'{rotor1} {rotor2} {rotor3}',
                            reflector = 'B',
                            ring_settings = [ring1, ring2, ring3]
                        )  
                        
                        plaintext = machine.process_text(ciphertext)
                        if plaintext.startswith('FLAG'):
                            print(rotor1, rotor2, rotor3)
                            print(ring1, ring2, ring3)
                            print(plaintext)
                            print()
