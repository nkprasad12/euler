package src;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

import src.utils.generators.Generators;
import src.utils.primes.Primes;

class Sandbox {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(Generators.range(2l, 999999l).filter(Primes::isPrimeStatic).collectInto(new ArrayList<>()).size());
  }
}