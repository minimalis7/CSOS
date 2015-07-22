import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution for Bonus Task 3
 * CSOS summer term 2015
 * WWU Münster
 * @author Daniel Grewe (395627)
 */
public class BonusTaskThree {
	public String inputCSV;
	
	/**
     * Constructor, 
     * referencing a path to the .csv-file via FileChooser.
     */
	public BonusTaskThree() {
		inputCSV = new String(FileChooser.pickAFile());
	}
	
	/**
     * Splits the .csv-file into an array of Strings and calls the simulation for each line
     * @param path String to the .csv-file
     */
	public void splitCSVandSimulate(String path) {
		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		
		System.out.println("--------------------------------------------------------------------------------");
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] process = line.split(cvsSplitBy);
				// ProcessID;ProcessingTime;ArrivalTime;IOBlockProbability;IOBlockTime
				System.out.println("ProcessID: " + process[0] + ", PTime: " + process[1] + ", ATime: " + process[2] + ", IOBlockP: " + process[3] + ", IOBlockT: " + process[4]);
				// cast string values into correct datatype
				String id = process[0];
				int ptime = Integer.parseInt(process[1]);
				int atime = Integer.parseInt(process[2]);
				float ioblockp = Float.parseFloat(process[3]);
				int ioblockt = Integer.parseInt(process[4]);
				// call simulations
				simFirstComeFirstServe(id, ptime, atime, ioblockp, ioblockt);
				simHighestResponseRatioNext(id, ptime, atime, ioblockp, ioblockt);
				simRoundRobin(id, ptime, atime, ioblockp, ioblockt);
				simShortestRemainingTime(id, ptime, atime, ioblockp, ioblockt);
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("- file successfully read!");
		System.out.println("--------------------------------------------------------------------------------");
	
	}
	
	/**
     * First come first serve
     * @param input String array
     */
	public void simFirstComeFirstServe(String id, int ptime, int atime, float ioblockp, int ioblockt){
		//TODO
	}
	
	/**
     * Highest response ratio next
     * @param input String array
     */
	public void simHighestResponseRatioNext(String id, int ptime, int atime, float ioblockp, int ioblockt){
		//TODO
	}
	
	/**
     * Round robin (10ms)
     * @param input String array
     */
	public void simRoundRobin(String id, int ptime, int atime, float ioblockp, int ioblockt){
		//TODO
	}
	
	/**
     * Shortest remaining time
     * @param input String array
     */
	public void simShortestRemainingTime(String id, int ptime, int atime, float ioblockp, int ioblockt){
		//TODO
	}
	
	public void run() {
		System.out.println("Starting BonusTaskThree...");
		System.out.println(inputCSV + " is the file of your choice!");
		splitCSVandSimulate(inputCSV);
	}

	public static void main(String[] args) {
		BonusTaskThree btt = new BonusTaskThree();
		btt.run();
	}

}
