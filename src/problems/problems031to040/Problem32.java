package src.problems.problems031to040;

import java.lang.invoke.MethodHandles;

import java.util.Arrays;
import java.util.HashSet;

import src.utils.generators.Generators;

public class Problem32 {

    public static void main(String[] args) {
        System.out.println(MethodHandles.lookup().lookupClass());

        HashSet<Integer> products =
            Generators.range(100, 9999)
                .mapAndPair(a -> Generators.range(1, a))
                .filter((a, b) -> isPandigital(a + "" + b + "" + a * b))
                .mapPair((a, b) -> a * b)
                .collectInto(new HashSet<>());
        Generators.from(products)
            .reduceAndPrint(0, (sum, next) -> sum + next);
    
        HashSet<Integer> set = new HashSet<Integer>();
        for (int a = 100; a < 9999; a++) {
            for (int b = 0; b < a; b++ ) {
              Integer c = a*b;
              if (isPandigital(a + "" + b + "" + c.toString())) {
                  set.add(c);
              }  
            }
        }
        
        Integer sum = 0;
        for (Integer i : set) {
            sum += i;
        }
        System.out.println(sum);   
    }

    public static boolean isPandigital(String input) {
        if (input.length() != 9) {
            return false;
        }
        char[] ordered = input.toCharArray();
        Arrays.sort(ordered);
        return new String(ordered).equals("123456789");
    }
}