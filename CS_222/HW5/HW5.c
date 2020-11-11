//  CS222  Homework 5
//
//  Name: Jordan Ditzler
//
//  Searches through a text file for a user word, displays if the word was found and
//  also how many chararacters there are in the file total
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int search_and_count(FILE *fp, char *word, int *n); int character_count(char *str); // function prototypes

int main() {
	int n; // counter for total characters
	FILE *fp; // file pointer
	char fName[100], word[100]; // the file name and word to be determined by the user
	printf("Please input the text file name: "); // asks user for the .txt filename (including the .txt extension)
	scanf("%s", &fName);
	fp = fopen(fName, "r"); // opens the file
	if (fp) { // if file exists
		printf("File found.\n");
		printf("Please input the word to search: "); // asks user for search word
		scanf("%s", &word);
		if (search_and_count(fp, word, &n) == 1) { // calls search_and_count() and determines if word was found in file (1 = yes, 0 = no)
			printf("User word was find in file.\n"); // displays success and also the total characters
			printf("There are %d total characters in the file.\n", n);
		}
	} else { // if file doesn't exist
		printf("File not found.\n");
	}
	fclose(fp); // closes file for last step
	return(0);
}

// takes in file, search word, and character counter pointer
// recursively calls itself if file still has more strings
// returns 0 or 1 depending if the word was found in the .txt file (1 = yes, 0 = no)
int search_and_count(FILE *fp, char *word, int *n) {
	int f = 0; // variable to hold whether or not the word in is the .txt file
	char str[100]; // placeholder string from .txt file for comparison
	
	if (fscanf(fp, "%s", str) == EOF) { // if .txt file has no more strings
		return(f); // returns 1 or 0 (1 = yes, 0 = no)
	} else { // if .txt file still has more
		*n = *n + character_count(str); // adds to character total
		f = word_search(word, str) + search_and_count(fp, word, n); // adds all recursions and will always be either a 0 or 1
	}
}

// takes in file pointer
// counts total characters in string
// return character number
int character_count(char *str) {
	int i = 0;
	while (*str++) i++; // adds 1 when string has characters
	return(i);
}

// takes in placeholder (comparison) string and user word pointer
// if strings are same returns value
// return 1 if same, 0 if different
int word_search(char *str, char *word) {
	if (strcmp(str, word) == 0) { // if string is same
		return(1); 
	} else { // if string is different
		return(0);
	}
}