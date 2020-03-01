package study;

import java.util.ArrayList;
import java.util.List;

public class StreamMain {

    static List<String> places = new ArrayList<> ();

    // preparing our data
    public static List getPlaces(){

        places.add("Nepal, Kathmandu");
        places.add("India, Delhi");
        places.add("USA, New York");
        places.add("Nepal, Pokhara");
        places.add("Africa, Nigeria");

        return places;
    }
    
    
    public static void main( String[] args ) {

        List<String> myPlaces = getPlaces();
        System.out.println("Places from Nepal:");
        
        // Filter places from Nepal
        myPlaces.stream()
                .filter((p) -> p.startsWith("Nepal"))
                .forEach((p) -> System.out.println(p));
    }
    
    
    
    
    

}