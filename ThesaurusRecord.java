import java.util.Comparator;
import java.util.ArrayList;

/**
 * The ThesaurusRecord class is the child class of Record to be used when merging thesaurus data.
 * The word field is the entry in the thesaurus, syn is the list of all associated synonyms.
 */

public class ThesaurusRecord extends Record{
    private String word; //word to compare synonyms 
    private ArrayList<String> synonyms; 
    
    
    
   
	/**
	 * Constructs a new ThesaurusRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public ThesaurusRecord(int numFiles) {
	super(numFiles);
	clear();
    }

    /**
	 * This Comparator should simply behave like the default (lexicographic) compareTo() method
	 * for Strings, applied to the portions of the FileLines' Strings up to the ":"
	 * The getComparator() method of the ThesaurusRecord class will simply return an
	 * instance of this class.
	 */
	private class ThesaurusLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			String l1words = l1.getString().split(":")[0];
			String l2words = l2.getString().split(":")[0];
			
			
			return l1words.compareTo(l2words);
		}
		
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the ThesaurusLineComparator class.
	 */
    public Comparator<FileLine> getComparator() {
		return new ThesaurusLineComparator();
    }
	
	/**
	 * This method should (1) set the word to null and (2) empty the list of synonyms.
	 */
    public void clear() {
		word = null;
		try{
			synonyms.clear();
		}catch(NullPointerException ex){
			System.out.print("Null Pointer!");
		}
    }
	
    
    
	/**
	 * This method should parse the list of synonyms contained in the given FileLine and insert any
	 * which are not already found in this ThesaurusRecord's list of synonyms.
	 */
    public void join(FileLine w) {
    	if(this.isCleared()){
    		word = w.getString().split(":")[0];
    		String fileLineWords = w.getString().split(":")[1];
    		String[] newSyns = fileLineWords.split(",");
    		for(int i = 0; i < newSyns.length; i++){
    			synonyms.add(newSyns[i]);
    		}
    	}
    	else{
    		String fileLineWords = w.getString();
    		fileLineWords = fileLineWords.split(":")[1];
    		String[] newSyns = fileLineWords.split(",");

    		for (int i = 0; i < newSyns.length; i++) {
    			for (String s : synonyms) {
    				if (newSyns[i].compareTo(s) == 0) break;
    				else if (newSyns[i].compareTo(s) > 0) continue;
    				else if (newSyns[i].compareTo(s) < 0) synonyms.add(synonyms.indexOf(s), newSyns[i]);
    			}
    		}
    	}
    }
    
    private boolean isCleared() {
    	return (word == null && synonyms.size() == 0);
    }
	
	/**
	 * See the assignment description and example runs for the exact output format.
	 */
    public String toString() {
		String output = "";
		output += word + ":";
		for (String s : synonyms)
			output += s + ",";
		
		return output;
	}
}
