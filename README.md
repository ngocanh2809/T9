# SPtest

##### Question 1
Given a word, return the total number of key presses required to spell the word using the keypad. 
##### Question 2
Given a word, return the number that the word could represent.
##### Question 3
Given a number, return all possible letter combinations that the number could represent.
##### Question 4
Given a number, return all possible word combinations (from Dictionary- pls see attached file) that the number could represent.



# How To Run
1. From command line, enter 
```java
javac Solution.java
java Solution
```
2. When prompted for question number, enter an integer from 0 to 4.
    * 0 to exit the program
    * 1 - 4 for questions 1 to 4
3. After entering question number
    * Questions 1 & 2 - enter a word
    * Questions 3 & 4 - enter a number

# Implementation
1. Parts 1 & 2 are implemented by first building a lookup table at the start of the program. The use of the lookup table makes it fast to get the corresponding key/number of presses for each character in the input string.
Inputs are case-insensitive.
2. Parts 3 & 4 use HashMap to store the characters corresponding to each key in the keypad.
3. Part 4 is implemented using a trie data structure to store the dictionary. This is done at the start of the program. Trie is used as it is suitable for dictionary representation and word lookup.