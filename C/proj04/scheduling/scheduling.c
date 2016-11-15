/*
 * File: scheduling.c
 * Author: Brendon Hudnell
 * Purpose: This program reads in a schedule size. It then reads through pairs of integers which
 * 			represent a range of availability. It stores the ranges in the schedule array and
 *			returns data about the schedule once all ranges are entered.
 */

#include <stdio.h>
#include <stdlib.h>

int main(){
	int errcode, min, max, sum, rc, size, i, start, end;
	int *arr;
	errcode = 0;

	if ((rc=scanf("%d", &size)) == 0){
		fprintf(stderr, "ERROR: Could not read in array size.     rc=%d\n", rc);
		return 1;
	}

	if (size<1){
		fprintf(stderr, "ERROR: Schedule size is invalid.\n");
		return 1;
	}

	arr=calloc(size*sizeof(int), sizeof(int));
	if (arr==NULL){
		fprintf(stderr, "ERROR: Out of memory.\n");
		return 1;
	}

	while ((rc=scanf("%d %d", &start, &end)) != EOF){
		if (rc < 2){
			fprintf(stderr, "ERROR: Could not read in the range.\n");
			return 1;
		}

		if (start < 0 || end >= size){
			fprintf(stderr, "ERROR: Invalid range. Start or end is out of bounds.\n");
			errcode =  1;
			continue;
		}

		if (start > end){
			fprintf(stderr, "ERROR: Invalid range. Start is greater than end.\n");
			errcode = 1;
			continue;
		}

		for (i=start; i<=end; i++)
			*(arr+i) += 1;
	}

	min = *arr;
	max = *arr;

	printf("%d:", size);
	for (i=0; i<size; i++){
		printf(" %d", *(arr+i));
		sum += *(arr+i);
		if (*(arr+i) > max)
			max = *(arr+i);
		if (*(arr+i) < min)
			min = *(arr+i);
	}
	printf("\n");

	printf("minimum: %d\n", min);
	printf("minimum-slots:");
	for (i=0; i<size; i++){
		if (*(arr+i) == min)
			printf(" %d", i);
	}
	printf("\nmaximum: %d\n", max);
	printf("maximum-slots:");
	for (i=0; i<size; i++){
		if (*(arr+i) == max)
			printf(" %d", i);
	}
	printf("\naverage: %.2f\n", (double)sum/(double)size);

	return errcode;
}
