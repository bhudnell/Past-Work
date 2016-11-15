/*
 * File: substrings.c
 * Author: Brendon Hudnell
 * Purpose: a program that reads in a single, long string, then compares multiple other strings to it,
 * 			checking to see if these strings are substrings of the original, long string.
 */

#include <stdio.h>
#include <string.h>

int main(){
	char master[121], substring[121];
	int i, j, index;

	if (fgets(master, 121, stdin) == NULL || master[0] == '\n'){
		fprintf(stderr, "ERROR: Master string is empty.\n");
		return 1;
	}

	while (fgets(substring, 121, stdin) != NULL){
		index = -1;
		if (substring[0] != '\n'){
			for (i=0; i<strlen(master)-strlen(substring)+1; i++){
				j=-999;
				if (master[i] == substring[0]){
					for(j=0; j<strlen(substring)-1; j++){
						if (master[i+j] != substring[j])
							break;
					}
					if (j == strlen(substring)-1){
						index =  i;
						break;
					}
				}
			}
			printf("%d\n", index);
		}
	}
	return 0;
}
