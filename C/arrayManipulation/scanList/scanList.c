/*
 * File: scanList.c
 * Author: Brendon Hudnell
 * Purpose: this program reads in an array of integers. It then reads in a series of otherintegers.
 *			for each integer read, it returns the number of times that integer exists in the array
 */

#include <stdio.h>
#include <stdlib.h>

int main(){
	int count, i, rc, size, num;
	int *arr;

	if ((rc = scanf("%d", &size)) == 0){
		fprintf(stderr, "ERROR: Could not read in array size.    rc=%d\n", rc);
		return 1;
	}

	arr = malloc(size*sizeof(int));
	if (arr == NULL){
		fprintf(stderr, "ERROR: Out of memory.\n");
		return 1;
	}

	for (i=0; i<size; i++){
		if ((rc=scanf("%d", arr+i)) < 1){
			fprintf(stderr, "ERROR: Array elememt %d could not be read.   rc=%d\n", i, rc);
			return 1;
		}
	}

	printf("Comparison array (%d elements):", size);
	for (i=0; i<size; i++)
		printf(" %d", *(arr+i));
	printf("\n");

	while ((rc = scanf("%d", &num)) != EOF){
		if (rc == 0){
			fprintf(stderr, "ERROR: Could not read one of the search elements from stdin.\n");
			return 1;
		}

		count=0;
		for (i=0; i<size; i++){
			if (num == *(arr+i))
				count++;
		}
		printf("Number: %d count=%d\n", num, count);
	}
	return 0;
}
