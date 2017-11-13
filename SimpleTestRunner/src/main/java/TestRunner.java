import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestRunner
{

   private Set<Class> classes;

   public TestRunner()
   {
      this.classes = new HashSet<>();
   }

   public TestRunner add(final Class clazz)
   {
      this.classes.add(clazz);
      return this;
   }

   public void run()
   {
      if (classes.isEmpty())
      {
         System.out.println("No test classes found");
         return;
      }

      for (Class clazz : classes)
      {
         System.out.printf("Running tests from class %s...%n", clazz.getSimpleName());
         for (Method method : clazz.getMethods())
         {
            if (method.isAnnotationPresent(Test.class))
            {
               try
               {
                  final Object instance = clazz.newInstance();
                  method.invoke(instance);
                  System.out.printf("\tTest '%s' passed%n", method.getName());
               }
               catch (InvocationTargetException e)
               {
                  System.out.printf("\tTest '%s' failed with message '%s'%n", method.getName(), e.getTargetException()
                        .getMessage());
               }
               catch (Exception e)
               {
                  e.printStackTrace();
                  System.out.printf("\tTest '%s' failed%n", method.getName());
               }
            }
         }
      }
   }

}
