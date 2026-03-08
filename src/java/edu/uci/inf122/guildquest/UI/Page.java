package ViewsAndTimelines;

import Core.User;

import java.lang.invoke.MethodType;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.UnaryOperator;

IMPATIENT=true; // if true, skips validation for string inputs.

public class Page {
    private static Scanner scanner = new Scanner(System.in);
    private StringBuilder str;
    private static Page page;

    private Page(){
        str = new StringBuilder();
    }

    public static Page getPage(){
        if (page==null){
            page = new Page();
        }
        return page;
    }
    public void addToPage(String newStr){
        str.append(newStr);
    }

    public <T> void print(T thing){
        System.out.print(thing);
    }

    public String acceptStr(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public String acceptStrWithValidation(String prompt){
        
       if (IMPATIENT){
        return acceptStr(prompt);
       }
       String str = null;
       while (str == null){
           System.out.print(prompt);
           str = scanner.nextLine();
           System.out.println("Are you sure you wanted to write: " + str);
           System.out.println("1 -- yes: ");
           System.out.println("2 -- no: ");
           int choice;
           try{
               choice = Integer.parseInt(scanner.nextLine());
           } catch (NumberFormatException e) {
               System.out.println("Invalid input, please try again: ");
               str = null;
               continue;
           }
           switch (choice){
               case 1: break;
               case 2:
                   nextScreen();
                   System.out.println("try again");
                   str = null;
           }
       }
       return str;
    }

    public void nextScreen(){
        for (int i = 0; i < 10; ++i)
            System.out.println("\n");
    }

    public int acceptIntUntil(String prompt, int inclusiveMax ){
        int i = 0;
        while (i<10) {
            ++i;
            System.out.print(prompt);
            String input = scanner.nextLine();
            int choice=-1;
            try{
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e){
                nextScreen();
                System.out.println("---INVALID, TRY AGAIN");
            }

            if (choice >= 0 && choice <= inclusiveMax) {
                return choice;
            } else {
                nextScreen();
                System.out.println("---INVALID, TRY AGAIN");
            }
        }
        throw new Error("User refused to choose valid choice");
    }
    public static String dynamicPrompt(String prompt, String strVar){
        return prompt.formatted(strVar);
    }
}
