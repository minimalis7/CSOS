import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * Abstract class for the specific scheduling algorithms
 */
public abstract class SchedulingAlgorithm {
    protected List<Process> processes;
    protected ArrayList<Process> queue;
    
    /**
     * Constructor, 
     * representation of a scheduling algorithm, needs a list of processes
     * @param list a list of process objects
     */
    public SchedulingAlgorithm(List<Process> list) {
        super();
        processes = list;
    }
    
    /**
     * This method will implement the scheduling simulation
     */
    public abstract void run();
}