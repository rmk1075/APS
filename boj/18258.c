#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <malloc.h>

int main() {
    int* queue = (int*)malloc(2000000*sizeof(int));
    int front = 0;
    int tail = 0;
    int N;
    
    scanf("%d", &N);
    for(int i = 0; i < N; i++) {
        char *input;
        scanf("%s", input);
        if(!strcmp(strtok(input, " ")[0], "push")) {
            // int num;
            // scanf("%d", &num);
            queue[tail++] = atoi(strtok(input, " ")[1]);
        } else if(!strcmp(input, "pop")) {
            if(tail == front) {
                printf("-1\n");
            } else {
                printf("%d\n", queue[tail--]);
            }
        } else if(!strcmp(input, "size")) {
            printf("%d\n", tail-front);
        } else if(!strcmp(input, "empty")) {
            if(tail == front) {
                printf("1\n");
            } else {
                printf("0\n");
            }
        } else if(!strcmp(input, "front")) {
            if(tail == front) {
                printf("-1\n");
            } else {
                printf("%d\n", queue[front]);
            }
        } else if(!strcmp(input, "back")) {
            if(tail == front) {
                printf("-1\n");
            } else {
                printf("%d\n", queue[tail]);
            }
        }
    }

    return 0;
}
