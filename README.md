# Group-Project-OOP-Section-C

As GitHub did not allow us to add folders, while our program relies on two such folders, we decided to add everything in one zip file.
To see our program, you can only check the file LevonYedigaryan_DianaStepanyan_OOP_GroupProject.zip
The only reason I kept all the other files was that it would allow you, if needed, to see our program's creation process.

Please extract the file LevonYedigaryan_DianaStepanyan_OOP_GroupProject.zip then go in the following direction,

OOP Group Project\Enigma Machine

To use our program, please compile and run the GUI.java file inside.



How to use our program?



To encode, please select one reflector and as many rotors as you wish from the left panel of the interface,
input the message in the upper-middle panel and choose 1 of 3 encoding machines provided in the panel below.
Depending on the type of machine you choose, you may be requested to add a configuration/number of working rotors.

To decode, please select the same reflectors and rotors which have been used for encoding from the same left panel,
input the code in the upper-middle panel and choose the type of machine which have been used to encode the initial message.
Depending on the type of machine you choose, you may be requested to add a configuration/key which must match exactly those
used during encryption so the message could be recovered.



Configuration:



Without spacing in between, input the character, and right after it, input the other character you want to be connected to.

For example

abcdef

a and b are connected, c and d are connected, and e and f are connected.

Note that every character is connected to only one character thus, you can not have character repetitions and the length
of the configuration will always be an even number.



The Number of Working Rotors.



This number indicates the number of rotors the signal goes through during encoding.
And thus can be, at most, the number of rotors you choose initially.
Please input it as a whole integer number with characters,

For example, 1, 3, or 4 are correct inputs, while one, five, or 1.3 are incorrect.



Key:



The key is always provided after encoding that uses EnigmaProMax.
It is a unique key that works together with the encoded message. The key changes every time, even if the initial message is the same.
Thus, without the key, restoring the initial message is impossible.
So all you have to do is copy the key provided in a safe place and use it whenever decoding is needed.



Hopefully, you will enjoy our program :)
