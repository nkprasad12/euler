import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

import utils.generators.Generators;
import utils.primes.Primes;

class Sandbox {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(Generators.range(2, 99999).filter(Primes::isPrimeStatic).collectInto(new ArrayList<>()));
  }
}