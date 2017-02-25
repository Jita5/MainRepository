package core;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LengthComparator implements Comparator<String> {

	public int compare(String o1, String o2)
    {
		WordProcessor wp1 = Config.GetWordProcessor(o1);
		WordProcessor wp2 = Config.GetWordProcessor(o2);
		
        if(wp1.getLength() > wp2.getLength())
            return -1;

        if(wp2.getLength() > wp1.getLength())
            return 1;

        return 0;
    }

    public static void main(String[] args) {
        ArrayList<String> Words = new ArrayList<String>(Arrays.asList("CAR",
                "FISHING",
                "GOLD",
                "ALCOHOL",
                "PARACHUTE",
                "UNIFORM",
                "SILVER",
                "TRASH",
                "RATING",
                "LOBSTER",
                "KITE",
                "NEVER",
                "HADES",
                "MAROON",
                "CAPITOL",
                "LINK",
                "VALENTINE",
                "SAINT",
                "OCTOBER",
                "POT",
                "BEND"));
        
        Collections.sort(Words, new LengthComparator());
        
        System.out.println(Words);
    }
}