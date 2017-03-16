import java.util.*;

public class SortExample {
    public static void main(String[] args) {

	List<String> l = new ArrayList<String>();
	l.add("aaa");
	l.add("222");
	l.add("111");
	
	System.out.println("Before: ");
	printList(l);

	// Sort the list in alphabetical order
	Collections.sort(l);

	System.out.println("After: ");
	printList(l);
    }
    
    private static void printList(List<String> l) {
	for (int i = 0; i < l.size(); i++) {
	    System.out.println(l.get(i));
	}
	System.out.println();
    }
}
