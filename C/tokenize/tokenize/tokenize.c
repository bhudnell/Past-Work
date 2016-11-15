/*
 * File: tokenize.c
 * Author: Brendon Hudnell
 * Purpose: A program that reads lines of input, tokenizes each line, stores the tokens in a linked list,
 *          then prints out the tokens of each line.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * isWhitespace(chr) -- returns 0 if the character is a space, tab, or newline. Returns 1 otherwise.
 */
int isWhitespace(char chr){
	if (chr == ' ' || chr == '\t' || chr == '\n')
		return 1;
	return 0;
}

int main(){
	typedef struct node{
		struct node *next;
		int count;
		char **tokens;
	} node;

	char *buff = NULL;
	node *head = NULL;
	node *ptr = NULL;
	node *curr = NULL;
	int j, i, tokenloc, bufflen;
	int lines = 0;
	int tokens = 0;
	size_t len = 0;

	while (getline(&buff, &len, stdin) != -1){

		ptr = malloc(sizeof(node));
		if (ptr == NULL){
			fprintf(stderr, "ERROR: Out of memory.\n");
			return 1;
		}
		ptr->next = NULL;
		ptr->tokens = NULL;
		ptr->count = 0;

		bufflen = strlen(buff);
		//counting the number of tokens in the line
		if (!isWhitespace(buff[0]) && buff[0] != '\0')
			ptr->count++;
		for (i=1; i<bufflen; i++){
			if (isWhitespace(buff[i-1]) && !isWhitespace(buff[i]))
				ptr->count++;
		}

		//allocating the array of pointers
		ptr->tokens = malloc(ptr->count*sizeof(char*));
		if (ptr->tokens == NULL){
			fprintf(stderr, "ERROR: Out of memory.\n");
			return 1;
		}
		tokenloc = 0;

		//check first char for token start
		if (!isWhitespace(buff[0]) && buff[0] != '\0'){
			ptr->tokens[tokenloc] = &buff[0];
			tokenloc++;
		}
		else
			buff[0] = '\0';

		//check rest of chars for token start
		for (i=1; i<bufflen; i++){
			if (!isWhitespace(buff[i])){
				if (buff[i-1] == '\0'){
					ptr->tokens[tokenloc] = &buff[i];
					tokenloc++;
				}
			}
			else
				buff[i] = '\0';
		}

		lines++;
		tokens += ptr->count;
		if (head == NULL){
			head = ptr;
			curr = head;
		}
		else{
			curr->next = ptr;
			curr = curr->next;
		}
		ptr = NULL;
		buff = NULL;
	}

	printf("Lines=%d Tokens=%d\n", lines, tokens);
	ptr = head;
	i=0;
	while (ptr != NULL){
		printf("Line=%d Tokens: %d\n", i, ptr->count);
		for (j=0; j < ptr->count; j++)
			printf("  Line=%d Token=%d: \"%s\"\n", i, j, ptr->tokens[j]);
		i++;
		ptr = ptr->next;
	}
	return 0;
}
