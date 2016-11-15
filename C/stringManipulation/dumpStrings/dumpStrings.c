/*
 * File: dumpStrings.c
 * Author: Brendon Hudnell
 * Purpose: This program reads strings from input. For each charater in the string, it prints out the
			character's index, the character itself, its decimal value, and its hex value.
 */

#include <stdio.h>
#include <string.h>

int main(){
	int i;
	char str[32];

	while (scanf("%31s", str) != EOF){
		for (i=0; str[i] != '\0'; i++)
			printf("index=%d char='%c' dec=%d hex=%#4x\n", i, str[i], str[i], str[i]);
		printf("count=%d strlen=%d\n\n", i, (int)strlen(str));
	}
	return 0;
}
