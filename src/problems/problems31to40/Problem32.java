package src.problems.problems31to40;

import java.lang.invoke.MethodHandles;

import java.util.Arrays;
import java.util.HashSet;

import src.utils.generators.Generators;

public class Problem32 {

    public static void main(String[] args) {
        System.out.println(MethodHandles.lookup().lookupClass());

        HashSet<Integer> productSet = 
            Generators.range(100, 9999)
                .map(
                    a -> 
                        Generators.range(1, a)
                            .filter(b -> isPandigital("" + a + "" + b + "" + a * b))
                            .map(b -> a * b)
                            .collectInto(new HashSet<>()))
                .reducing(
                    new HashSet<Integer>(), 
                    (soFar, next) -> {
                      soFar.addAll(next);
                      return soFar;
                    })
                .lastValue();
        Generators.from(productSet)
            .reducing(0, (sum, next) -> sum + next)
            .printLast();
        
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