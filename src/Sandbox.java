package src;

import java.lang.invoke.MethodHandles;

class Sandbox {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
  }
}