import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static int[][] dimensions = { { 2, 5, 3, 4, 2 }, { 3, 5, 2, 4, 3 }, { 1, 5, 0, 4, 1 }, { 0, 5, 1, 4, 0 },
            { 1, 2, 0, 3, 1 }, { 1, 3, 0, 2, 1 } };
    static char[][][] cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while (0 < T--) {
            cube = new char[6][3][3];
            for (int c = 0; c < 6; c++) {
                char color;
                if (c == 0)
                    color = 'w';
                else if (c == 1)
                    color = 'y';
                else if (c == 2)
                    color = 'r';
                else if (c == 3)
                    color = 'o';
                else if (c == 4)
                    color = 'g';
                else
                    color = 'b';

                for (char[] dimension : cube[c]) {
                    Arrays.fill(dimension, color);
                }
            }

            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            while (0 < N--) {
                rotate(st.nextToken().toCharArray());
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(cube[0][i][j]);
                }
                sb.append("\n");
            }
        }

        System.out.print(sb.toString());
    }

    public static void rotate(char[] command) {
        int dimension;
        if (command[0] == 'U')
            dimension = 0;
        else if (command[0] == 'D')
            dimension = 1;
        else if (command[0] == 'F')
            dimension = 2;
        else if (command[0] == 'B')
            dimension = 3;
        else if (command[0] == 'L')
            dimension = 4;
        else
            dimension = 5;
  
        char temp;
        if (command[1] == '+') {
            temp = cube[dimension][0][0];
            cube[dimension][0][0] = cube[dimension][2][0];
            cube[dimension][2][0] = cube[dimension][2][2];
            cube[dimension][2][2] = cube[dimension][0][2];
            cube[dimension][0][2] = temp;
            temp = cube[dimension][0][1];
            cube[dimension][0][1] = cube[dimension][1][0];
            cube[dimension][1][0] = cube[dimension][2][1];
            cube[dimension][2][1] = cube[dimension][1][2];
            cube[dimension][1][2] = temp;
        } else {
            temp = cube[dimension][0][0];
            cube[dimension][0][0] = cube[dimension][0][2];
            cube[dimension][0][2] = cube[dimension][2][2];
            cube[dimension][2][2] = cube[dimension][2][0];
            cube[dimension][2][0] = temp;
            temp = cube[dimension][0][1];
            cube[dimension][0][1] = cube[dimension][1][2];
            cube[dimension][1][2] = cube[dimension][2][1];
            cube[dimension][2][1] = cube[dimension][1][0];
            cube[dimension][1][0] = temp;
        }

        int[] order = new int[4];
        for (int i = 0; i < 4; i++) {
            if (command[1] == '+')
                order[i] = dimensions[dimension][i];
            else
                order[i] = dimensions[dimension][4 - i];
        }
        
        char[] tempArr = new char[3];
        switch (dimension) {
            case 0:
            for (int i = 0; i < 3; i++) {
                tempArr[i] = cube[order[0]][0][i];
            }
            
            for (int i = 0; i < 3; i++) {
                cube[order[0]][0][i] = cube[order[1]][0][i];
            }
            
            for (int i = 0; i < 3; i++) {
                cube[order[1]][0][i] = cube[order[2]][2][2 - i];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[2]][2][2 - i] = cube[order[3]][0][i];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[3]][0][i] = tempArr[i];
            }
            break;
        case 1:
            for (int i = 0; i < 3; i++) {
                tempArr[i] = cube[order[0]][0][i];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[0]][0][i] = cube[order[1]][2][2 - i];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[1]][2][2 - i] = cube[order[2]][2][2 - i];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[2]][2][2 - i] = cube[order[3]][2][2 - i];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[3]][2][2 - i] = tempArr[i];
            }
            break;

        case 2:
            if(command[1] == '+') {
                for (int i = 0; i < 3; i++) {
                    tempArr[i] = cube[order[0]][0][i];
                }
    
                for (int i = 0; i < 3; i++) {
                    cube[order[0]][0][i] = cube[order[1]][2 - i][0];
                }
    
                for (int i = 0; i < 3; i++) {
                    cube[order[1]][2 - i][0] = cube[order[2]][2][2 - i];
                }
    
                for (int i = 0; i < 3; i++) {
                    cube[order[2]][2][2 - i] = cube[order[3]][i][2];
                }
    
                for (int i = 0; i < 3; i++) {
                    cube[order[3]][i][2] = tempArr[i];
                }

                break;
            } else {
                for (int i = 0; i < 3; i++) {
                    tempArr[i] = cube[order[0]][0][i];
                }

                for (int i = 0; i < 3; i++) {
                    cube[order[0]][0][i] = cube[order[1]][i][2];
                }

                for (int i = 0; i < 3; i++) {
                    cube[order[1]][i][2] = cube[order[2]][2][2 - i];
                }

                for (int i = 0; i < 3; i++) {
                    cube[order[2]][2][2 - i] = cube[order[3]][2 - i][0];
                }

                for (int i = 0; i < 3; i++) {
                    cube[order[3]][2 - i][0] = tempArr[i];
                }

                break;
            }
        case 3:
            if(command[1] == '+') {
                for (int i = 0; i < 3; i++) {
                    tempArr[i] = cube[order[0]][0][i];
                }
    
                for (int i = 0; i < 3; i++) {
                    cube[order[0]][0][i] = cube[order[1]][i][2];
                }
    
                for (int i = 0; i < 3; i++) {
                    cube[order[1]][i][2] = cube[order[2]][2][2 - i];
                }
    
                for (int i = 0; i < 3; i++) {
                    cube[order[2]][2][2 - i] = cube[order[3]][2 - i][0];
                }
    
                for (int i = 0; i < 3; i++) {
                    cube[order[3]][2 - i][0] = tempArr[i];
                }
                break;
            } else {
                for (int i = 0; i < 3; i++) {
                    tempArr[i] = cube[order[0]][0][i];
                }

                for (int i = 0; i < 3; i++) {
                    cube[order[0]][0][i] = cube[order[1]][2-i][0];
                }

                for (int i = 0; i < 3; i++) {
                    cube[order[1]][2-i][0] = cube[order[2]][2][2-i];
                }

                for (int i = 0; i < 3; i++) {
                    cube[order[2]][2][2-i] = cube[order[3]][i][2];
                }

                for (int i = 0; i < 3; i++) {
                    cube[order[3]][i][2] = tempArr[i];
                }
                break;
            }
        case 4:
            for (int i = 0; i < 3; i++) {
                tempArr[i] = cube[order[0]][2 - i][0];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[0]][2 - i][0] = cube[order[1]][2 - i][0];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[1]][2 - i][0] = cube[order[2]][2 - i][0];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[2]][2 - i][0] = cube[order[3]][2 - i][0];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[3]][2 - i][0] = tempArr[i];
            }
            break;
        case 5:
            for (int i = 0; i < 3; i++) {
                tempArr[i] = cube[order[0]][i][2];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[0]][i][2] = cube[order[1]][i][2];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[1]][i][2] = cube[order[2]][i][2];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[2]][i][2] = cube[order[3]][i][2];
            }

            for (int i = 0; i < 3; i++) {
                cube[order[3]][i][2] = tempArr[i];
            }
            break;
        }
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.Arrays;
// import java.util.StringTokenizer;

// public class Main {
//     static int T, N;
//     static int[][] dimensions = { { 2, 5, 3, 4, 2 }, { 3, 5, 2, 4, 3 }, { 1, 5, 0, 4, 1 }, { 0, 5, 1, 4, 0 },
//             { 1, 2, 0, 3, 1 }, { 1, 3, 0, 2, 1 } };
//     static char[][][] cube;

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringBuilder sb = new StringBuilder();
//         StringTokenizer st;

//         T = Integer.parseInt(br.readLine());
//         while (0 < T--) {
//             cube = new char[6][3][3];
//             for (int c = 0; c < 6; c++) {
//                 char color;
//                 if (c == 0)
//                     color = 'w';
//                 else if (c == 1)
//                     color = 'y';
//                 else if (c == 2)
//                     color = 'r';
//                 else if (c == 3)
//                     color = 'o';
//                 else if (c == 4)
//                     color = 'g';
//                 else
//                     color = 'b';

//                 for (char[] dimension : cube[c]) {
//                     Arrays.fill(dimension, color);
//                 }
//             }

//             N = Integer.parseInt(br.readLine());
//             st = new StringTokenizer(br.readLine());
//             while (0 < N--) {
//                 rotate(st.nextToken().toCharArray());
//             }

//             for (int i = 0; i < 3; i++) {
//                 for (int j = 0; j < 3; j++) {
//                     sb.append(cube[0][i][j]);
//                 }
//                 sb.append("\n");
//             }
//         }

//         System.out.print(sb.toString());
//     }

//     public static void rotate(char[] command) {
//         int dimension;
//         if (command[0] == 'U')
//             dimension = 0;
//         else if (command[0] == 'D')
//             dimension = 1;
//         else if (command[0] == 'F')
//             dimension = 2;
//         else if (command[0] == 'B')
//             dimension = 3;
//         else if (command[0] == 'L')
//             dimension = 4;
//         else
//             dimension = 5;

//         int[] order = new int[4];
//         for (int i = 0; i < 4; i++) {
//             if (command[1] == '+')
//                 order[i] = dimensions[dimension][i];
//             else
//                 order[i] = dimensions[dimension][4 - i];
//         }

//         char temp;
//         char[] tempArr = new char[3];
//         if (command[1] == '+') {
//             temp = cube[dimension][0][0];
//             cube[dimension][0][0] = cube[dimension][2][0];
//             cube[dimension][2][0] = cube[dimension][2][2];
//             cube[dimension][2][2] = cube[dimension][0][2];
//             cube[dimension][0][2] = temp;
//             temp = cube[dimension][0][1];
//             cube[dimension][0][1] = cube[dimension][1][0];
//             cube[dimension][1][0] = cube[dimension][2][1];
//             cube[dimension][2][1] = cube[dimension][1][2];
//             cube[dimension][1][2] = temp;

//             switch (dimension) {
//             case 0:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][0][i] = cube[order[1]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][0][i] = cube[order[2]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2][2 - i] = cube[order[3]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][0][i] = tempArr[i];
//                 }
//                 break;
//             case 1:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][0][i] = cube[order[1]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][2][2 - i] = cube[order[2]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2][2 - i] = cube[order[3]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][2][2 - i] = tempArr[i];
//                 }
//                 break;

//             case 2:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][0][i] = cube[order[1]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][2 - i][0] = cube[order[2]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2][2 - i] = cube[order[3]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][i][2] = tempArr[i];
//                 }
//                 break;
//             case 3:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][0][i] = cube[order[1]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][i][2] = cube[order[2]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2][2 - i] = cube[order[3]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][2 - i][0] = tempArr[i];
//                 }
//                 break;
//             case 4:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][2 - i][0] = cube[order[1]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][2 - i][0] = cube[order[2]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2 - i][0] = cube[order[3]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][2 - i][0] = tempArr[i];
//                 }
//                 break;
//             case 5:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][i][2] = cube[order[1]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][i][2] = cube[order[2]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][i][2] = cube[order[3]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][i][2] = tempArr[i];
//                 }
//                 break;
//             }

//         } else {
//             temp = cube[dimension][0][0];
//             cube[dimension][0][0] = cube[dimension][0][2];
//             cube[dimension][0][2] = cube[dimension][2][2];
//             cube[dimension][2][2] = cube[dimension][2][0];
//             cube[dimension][2][0] = temp;
//             temp = cube[dimension][0][1];
//             cube[dimension][0][1] = cube[dimension][1][2];
//             cube[dimension][1][2] = cube[dimension][2][1];
//             cube[dimension][2][1] = cube[dimension][1][0];
//             cube[dimension][1][0] = temp;

//             switch (dimension) {
//             case 0:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][0][i] = cube[order[1]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][0][i] = cube[order[2]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2][2 - i] = cube[order[3]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][0][i] = tempArr[i];
//                 }
//                 break;
//             case 1:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][0][i] = cube[order[1]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][2][2 - i] = cube[order[2]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2][2 - i] = cube[order[3]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][2][2 - i] = tempArr[i];
//                 }
//                 break;

//             case 2:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][0][i] = cube[order[1]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][i][2] = cube[order[2]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2][2 - i] = cube[order[3]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][2 - i][0] = tempArr[i];
//                 }
//                 break;
//             case 3:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][0][i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][0][i] = cube[order[1]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][2 - i][0] = cube[order[2]][2][2 - i];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2][2 - i] = cube[order[3]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][i][2] = tempArr[i];
//                 }
//                 break;
//             case 4:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][2 - i][0] = cube[order[1]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][2 - i][0] = cube[order[2]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][2 - i][0] = cube[order[3]][2 - i][0];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][2 - i][0] = tempArr[i];
//                 }
//                 break;
//             case 5:
//                 for (int i = 0; i < 3; i++) {
//                     tempArr[i] = cube[order[0]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[0]][i][2] = cube[order[1]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[1]][i][2] = cube[order[2]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[2]][i][2] = cube[order[3]][i][2];
//                 }

//                 for (int i = 0; i < 3; i++) {
//                     cube[order[3]][i][2] = tempArr[i];
//                 }
//                 break;
//             }
//         }
//     }
// }