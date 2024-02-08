import java.util.Arrays;

class Solution {
  private static int plusCount, profit;
  private static int[] priceList;
  private static int[][] discountList;
  private static int[] discountCartegory = {10, 20, 30, 40};
  public int[] solution(int[][] users, int[] emoticons) {
      init(emoticons);
      find(users, emoticons, 0);
      return new int[]{plusCount, profit};
  }

  private void init(int[] emoticons) {
    discountList = new int[emoticons.length][2];
    for (int i = 0; i < discountList.length; i++) {
      discountList[i] = new int[]{i, -1};
    }
    priceList = new int[emoticons.length];
  }

  private void find(int[][] users, int[] emoticons, int index) {
    if (index == emoticons.length) {
      calculate(users);
      return;
    }

    for (int i = 0; i < discountCartegory.length; i++) {
      int discount = discountCartegory[i];
      discountList[index][1] = discount;
      priceList[index] = emoticons[index] * (100 - discount) / 100;
      find(users, emoticons, index + 1);
    }
  }

  private void calculate(int[][] users) {
    int tempPlusCount = 0;
    int tempProfit = 0;
    for (int[] user : users) {
      int price = calculatePrice(user[0]);
      if (user[1] <= price) {
        tempPlusCount++;
      } else {
        tempProfit += price;
      }
    }

    if (plusCount <= tempPlusCount) {
      if (plusCount < tempPlusCount) {
        profit = tempProfit;
      } else {
        profit = Math.max(profit, tempProfit);
      }
      plusCount = tempPlusCount;
    }
  }

  private int calculatePrice(int limit) {
    return Arrays.stream(discountList).filter(discount -> limit <= discount[1]).mapToInt(discount -> priceList[discount[0]]).sum();
  }
}