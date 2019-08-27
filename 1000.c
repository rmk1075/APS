#include<stdio.h>

int main() {
    int A = -1;
    int B = -1;
    int C;

    if(A < 0 || B < 0 || 10 < A || 10 < B) {
        scanf("%d %d", &A, &B);
    }

    C = A + B;

    printf("%d", C);

    return 0;
}
