/*
 * File: phoneCalls.c
 * Author: Brendon Hudnell
 * Purpose: 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

typedef struct outerNode {
	char phoneNum[9];
	struct outerNode *next;
	struct innerNode *head;
} outerNode;

typedef struct innerNode {
	char phoneNum[9];
	int count;
	struct innerNode *next;
} innerNode;

int isPhoneNumber(char *num);
outerNode* insertIntoOuterList(outerNode *head, char *firstNum, char *secondNum);
innerNode* insertIntoInnerList(innerNode *head, char *secondNum);

int main(int argc, char **argv){

	int errcode = 0;
	outerNode *head = NULL;
	int lineCount, i, errno, rc;
	FILE *fp;
	char buff[20], firstNum[10], secondNum[10];

	//checks the command line arguments for error conditions
	if (argc < 4){
		fprintf(stderr, "ERROR: Either no file arguments or not 2 phone number arguments.\n");
		exit(1);
	}
	if (!isPhoneNumber(argv[argc-2]) || !isPhoneNumber(argv[argc-1])){
		fprintf(stderr, "ERROR: The phone number arguments were not in the form xxx-xxxx.\n");
		exit(1);
	}

	//main loop to open each file and put its contents in the linked list of linked lists
	for (i=1; i<argc-2; i++){
		fp = fopen(argv[i], "r");
		if (fp == NULL){
			perror(argv[i]);
			errcode = 1;
			continue;
		}
		lineCount = 0;
		while (fgets(buff, 20, fp) != NULL){
			rc = sscanf(buff, "%9s %9s", firstNum, secondNum);

			// checking file line input for error conditions
			if (rc < 1)
				continue;
			if (rc != 2){
				fprintf(stderr, "ERROR: Line contains an incorrect number of phone numbers.\n");
				errcode = 1;
				continue;
			}
			if (!isPhoneNumber(firstNum) || !isPhoneNumber(secondNum)){
				fprintf(stderr, "ERROR: Line does not contain a phone number.\n");
				errcode = 1;
				continue;
			}
			if (strcmp(firstNum, secondNum) == 0){
				fprintf(stderr, "ERROR: Line has identical phone numbers.\n");
				errcode = 1;
				continue;
			}

			//enter two phone numbers into the linked list of linked lists
			head = insertIntoOuterList(head, firstNum, secondNum);
			head = insertIntoOuterList(head, secondNum, firstNum);
			lineCount++;
		}
		//empty file error message
		if (lineCount == 0){
			fprintf(stderr, "ERROR: Empty file.\n");
			errcode = 1;
		}
	fclose(fp);
	}

	//searching linked list of linked lists to find the number of times the two numbers called each other
	outerNode *oCurr = head;
	while (oCurr != NULL && strcmp(oCurr->phoneNum, argv[argc-2]) < 0)
		oCurr = oCurr->next;
	if (oCurr != NULL && strcmp(oCurr->phoneNum, argv[argc-2]) == 0){
		innerNode *iCurr = oCurr->head;
		while (iCurr != NULL && strcmp(iCurr->phoneNum, argv[argc-1]) < 0)
			iCurr = iCurr->next;
		if (iCurr != NULL && strcmp(iCurr->phoneNum, argv[argc-1]) == 0)
			printf("%d\n", iCurr->count);
		else
			printf("0\n");
	}
	else
		printf("0\n");

	//searching linked list of linked lists to find if the two numbers called the same person
	outerNode *oPtr0, *oPtr1;
	innerNode *iPtr0, *iPtr1;
	iPtr0 = NULL;
	oPtr0 = NULL;
	oPtr1 = NULL;
	oCurr = head;
	int calledSame = 0;
	while (oCurr != NULL){
		if (strcmp(oCurr->phoneNum, argv[argc-2]) == 0)
			oPtr0 = oCurr;
		if (strcmp(oCurr->phoneNum, argv[argc-1]) == 0)
			oPtr1 = oCurr;
		oCurr = oCurr->next;
	}

	if (oPtr0 != NULL && oPtr1 != NULL){
		iPtr0 = oPtr0->head;
		iPtr1 = oPtr1->head;
		while (iPtr0 != NULL){
			while (iPtr1 != NULL){
				if (strcmp(iPtr0->phoneNum, iPtr1->phoneNum) == 0){
					calledSame = 1;
					break;
				}
				iPtr1 = iPtr1->next;
			}
			if (calledSame)
				break;
			iPtr0 = iPtr0->next;
		}
	}

	if (calledSame)
		printf("yes\n");
	else
		printf("no\n");

	//freeing the linked list
	oPtr0 = head;
	while (oPtr0 != NULL){

		iPtr0 = oPtr0->head;
		while (iPtr0 != NULL){
			iPtr1 = iPtr0->next;
			free(iPtr0);
			iPtr0 = iPtr1;
		}

		oPtr1 = oPtr0->next;
		free(oPtr0);
		oPtr0 = oPtr1;
	}

	return errcode;
}

/*
 * isPhoneNumber(char*) -- returns 1 if num is in the form "xxx-xxxx" where x is an integer
 *                        between 0 and 9. returns 0 otherwise.
 */
int isPhoneNumber(char *num){
	int len = strlen(num);
	if (len != 8)
		return 0;
	int i;
	for (i=0; i<len; i++){
		if (i != 3 && (num[i] < '0' || (int)num[i] > '9'))
			return 0;
		if (i == 3 && num[i] != '-')
			return 0;
	}
	return 1;
}

/*
 * insertIntoOuterList(outerNode*, char*, char*) -- Scans through the outer list, and inserts
 *				a new outerNode in order, then inserts the innerNode into the outerNode.. Returns
 *				the head of the list. If either number is already in the list, it will just add
 *				an innerNode of the other number to that number's node.
 */
outerNode* insertIntoOuterList(outerNode *head, char *firstNum, char *secondNum){
	outerNode *curr = head;
	outerNode **ptrInPrev = &head;

	while (curr != NULL && strcmp(curr->phoneNum, firstNum) < 0){
		ptrInPrev = &curr->next;
		curr = curr->next;
	}

	if (curr != NULL && strcmp(curr->phoneNum, firstNum) == 0){
		curr->head = insertIntoInnerList(curr->head, secondNum);
		return head;
	}

	outerNode *temp = malloc(sizeof(outerNode));
	if (temp == NULL){
		perror("Failed to allocate a new node for the outer linked list.");
		exit(1);
	}

	strcpy(temp->phoneNum, firstNum);
	temp->head = NULL;
	temp->head = insertIntoInnerList(temp->head, secondNum);

	temp->next = curr;
	*ptrInPrev = temp;

	return head;
}

/*
 * insertIntoInnerList(innerNode*, char*) -- Scans through the inner list, and inserts
 *				a new innerNode in order. Returnsthe head of the list. If the number is
 *				already in the list, it will just increment the count variable of that node.
 */
innerNode* insertIntoInnerList(innerNode *head, char *secondNum){
	innerNode *curr = head;
	innerNode **ptrInPrev = &head;

	while (curr != NULL && strcmp(curr->phoneNum, secondNum) < 0){
		ptrInPrev = &curr->next;
		curr = curr->next;
	}

	if (curr != NULL && strcmp(curr->phoneNum, secondNum) == 0){
		curr->count++;
		return head;
	}

	innerNode *temp = malloc(sizeof(innerNode));
	if (temp == NULL){
		perror("Failed to allocate a new node for the inner linked list.");
		exit(1);
	}

	strcpy(temp->phoneNum, secondNum);
	temp->count = 1;

	temp->next = curr;
	*ptrInPrev = temp;

	return head;
}
