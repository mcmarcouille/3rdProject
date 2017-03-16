import java.util.Comparator;

/**
 * An implementation of the MinPriorityQueueADT interface. This implementation stores FileLine objects.
 * See MinPriorityQueueADT.java for a description of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {

    private Comparator<FileLine> cmp;
    private int maxSize;
    private int numItems;
    private FileLine[] queue;
    
    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		numItems = 0;
		queue = new FileLine[maxSize];

    }

    public FileLine removeMin() throws PriorityQueueEmptyException {
		if (numItems <= 0){
			throw new PriorityQueueEmptyException();
		}
		FileLine tempStore = queue[0];
		queue[0] = null;
		numItems--;


		return tempStore;
    }

    public void insert(FileLine fl) throws PriorityQueueFullException {
		// TODO
    }

    public boolean isEmpty() {
		return numItems == 0;
    }
}
