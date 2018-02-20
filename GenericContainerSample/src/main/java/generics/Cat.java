package generics;

public class Cat implements Killable
{

   private boolean isAlive = true;

   public void kill()
   {
      this.isAlive = false;
   }

   public void meow()
   {
      if (isAlive)
      {
         System.out.println("Meow");
      }
      else
      {
         throw new DeadException("Cat is dead");
      }
   }
}
