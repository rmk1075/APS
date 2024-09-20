import java.util.*;

class Solution {
    private static final String EMPTY_SPACE = " ";
    private static final String X = "X";

    public String[] solution(String[] expressions) {
        List<String[]> hints = new ArrayList<>();
        List<String[]> questions = new ArrayList<>();
        for (String expression : expressions) {
            String[] splitted = expression.split(EMPTY_SPACE);
            if (splitted[4].equals(X)) {
                questions.add(splitted);
            } else {
                hints.add(splitted);
            }
        }

        boolean[] nums = initNums(expressions);
        List<Integer> found = find(nums, hints);
        return questions.stream().map(
        q -> makeAnswer(found, q)
        ).toArray(String[]::new);
    }

    private boolean[] initNums(String[] expressions) {
        boolean[] nums = new boolean[10];
        for (int i = 2; i < 10; i++) {
            nums[i] = true;
        }
        for (String expression : expressions) {
            String[] splitted = expression.split(" ");
            validate(nums, splitted[0]);
            validate(nums, splitted[2]);
            if (!splitted[4].equals("X")) {
                validate(nums, splitted[4]);
            }
        }
        return nums;
    }

    private void validate(boolean[] nums, String splitted) {
        for (char ch : splitted.toCharArray()) {
            for (int i = (ch - '0'); 1 < i; i--) {
                nums[i] = false;
            }
        }
    }

    private String makeAnswer(List<Integer> nums, String[] question) {
        String result = "";
        for (int num : nums) {
            int a = convert(num, question[0]);
            int b = convert(num, question[2]);
            int temp = question[1].equals("+") ? a + b : a - b;

            StringBuilder sb = new StringBuilder();
            if (temp == 0) {
                sb.append(0);
            } else {
                while (0 < temp) {
                    sb.insert(0, (temp % num));
                    temp = temp / num;
                }
            }

            if (result.equals("")) {
                result = sb.toString();
            } else {
                if (!result.equals(sb.toString())) {
                    result = "?";
                    break;
                }
            }
        }
        return String.format("%s %s %s = %s", question[0], question[1], question[2], result);
    }

    private List<Integer> find(boolean[] nums, List<String[]> hints) {
        for (String[] hint : hints) {
            nums = findHint(nums, hint);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 2; i < 10; i++) {
            if (nums[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean[] findHint(boolean[] nums, String[] hint) {
        for (int i = 2; i < nums.length; i++) {
            if (!nums[i]) {
                continue;
            }

            int num = i;
            int a = convert(num, hint[0]);
            int b = convert(num, hint[2]);
            int result = convert(num, hint[4]);
            if (hint[1].equals("+")) {
                if (a + b != result) {
                    nums[i] = false;
                }
            } else {
                if (a - b != result) {
                    nums[i] = false;
                }
            }
        }
        return nums;
    }

    private int convert(int a, String b) {
        char[] chs = b.toCharArray();
        int result = 0;
        for (int i = 0; i < chs.length; i++) {
            result += (chs[chs.length - 1 - i] - '0') * Math.pow(a, i);
        }
        return result;
    }
}