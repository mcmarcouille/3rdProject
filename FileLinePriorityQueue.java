import java.util.Comparator;

/**
 * An implementation of the MinPriorityQueueADT interface. This implementation stores FileLine objects.
 * See MinPriorityQueueADT.java for a description of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
    // TODO
	int maxSize;
    private Comparator<FileLine> cmp;
    private FileLine[] queue;

    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		
		// TODO
		queue = new FileLine[initialSize];
    }
    
    private void reheapify(int index){
    	if(index == 1)
    		return;
    	if(cmp.compare(queue[index],queue[index/2])<0){
    		 FileLine temp = queue[index];
    	     queue[index] = queue[index/2];
    	     queue[index/2]= temp;
    	     reheapify(index/2);
    	}
    }

    public FileLine removeMin() throws PriorityQueueEmptyException {
		// TODO
    	if(0 == maxSize)
    		throw new PriorityQueueEmptyException();
    	FileLine temp = queue[1];
    	queue[1] = queue[maxSize];
    	reheapify(maxSize);
        return temp;
    }

    public void insert(FileLine fl) throws PriorityQueueFullException {
		// TODO
    	if(maxSize+1 == queue.length)
    		throw new PriorityQueueFullException();
    	queue[maxSize+1] = fl;
    	reheapify(maxSize+1);
    }

    public boolean isEmpty() {
		// TODO
    	return queue.length ==0;
    }
}
