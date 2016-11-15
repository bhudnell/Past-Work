lineSort : main.o util.o
	gcc -Wall -g -fprofile-arcs -ftest-coverage -o lineSort main.o util.o

main.o : main.c util.h
	gcc -Wall -g -fprofile-arcs -ftest-coverage -c main.c

util.o : util.c util.h
	gcc -Wall -g -fprofile-arcs -ftest-coverage -c util.c
