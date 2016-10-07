/* AUTHOR: <Brian Henderson>
 * OBJECTIVE: < According to the Passenger_Weather_Combined.csv file, develop a program that reads the C  
 *              column from the data set, and returns the unique stops in a CSV file named "stop.csv." >
 * VERSION: 1 
*/
import java.util.*;
import java.io.*;

public class Question1 {

	public static void main(String[] args){
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Passenger_Weather_Combined.csv"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("stop.csv"));
			String line = reader.readLine();
			Set<String> set = new TreeSet<String>();
			
			while(line != null){
				String[]att = line.split(",");
				set.add(att[2]);
				line = reader.readLine();
			}
				
			Iterator <String> it = set.iterator();
			while(it.hasNext()){
				String val=it.next();
				writer.write(val);
				writer.newLine();
			}
			
			writer.close();
			reader.close();
		}
		catch(IOException e){
			
		}
		
		
	}
	
}
