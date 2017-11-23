package com.example;

public class Main
{
   public static void main(String[] args)
   {
           int successfull = 200;
           int failed = 404;

           System.out.println(successfull + " is " + isSuccessful(successfull));
           System.out.println(failed + " is " + isSuccessful(failed));
   }

   private static boolean isSuccessful(final int statusCode)
   {
           return String.valueOf(statusCode).matches("^[1-2]\\d{2}$");
   }
}
