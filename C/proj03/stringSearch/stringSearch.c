/*
 * File: stringSearch.c
 * Author: Brendon Hudnell
 * Purpose: This program reads a string from input, then reads additional strings. If any of the additional
 *          strings exactly matches or matches the reverse of the first string, or if it exactly matches the
			previous string, that string will be printed. 
 */

#include <stdio.h>
#include <string.h>

int main(){
	char first[32];
	char prev[32];
	char curr[32];
	int i, mat=0, rev=0, dup=0, total=0;

	if (scanf("%31s", first) == EOF){
		fprintf(stderr, "ERROR: there is nothing to search.\n");
		return 1;
	}

	strcpy(prev, first);

	while (scanf("%31s", curr) != EOF){

		//match first
		if (strcmp(curr, first) == 0){
			printf("Mat: %s\n", curr);
			mat++;
		}

		//reverse first
		if (strlen(curr) == strlen(first)){
			for (i=0; curr[i] != '\0'; i++){
				if (curr[i] != first[strlen(first)-1-i])
					break;
			}
			if (i == strlen(curr)){
				printf("Rev: %s\n", curr);
				rev++;
			}
		}

		//duplicate previous
		if (strlen(curr) == strlen(prev)){
			for (i=0; curr[i] != '\0'; i++){
				if (curr[i] != prev[i])
					break;
			}
			if (i == strlen(curr)){
				printf("Dup: %s\n", curr);
				dup++;
			}
		}
		strcpy(prev, curr);
		total++;
	}
	printf("Totals: strings=%d : m=%d r=%d d=%d\n", total, mat, rev, dup);
	return 0;
}
