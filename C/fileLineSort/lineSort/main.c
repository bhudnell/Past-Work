/*
 * File: main.c
 * Author: Brendon Hudnell
 * Purpose: contains the primary main() function that comprises the lineSort program. Reads file names
 *          from the command line, reads the file and sorts the lines. Prints the number of lines and the
 *          sorted line list.
 */

#include "util.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv){
	int i, j, lineCount, arrSize;
	FILE *fp;
	size_t size = 0;

	//for each file in the command line
	for (i=1; i<argc; i++){
		char *buff = NULL;
		fp = fopen(argv[i], "r");
		if (fp == NULL){
			printf("The file '%s' did not exist.\n", argv[i]);
			continue;
		}
		arrSize = 1;
		char **strArray = malloc(arrSize*sizeof(char*));
		lineCount = 0;

		//read file line by line and put the lines in the strArray
		while (getline(&buff, &size, fp) != -1){
			if (lineCount == arrSize){
				strArray = extendArray(strArray, arrSize, arrSize*2);
				arrSize = arrSize*2;
				if (strArray == NULL){
					fprintf(stderr, "ERROR: Out of memory.\n");
					exit(1);
				}
			}
			if (buff[strlen(buff)-1] == '\n')
				buff[strlen(buff)-1] = '\0';

			strArray[lineCount] = buff;
			lineCount++;
			buff = NULL;
		}
		free(buff);
		fclose(fp);
		sortArray(strArray, lineCount);
		printf("The file '%s' had %d lines.\n", argv[i], lineCount);
		for (j=0; j<lineCount; j++)
			printf("%s\n", strArray[j]);
		freeArray(strArray, lineCount);
	}
	return 0;
}
