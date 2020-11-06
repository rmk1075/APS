import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Node {
    char ch;
    int cnt;
    List<Node> next;

    Node() {
        this.cnt = 0;
        this.next = new ArrayList<>();
    }

    Node(char ch) {
        this.ch = ch;
        this.cnt = 1;
        this.next = new ArrayList<>();
    }
}

class Solution {
    int[] answer;
    Node[] frontTrie, backTrie;
    public int[] solution(String[] words, String[] queries) {
        answer = new int[queries.length];
        frontTrie = new Node[10001];
        backTrie = new Node[10001];
        for(String word : words) {
            createTrie(word.toCharArray());
        }

        int index = 0;
        for(String query : queries) {
            char[] queryArr = query.toCharArray();
            if(queryArr[0] == '?' && backTrie[queryArr.length] != null) searchTrie(false, index, backTrie[queryArr.length], queryArr);
            else if(frontTrie[queryArr.length] != null) searchTrie(true, index, frontTrie[queryArr.length], queryArr);

            index++;
        }

        return answer;
    }

    public void searchTrie(boolean isFront, int idx, Node node, char[] query) {
        int index = 0, weight = 1;
        if(!isFront) {
            index = query.length - 1;
            weight = -1;

            if(query[index] == '?') {
                int count = 0;
                for(Node n : node.next) count += n.cnt;
                answer[idx] += count;
                return ;
            }
        }

        while(true) {
            if(query[index] == '?') {
                answer[idx] += node.cnt;
                return ;
            }

            boolean flag = true;
            for(Node n : node.next) {
                if(n.ch == query[index]) {
                    node = n;
                    flag = false;
                    break;
                }
            }

            if(flag) return ;

            index += weight;
        }
    }

    public void createTrie(char[] word) {
        Node node;
        int len = word.length;

        // front
        if(frontTrie[len] == null) frontTrie[len] = new Node();
        node = frontTrie[len];
        for(int i = 0; i < len; i++) {
            char ch = word[i];
            boolean isExist = false;
            for(Node n : node.next) {
                if(n.ch == ch) {
                    isExist = true;
                    node = n;
                    node.cnt++;
                    break;
                }
            }

            if(!isExist) {
                node.next.add(new Node(ch));
                node = node.next.get(node.next.size() - 1);
            }
        }

        // back
        if(backTrie[len] == null) backTrie[len] = new Node();
        node = backTrie[len];
        for(int i = len - 1; -1 < i; i--) {
            char ch = word[i];
            boolean isExist = false;
            for(Node n : node.next) {
                if(n.ch == ch) {
                    isExist = true;
                    node = n;
                    node.cnt++;
                    break;
                }
            }

            if(!isExist) {
                node.next.add(new Node(ch));
                node = node.next.get(node.next.size() - 1);
            }
        }

    }
}

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// class Node {
//     char ch;
//     List<int[]> list;
//     List<Node> next;

//     Node(char ch) {
//         this.ch = ch;
//         this.list = new ArrayList<>();
//         this.next = new ArrayList<>();
//     }
// }

// class Solution {
//     int[] answer;
//     Node frontTrie, backTrie;
//     public int[] solution(String[] words, String[] queries) {
//         answer = new int[queries.length];
//         frontTrie = new Node(' ');
//         backTrie = new Node(' ');
//         for(int i = 0; i < queries.length; i++) createTrie(queries[i].charAt(0) != '?', i, queries[i].toCharArray());

//         for(String word : words) {
//             char[] wordArr = word.toCharArray();
//             int len = wordArr.length;
//             char front = wordArr[0], back = wordArr[len - 1];
            
//             for(int[] l : backTrie.list) {
//                 if(l[1] == len) answer[l[0]]++;
//             }

//             for(Node t : frontTrie.next) {
//                 if(t.ch == front) {
//                     searchTrie(t, 1, wordArr, 1, len);
//                 }
//             }

//             for(Node t : backTrie.next) {
//                 if(t.ch == back) {
//                     searchTrie(t, -1, wordArr, 1, len);
//                 }
//             }
//         }

//         // TODO:
//         // System.out.println(Arrays.toString(frontTrie.list.get(0)));

//         return answer;
//     }

//     public void searchTrie(Node node, int weight, char[] word, int index, int len) {
//         char ch = word[index];

//         for(int[] l : node.list) {
//             if(l[1] == len - index) answer[l[0]]++;
//         }

//         for(Node n : node.next) {
//             if(n.ch == ch) searchTrie(n, weight, word, index + weight, len);
//         }
//     }

//     public void createTrie(boolean isFront, int index, char[] query) {
//         Node node;
//         int start, end, weight, len = query.length;
        
//         if(isFront) {
//             node = frontTrie;
//             start = 0;
//             end = len;
//             weight = 1;
//         } else {
//             node = backTrie;
//             start = len - 1;
//             end = -1;
//             weight = -1;
//         }
        
//         int i;
//         for(i = start; i != end; i += weight) {
//             char ch = query[i];
//             if(ch == '?') break;

//             boolean isExist = false;
//             for(Node n : node.next) {
//                 if(n.ch == ch) {
//                     isExist = true;
//                     node = n;
//                     break;
//                 }
//             }

//             if(!isExist) {
//                 node.next.add(new Node(ch));
//                 node = node.next.get(node.next.size() - 1);
//             }
//         }

//         // last : add to list
//         node.list.add(new int[] {index, len - i});
//     }
// }

// import java.util.ArrayList;
// import java.util.List;

// class Node {
//     char ch;
//     List<Integer> list;
//     List<Node> next;

//     Node(char ch) {
//         this.ch = ch;
//         this.list = new ArrayList<>();
//         this.next = new ArrayList<>();
//     }
// }

// class Solution {
//     int[] answer;
//     Node trie;
//     public int[] solution(String[] words, String[] queries) {
//         answer = new int[queries.length];
//         trie = new Node(' ');
//         for(int i = 0; i < queries.length; i++) {
//             createTrie(i, queries[i].toCharArray());
//         }

//         for(String word : words) {
//             char[] wordArr = word.toCharArray();
//             char ch = wordArr[0];
//             int len = wordArr.length;
//             for(Node t : trie.next ) {
//                 if(t.ch == '?' || t.ch == ch) {
//                     searchTrie(t, wordArr, 1, len);
//                 }
//             }
//         }

//         return answer;
//     }

//     public void searchTrie(Node node, char[] word, int index, int len) {
//         // if(index == len) {
//         //     for(int num : node.list) {
//         //         answer[num]++;
//         //     }
//         //     return ;
//         // }

//         char ch = word[index];
//         for(Node n : node.next) {
//             if(n.ch == '?' || n.ch == ch) {
//                 if(index == len - 1) {
//                     for(int num : n.list) {
//                         answer[num]++;
//                     }
//                     continue;
//                 }

//                 searchTrie(n, word, index + 1, len);
//             }
//         }
//     }

//     public void createTrie(int index, char[] query) {
//         Node node = trie;
//         for(char ch : query) {
//             boolean isExist = false;
//             for(Node n : node.next) {
//                 if(n.ch == ch) {
//                     isExist = true;
//                     node = n;
//                     break;
//                 }
//             }

//             if(!isExist) {
//                 node.next.add(new Node(ch));
//                 node = node.next.get(node.next.size() - 1);
//             }
//         }

//         // last : add to list
//         node.list.add(index);
//     }
// }