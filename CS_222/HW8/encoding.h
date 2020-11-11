// k equal 3: shift 3 bits to the left cyclicly. 

// input text file name: itxt[] 
// output binary file name: obin[] 

// 1) read 4 characters from input text file 
// 	(if not enough characters, use EOF or whatever)
// 2) pack the 4 characters into an integer; 
// 3) cyclic shift the integer 3 bits to the left; 
// 4) write the shifted integer into obin (binary); 


void encoding(char itxt[], char obin[]); 
