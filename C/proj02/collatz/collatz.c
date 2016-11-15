/*
 * File: collatz.c
 * Author: Brendon Hudnell
 * Purpose: A program that takes integer user input and outputs numbers according to the collatz conjecture
 */

#include <stdio.h>

int collatz(int num);

int main(){
	int errcode = 0;

	int rc, n, val;
	while(1){
		rc = scanf("%d", &n);

		if (rc == EOF)
			break;
		if (rc == 0){
			fprintf(stderr, "ERROR: The input was not an integer\n");
			errcode = 1;
			break;
		}
		if (n <= 0){
			fprintf(stderr, "ERROR: The input was a negative number.\n");
			errcode = 1;
		}
		else {
			val = collatz(n);
			printf("%d: %d", n, val);
			while (val > n){
				val = collatz(val);
				printf(" %d", val);
			}
			printf("\n");
		}
	}
	return errcode;
}

/*
 * collatz(num) -- returns the next value of num given by the collatz conjecture.
 * It assumes num is greater than zero.
 */
int collatz(int num){
	if (num%2 == 0)
		return (num/2);
	return (3*num+1);
}
