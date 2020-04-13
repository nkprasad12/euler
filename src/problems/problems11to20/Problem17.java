package src.problems.problems11to20;

import java.lang.invoke.MethodHandles;

public class Problem17 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    int total = 0;
    for (int i = 1; i <= 1000; i++) {
      total += Number.lengthOfSpelled(i);
    }
    System.out.println(total);  
  }
  
  private static final class Number {
    
    static int lengthOfSpelled(int num) {
      switch (num) {
        case 0: return 0;
        case 1: return 3; 
        case 2: return 3;
        case 3: return 5;
        case 4: return 4;
        case 5: return 4;
        case 6: return 3;
        case 7: return 5;
        case 8: return 5;
        case 9: return 4;
        case 10: return 3;
        case 11: return 6;
        case 12: return 6;
        case 13: return 8;
        case 14: return 8;
        case 15: return 7;
        case 16: return 7;
        case 17: return 9;
        // eighteen
        case 18: return 8;
        case 19: return 8;
        case 20: return 6;
        case 30: return 6;
        case 40: return 5;
        case 50: return 5;
        case 60: return 5;
        case 70: return 7;
        case 80: return 6;
        case 90: return 6;
        default: return handleOtherValues(num);
      }    
    }

    static int handleOtherValues(int num) {
      int result = 0;
      if (num == 1000) {
        return 11;
      }
      if (num < 100) {
        int oneDigit = num % 10;
        return lengthOfSpelled(oneDigit) + lengthOfSpelled(num - oneDigit);
      } else {
        if (num % 100 != 0) {
          result += 3;
        }

        int hundredPartLength = num / 100;
        int tensAndOnes = num % 100;

        result += lengthOfSpelled(hundredPartLength) + lengthOfSpelled(tensAndOnes) + 7;
        return result;
      }
    } 
  }
}