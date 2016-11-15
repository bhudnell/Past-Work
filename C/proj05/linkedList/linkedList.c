/*
 * File: linkedList.c
 * Author: Brendon Hudnell
 * Purpose: A program that contains a linked list structure. It takes user commands (print, insert,
 *          delete, and removeHead) to modify the linked list. Uses error checking throughout to make
 * 	    sure any invalid commands are disregarded.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
	typedef struct node{
		char name[21];
		int num;
		struct node *next;
	} node;

	node *head = NULL;
	char *line;
	int rc, size, errcode, i;
	errcode = 0;
	size = 0;
	line = malloc(81);
	if (line == NULL){
		fprintf(stderr, "ERROR: Out of memory.\n");
		return 1;
	}

	// Main loop
	while (fgets(line, 81, stdin) != NULL){
		char tempName[80], command[80];
		int tempNum;

		// Checks the line length
		if (line[0] == '\n')
			continue;
		for(i=0; i<81; i++){
			if (line[i] == '\n')
				break;
		}
		if (i > 80){
			fprintf(stderr, "ERROR: Line was over 80 characters in length.\n");
			return 1;
		}

		// Checks that the line isnt blank
		if ((rc = sscanf(line, "%79s %79s %d", command, tempName, &tempNum)) == -1){
			fprintf(stderr, "ERROR: invalid line\n");
			errcode = 1;
			continue;
		}

		// Print command
		if (strcmp(command, "print") == 0){
			node *curr = head;
			printf("%d:", size);
			while (curr != NULL){
				printf(" %s/%d", curr->name, curr->num);
				curr = curr->next;
			}
			printf("\n");
		}

		// Insert command
		else if (strcmp(command, "insert") == 0){
			// Check return code for at least 3 inputs
			if (rc < 3){
				fprintf(stderr, "Error: Not enough inputs for the the insert command.\n");
				errcode = 1;
				continue;
			}

			// Check that name is <= 20 bytes
			if (strlen(tempName) > 19){
				fprintf(stderr, "ERROR: Name is larger than 20 characters.\n");
				errcode = 1;
				continue;
			}

			// Insert into empty list
			if (head == NULL){
				head = malloc(sizeof(node));
				if (head == NULL){
					fprintf(stderr, "ERROR: Out of memory.\n");
					return 1;
				}
				strcpy(head->name, tempName);
				head->num = tempNum;
				head->next = NULL;
				size++;
				continue;
			}

			// Insert before head
			if (strcmp(tempName, head->name) < 0){
				node *temp = malloc(sizeof(node));
				if (temp == NULL){
					fprintf(stderr, "ERROR: out of memory.\n");
					return 1;
				}
				strcpy(temp->name, tempName);
				temp->num = tempNum;
				temp->next = head;
				head = temp;
				size++;
				continue;
			}

			// Insert after head
			node *curr = head;
			node *prev = head;
			while (strcmp(tempName, curr->name) > 0 && curr->next != NULL){
				prev = curr;
				curr = curr->next;
			}
			if (strcmp(tempName, curr->name) == 0){
				fprintf(stderr, "ERROR: Name is already in the list.\n");
				errcode = 1;
				continue;
			}
			else{
				node *temp = malloc(sizeof(node));
				if (temp == NULL){
					fprintf(stderr, "ERROR: Out of memory.\n");
					return 1;
				}
				strcpy(temp->name, tempName);
				temp->num = tempNum;
				if (strcmp(tempName, curr->name) < 0){
					temp->next = prev->next;
					prev->next = temp;
				}
				else{
					temp->next = curr->next;
					curr->next = temp;
				}
				size++;
				continue;
			}
		}

		//Delete command
		else if (strcmp(command, "delete") == 0){
			// Check return code for at least 2 inputs
			if (rc < 2){
				fprintf(stderr, "ERROR: Not enough inputs for the delete command.\n");
				errcode = 1;
				continue;
			}

			// Check for empty list
			if (head == NULL){
				fprintf(stderr, "ERROR: the list is already empty.\n");
				errcode = 1;
				continue;
			}

			// Delete head case
			if (strcmp(tempName, head->name) == 0){
				head = head->next;
				size--;
				continue;
			}

			// Delete anywhere else case
			node *curr = head->next;
			node *prev = head;
			while (curr != NULL){
				if (strcmp(tempName, curr->name) == 0){
					prev->next = curr->next;
					size--;
					break;;
				}
				prev = curr;
				curr = curr->next;
			}
			if (curr == NULL){
				fprintf(stderr, "ERROR: That name does not exist in the list.\n");
				errcode = 1;
			}
		}

		// RemoveHead command
		else if (strcmp(command, "removeHead") == 0){
			if (head == NULL){
				fprintf(stderr, "ERROR: list is already empty.\n");
				errcode = 1;
				continue;
			}
			head = head->next;
			size--;
		}
		else{
			fprintf(stderr, "ERROR: command not recognized.\n");
			errcode = 1;
			continue;
		}
	}
	return errcode;
}
