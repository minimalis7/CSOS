import java.io.File;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class BonusTaskOne {
	
	public Picture input;
	
	/**
     * Constructor, 
     * referencing an input picture via FileChooser.
     */
	public BonusTaskOne() {
		input = new Picture(FileChooser.pickAFile());
	}
	
	/**
     * Reduces a colored image to black and white.
     * @param colored Picture object of the colored image
     * @return output Picture object of the black and white image
     */
	public Picture imgToBW(Picture colored){
		Picture output = null;
		
		int width = colored.getWidth();
		int height = colored.getHeight();
		
		output = new Picture(width, height);
		
		// iterate through each pixel
		for (int x = 0; x < width; x++) {  
			for (int y = 0; y < height; y++) {  
				Pixel pixcol = new Pixel(colored,x,y);
				Pixel pixbw = new Pixel(output,x,y);
				if (pixcol != null) {
					double pixcolval = pixcol.getAverage();
					// if the average rgb-value of the pixel is <= 128 (darker) 
					// set the color of the pixel to black, else to white
					if(pixcolval <= 128) {
						pixbw.updatePicture(100, 0, 0, 0);
					} else {
						pixbw.updatePicture(100, 255, 255, 255);
					}
				}  
			}  
		}  
		return output;
	}
	
	/**
     * Slices the dimensions of an imagee for the hack output format (512x256)
     * @param blackwhite Picture object of the black and white image
     * @return output Picture object of the sliced black and white image
     */
	public Picture imgSliceHack(Picture blackwhite) {
		Picture output = null;
		
		int width = 512;
		int height = 256;
		
		output = new Picture(width, height);
		
		// iterate through each pixel in the range of the hack picture
		for (int x = 0; x < width; x++) {  
			for (int y = 0; y < height; y++) {  
				Pixel pixbw = new Pixel(blackwhite,x,y);
				Pixel pixout = new Pixel(output,x,y);
				
				double pixbwval = pixbw.getAverage();
				if ((pixbw != null) && (pixbwval <= 128)) {
					pixout.updatePicture(100, 0, 0, 0);
				} else {
					pixout.updatePicture(100, 255, 255, 255);
				}
			}
		}
		return output;
	}
	
	/**
     * Due to the limitations of the CPU Emulator, check if the picture has more black or white pixels. 
     * Reverse the image color in order to reduce assembler-code.
     * @param blackwhite Picture object of the black and white image
     * @return <code>original</code>, if 
     *         <code>null</code>.
     */
	public Picture flipColor(Picture slicedblackwhite) {
		Picture outputori = null;
		Picture outputrev = null;
		
		int width = 512;
		int height = 256;
		
		int countwhite = 0;
		int countblack = 0;
		
		outputori = new Picture(width, height);
		outputrev = new Picture(width, height);
		
		// iterate through each pixel in the range of the hack picture
		for (int x = 0; x < width; x++) {  
			for (int y = 0; y < height; y++) {  
				Pixel pixin = new Pixel(slicedblackwhite,x,y);
				Pixel pixoutori = new Pixel(outputori,x,y);
				Pixel pixoutrev = new Pixel(outputrev,x,y);
				
				double pixinval = pixin.getAverage();
				if (pixinval == 0) {
					countblack++;
					pixoutori.updatePicture(100, 0, 0, 0);
					pixoutrev.updatePicture(100, 255, 255, 255);
				} else {
					countwhite++;
					pixoutori.updatePicture(100, 255, 255, 255);
					pixoutrev.updatePicture(100, 0, 0, 0);
				}
			}
		}
		if (countblack > countwhite) {
			return outputrev;
		} else {
			return outputori;
		}
		
	}
	
	/**
     * Writes assembler-code to display a black and white picture.
     * @param blackwhite Picture object of a black and white picture
     */
	public void imgToAsm(Picture hackblackwhite) {
		
		try {
			PrintWriter outputFile= new PrintWriter(getSaveLocation(), "UTF-8");
			
			int linecounter = 0;
			int emulimit = 32767;
			
			int addr = 16384;
			int step = 16;
			
			int width = 512 / step;
			int height = 256;
			
			
			System.out.println("Starting Loop!");
			System.out.println("--------------------------");
			outerloop:
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					String bin = "";
					StringBuilder binString = new StringBuilder();
					for(int k = 0; k < 16; k++){
						Pixel pix = new Pixel(hackblackwhite, x * 16+ k, y);
						
						if(pix.getBlue() == 0){
							binString.append("1");
						} else{
							binString.append("0");
						}
						
					}
					bin = binString.reverse().toString();
					int val = Integer.parseInt(bin, 2);	

					if(val == 0){
						bin = "";
					} else {
						System.out.println("Binary:" + bin);
						
						int chunk1 = val / 3;
						int chunk2 = val - chunk1 * 2;
						
						outputFile.println("@" + chunk1);
						linecounter++;
						outputFile.println("D=A");
						linecounter++;
						outputFile.println("@" + chunk1);
						linecounter++;
						outputFile.println("D=D+A");
						linecounter++;
						outputFile.println("@" + chunk2);
						linecounter++;
						outputFile.println("D=D+A");
						linecounter++;
						outputFile.println("@" + addr);
						linecounter++;
						outputFile.println("M=D");
						linecounter++;
						outputFile.flush();
						bin = ""; 
						
						// check if the lines can be written (emulator limit)
						if (linecounter > emulimit - 8) {
							System.out.println("Limit exceeded, breaking..");
							break outerloop;
						}
						
					}
					
					addr++;	
				}
				
			}
			System.out.println("--------------------------");
			System.out.println("Ending Loop.");
			System.out.println("Lines written:" + linecounter);
			
			outputFile.close();
			}catch(Exception e){
				//
			}
	}
	
	 /**
     * Returns a File reference with path for saving.
     * @return <code>File</code>, if options are approved, else
     *         <code>null</code>.
     */
	public File getSaveLocation(){
		JFrame parentFrame = new JFrame();
	
		JFileChooser saveFile = new JFileChooser();
		saveFile.setDialogTitle("Choose a path to save the assembler file!");
	
		int selection = saveFile.showSaveDialog(parentFrame);
	
		if(selection == JFileChooser.APPROVE_OPTION) {
			return saveFile.getSelectedFile();
		} else {
			return null;
		}
	
	}
	
	public void test() {
		String bin1 = "0000000000000000";
		int val1 = Integer.parseInt(bin1, 2);
		System.out.println("Value 0:" + val1);
		
		String bin2 = "0000000000000100";
		int val2 = Integer.parseInt(bin2, 2);
		System.out.println("Value 4:" + val2);
		
		String bin3 = "0000000000000011";
		int val3 = Integer.parseInt(bin3, 2);
		System.out.println("Value 3:" + val3);
		
		String bin4 = "0111111111111111";
		int val4 = Integer.parseInt(bin4, 2);
		System.out.println("Value 255:" + val4);
		val4 = val4 - 1;
		System.out.println("Value 255:" + val4);
	}
	
	public void run() {
		// show the original input
		input.show();
		// show the input in black and white
		imgToBW(input).show();
		// show the sliced input (black and white)
		imgSliceHack(imgToBW(input)).show();
		// show the optimized input (black and white) for imgToAsm
		flipColor(imgSliceHack(imgToBW(input))).show();
		// write image information to assembler
		imgToAsm(flipColor(imgSliceHack(imgToBW(input))));
		// write original image information to assembler (no optimisation)
		// imgToAsm(imgSliceHack(imgToBW(input)));
	}

	public static void main(String[] args) {
		BonusTaskOne bto = new BonusTaskOne();
		bto.run();
		// bto.test();
		
			   
	}
}