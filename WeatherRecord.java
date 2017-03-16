import java.util.Comparator;
import java.util.ArrayList;

/**
 * The WeatherRecord class is the child class of Record to be used when merging weather data. Station and Date
 * store the station and date associated with each weather reading that this object stores.
 * l stores the weather readings, in the same order as the files from which they came are indexed.
 */
public class WeatherRecord extends Record{
	private ArrayList<Double> readings;
	private int station;
	int date;
	FileLine li;
	
	/**
	 * Constructs a new WeatherRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public WeatherRecord(int numFiles) {
		super(numFiles);
		clear();
    }
	
	/**
	 * This comparator should first compare the stations associated with the given FileLines. If 
	 * they are the same, then the dates should be compared. 
	 */
    private class WeatherLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			String[] fileLine1 = l1.getString().split(",");
			String[] fileLine2 = l2.getString().split(",");
			
			Integer firstStation = Integer.parseInt(fileLine1[0]);
			Integer secondStation = Integer.parseInt(fileLine2[0]);
			
			if(firstStation.equals(secondStation)){
				Integer firstDate = Integer.parseInt(fileLine1[1]);
				Integer secondDate = Integer.parseInt(fileLine2[1]);
				
				return firstDate.compareTo(secondDate);
			}
			else{
				return firstStation.compareTo(secondStation);
			}
			
		}
		
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the WeatherLineComparator
	 * class.
	 */
    public Comparator<FileLine> getComparator() {
		return new WeatherLineComparator();
    }
	
	/**
	 * This method should fill each entry in the data structure containing
	 * the readings with Double.MIN_VALUE
	 */
    public void clear() {
    	try{
    		for (int i=0; i<readings.size(); i++)
    			readings.set(i, Double.MIN_VALUE);
    	}catch(NullPointerException e){
    		System.out.println("Null Pointer!");
    	}
    }

	/**
	 * This method should parse the String associated with the given FileLine to get the station, date, and reading
	 * contained therein. Then, in the data structure holding each reading, the entry with index equal to the parameter 
	 * FileLine's index should be set to the value of the reading. Also, so that
	 * this method will handle merging when this WeatherRecord is empty, the station and date associated with this
	 * WeatherRecord should be set to the station and date values which were similarly parsed.
	 */
    public void join(FileLine li) {
    	//create an array of the strings in FileLine li
    	String[] line = li.getString().split(",");

    	if (this.isCleared()) {
    		this.li = li;
    		this.station = Integer.parseInt(line[0]);
    		this.date = Integer.parseInt(line[1]);
    		for (int i = 2; i < line.length; i++){
    			readings.add(Double.parseDouble(line[i]));

    			//if I took the data from the top of the file containing FileLine li, iterate li's iterator
    			li.getFileIterator().next();
    		}
    	}
    	else{
    		if (Integer.parseInt(li.getString().split(",")[0]) == this.station && Integer.parseInt(li.getString().split(",")[1]) == this.date){
    			for (int i = 2; i < line.length; i++){
    				readings.add(Double.parseDouble(line[i]));

    				li.getFileIterator().next();
    			}
    		}

    		else{
    			readings.add(null);
    		}
    	}
    }

    private boolean isCleared() {
    	return (station == 0 && date == 0 && readings.size() == 0);
    }

    
	/**
	 * See the assignment description and example runs for the exact output format.
	 */
    public String toString() {
		// TODO
		
		return null;
    }
}
