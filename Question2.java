/* AUTHOR: <Brian Henderson>
 * OBJECTIVE: < According to the Passenger_Weather_Combined.csv file, develop a program that calculates 
 *              the unique trips in the data set, and output to a CSV file "trip.csv." A unique departure 
 *              date (J column) and departure time (K column) determine a  unique trip. >
 * VERSION: 1 
*/
import java.io.*;
import java.util.*;

public class Question2 {
	public static void main(String[] args){
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("Passenger_Weather_Combined.csv"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("trip.csv"));
			String line = reader.readLine();
			Set<String> uniqueStops = new TreeSet<String>();

			while(line != null){
				String[]att = line.split(",");
				uniqueStops.add(att[9] + "," + att[10]);
				line = reader.readLine();
			}
			
			Iterator <String> it = uniqueStops.iterator();
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
