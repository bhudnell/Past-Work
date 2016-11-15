/*
 * File: util.h
 * Author: Brendon Hudnell
 * Purpose: the header file for all the utility functions for the lineSort program
 */

#ifndef _UTIL_H_
#define _UTIL_H_

char **extendArray(char **oldArray, int oldLen, int newLen);
void sortArray(char **array, int len);
void freeArray(char **array, int len);

#endif
