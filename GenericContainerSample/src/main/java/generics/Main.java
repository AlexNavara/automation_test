package generics;

public class Main
{

   public static void main(String[] args)
   {
      // Создание подопытных животных и коробок для них
      Cat cat = new Cat();
      Dog dog = new Dog();
      Box<Cat> boxForCat = new Box<>();
      Box<Dog> boxForDog = new Box<>();

      // Распределение животных по коробкам
      boxForCat.put(cat);
      boxForDog.put(dog);

      // Благодаря использованию обобщений (generics), этот код даже не скомпилируется
      // boxForCat.put(dog);
      // boxForDog.put(cat);

      // Запуск процесса
      boxForCat.process();
      boxForDog.process();

      // Извлечение животных из коробок
      cat = boxForCat.get();
      dog = boxForDog.get();

      // Опять же, благодаря обобщениям, соблюдается строгость типизации, и из коробки для собаки извлекается собака, а не кот, и наоборот
//      dog = boxForCat.get();
//      cat = boxForDog.get();

      // Проверка животного
      cat.meow();
      dog.bark();
   }

}
