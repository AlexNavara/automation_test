package generics;

public class Dog implements Killable
{
   private boolean isAlive = true;

   @Override
   public void kill()
   {
      this.isAlive = false;
   }

   public void bark()
   {
      if (isAlive)
      {
         System.out.println("Bark");
      }
      else
      {
         throw new DeadException("Dog is dead");
      }
   }
}
