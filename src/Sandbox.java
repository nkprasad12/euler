import java.lang.invoke.MethodHandles;

class Sandbox {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
