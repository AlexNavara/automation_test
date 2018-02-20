package generics;

// Коробка обобщена типом Т. Во время компиляции будет выполнена проверка соответствия типов.
// Кроме этого, тип Т ограничен интерфейсом Killable. Благодаря этому, во-первых, гарантируется что во время выполнения
// будут передаваться только объекты, реализующие этот интерфейс, во-вторых - мы можем безопасно использовать методы
// этого интерфейса внутри класса.
public class Box<T extends Killable>
{
   private T killable;

   public void put(final T killable)
   {
      this.killable = killable;
   }

   public T get()
   {
      return killable;
   }

   public void process()
   {
      if (Math.random() > 0.5)
      {
         killable.kill();
      }
   }
}
