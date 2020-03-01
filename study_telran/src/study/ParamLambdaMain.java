package study;

import org.w3c.dom.Text;

@FunctionalInterface
interface MyInterface {
    String reverse(String n);
}




public class ParamLambdaMain {

    public static void main( String[] args ) {

        MyInterface myInterface = (str) -> {
            String result = "";
            for (int i = str.length()-1; i >= 0 ; i--)
            result += str.charAt(i);
            return result;
        };

        System.out.println("Lambda reversed = " + myInterface.reverse("Lambda"));
        
        
        // ф-я без запуска интерфейса
        reverseWord("persik");
        
    }

    
    
    
    
    // Reverse without Interface
    public static void reverseWord( String word ) {
    
            String result = "";
            for (int i = word.length()-1; i >= 0 ; i--)
            result += word.charAt(i);
        
        System.out.println("Lambda reversed = " + result);
    }
    
    
    
    
    
    
}