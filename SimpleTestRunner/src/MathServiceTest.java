public class MathServiceTest
{
   @Test
   public void passingTest()
   {
      final int a = 1;
      final int b = 1;
      final int expected = 2;
      final int actual;

      MathService service = new MathService();
      actual = service.add(a, b);

      if (expected != actual)
      {
         throw new RuntimeException(String.format("Test failed. Expected %d, but found %d%n", expected, actual));
      }
   }

   @Test
   public void failingTest()
   {

      final int a = 1;
      final int b = 1;
      final int expected = 3;
      final int actual;

      MathService service = new MathService();
      actual = service.add(a, b);

      if (expected != actual)
      {
         throw new RuntimeException(String.format("Test failed. Expected %d, but found %d%n", expected, actual));
      }
   }
}
