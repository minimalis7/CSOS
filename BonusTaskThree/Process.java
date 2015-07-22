
public class Process {
	
	// ProcessID;ProcessingTime;ArrivalTime;IOBlockProbability;IOBlockTime
	private String processID;
	private int processingTime, arrivalTime, ioBlockTime;
	private float ioBlockProbability; 
    public int turnAroundTime;
    
    /**
     * Constructor, 
     * representation of a process in the .csv-file
     * @param pid the processID
     * @param pTime the processingTime
     * @param aTime the arrivalTime
     * @param iobp the ioBlockProbability
     * @param iobt the ioBlockTime 
     */
    public Process(String pid, int pTime, int aTime, float iobp, int iobt) {
        super();
        this.setProcessID(pid);
        this.setProcessingTime(pTime);
        this.setArrivalTime(aTime);
        this.setIoBlockProbability(iobp);
        this.setIoBlockTime(iobt);
    }

	/**
	 * Get the processID
	 * @return the processID
	 */
	public String getProcessID() {
		return processID;
	}

	/**
	 * Set the processID
	 * @param processID the processID to set
	 */
	public void setProcessID(String processID) {
		this.processID = processID;
	}

	/**
	 * Get the arrivalTime
	 * @return the arrivalTime
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Set the arrivalTime
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Get the processingTime
	 * @return the processingTime
	 */
	public int getProcessingTime() {
		return processingTime;
	}

	/**
	 * Set the processingTime
	 * @param processingTime the processingTime to set
	 */
	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}

	/**
	 * Get the ioBlockTime
	 * @return the ioBlockTime
	 */
	public int getIoBlockTime() {
		return ioBlockTime;
	}

	/**
	 * Set the ioBlockTime
	 * @param ioBlockTime the ioBlockTime to set
	 */
	public void setIoBlockTime(int ioBlockTime) {
		this.ioBlockTime = ioBlockTime;
	}

	/**
	 * Get the ioBlockProbability
	 * @return the ioBlockProbability
	 */
	public float getIoBlockProbability() {
		return ioBlockProbability;
	}

	/**
	 * Set the ioBlockProbability
	 * @param ioBlockProbability the ioBlockProbability to set
	 */
	public void setIoBlockProbability(float ioBlockProbability) {
		this.ioBlockProbability = ioBlockProbability;
	}

}
