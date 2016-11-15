/*
 * File: util.c
 * Author: Brendon Hudnell
 * Purpose: contains the utility functions that are used in the lineSort program.
 */

#include "util.h"
#include <string.h>
#include <stdlib.h>

/*
 * extendArray(oldArray, oldLen, newLen) -- resizes oldArray from size oldLen to size lewLen. Returns the new array
 */
char **extendArray(char **oldArray, int oldLen, int newLen){
	char **newArray = calloc(newLen, sizeof(char*));
	int i;

	if (newArray != NULL){
		for (i=0; i<oldLen; i++){
			newArray[i] = calloc(strlen(oldArray[i])+1, sizeof(char));
			strcpy(newArray[i], oldArray[i]);
		}
	}
	freeArray(oldArray, oldLen);
	return newArray;
}

/*
 * sortArray(array, len) -- sorts the charr** in increasing order using the bubble sort algorithm
 */
void sortArray(char **array, int len){
	int i, j;
	char *temp;
	for (i=0; i<len-1; i++){
		for (j=0; j<len-i-1; j++){
			if (strcmp(array[j], array[j+1]) > 0){
				temp = array[j];
				array[j] = array[j+1];
				array[j+1] = temp;
			}
		}
	}
}

/*
 * freeArray(array, len) -- frees each element in a char** then frees the char** itself
 */
void freeArray(char **array, int len){
	int i;
	for (i=0; i<len; i++)
		free(array[i]);
	free(array);
}
