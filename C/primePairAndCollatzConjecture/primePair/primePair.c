/*
 * File: primePair.c
 * Author: Brendon Hudnell
 * Purpose: a program to print out all of the numbers between two user-input integers
 *          that are the product of exactly two primes.
 */

#include <stdio.h>
#include <math.h>

int firstFactor(int val);
int isPrime(int val);

int main(){
	int errcode = 0;
	int rc, lower, upper;

	rc = scanf("%d %d", &lower, &upper);

	if (rc < 2){
		fprintf(stderr, "ERROR: One or both inputs were not integers.\n");
		errcode = 1;
	}
	else if (lower > upper){
		fprintf(stderr, "ERROR: lower bound is greater than upper bound.\n");
		errcode = 1;
	}
	else if (lower < 2){
		fprintf(stderr, "ERROR: lower bound is less than 2.\n");
		errcode = 1;
	}
	else{
		int i, first, second;
		for (i=lower; i<=upper; i++){
			first = firstFactor(i);
			if (first == i)
				continue;
			second = i/first;
			if (isPrime(second))
				printf("%d: %d %d\n", i, first, second);
		}
	}
	return errcode;
}

/*
 * firstFactor(val) -- returns the smallest prime factor of val, or returns
 * val if val has no prime factors.
 */
int firstFactor(int val){
	int i;
	for (i=2; i<=sqrt(val); i++){
		if (isPrime(i) && val%i == 0)
			return i;
	}
	return val;
}

/*
 * isPrime(val) -- returns 1 if val is prime, or 0 otherwise.
 */
int isPrime(int val){
	int i;
	for (i=2; i<val; i++){
		if (val%i == 0)
			return 0;
	}
	return 1;
}
