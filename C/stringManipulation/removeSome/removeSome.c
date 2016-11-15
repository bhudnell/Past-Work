/*
 * File: removeSome.c
 * Author: Brendon Hudnell
 * Purpose: This program reads strings from input. If the string contains only letters, it removes any vowels
 * 			and prints the resulting string. If it contains only digits, it removes even digits and prints the
			reverse of the resulting string.
 */

#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main(){
	char str[32];
	int i;
	int errcode=0;

	while (scanf("%31s", str) != EOF){
		if (isdigit(str[0])){
			for (i=0; str[i] != '\0'; i++){
				if (!isdigit(str[i])){
					fprintf(stderr, "ERROR: the string '%s' is a mix of letters and numbers.\n", str);
					errcode=1;
					break;
				}
			}
			if (i == strlen(str)){
				for (i=strlen(str)-1; i >= 0; i--){
					if ((int)str[i]%2 == 1)
						printf("%c", str[i]);
				}
				printf("\n");
			}
		}
		else if (isalpha(str[0])){
			for (i=0; str[i] != '\0'; i++){
				if (!isalpha(str[i])){
					fprintf(stderr, "ERROR: the string '%s' is a mix of letters and numbers.\n", str);
					errcode=1;
					break;
				}
			}
			if (i == strlen(str)){
				char chr;
				for (i=0; str[i] != '\0'; i++){
					chr = tolower(str[i]);
					if (chr != 'a' && chr != 'e' && chr != 'i' && chr != 'o' && chr != 'u')
						printf("%c", str[i]);
				}
				printf("\n");
			}
		}
		else
			fprintf(stderr, "ERROR: the string '%s' is a mix of letters and numbers.\n", str);
	}
	return errcode;
}
