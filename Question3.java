/* AUTHOR: <Brian Henderson>
 * OBJECTIVE: < According to the Passenger_Weather_Combined.csv file, develop a program that calculates
 *              the average passenger activities according to different weather conditions and weekday
 *              and weekend in each station. Output results to "question3.csv" >
 *                Passenger_Weather_Combined Key:	
 *                 - Passenger get on ---- Column E (Use MEAN in the result)
 *                 - Passenger get off --- Column F (Use MEAN in the result)
 *                 - Weekday/Weekend ----- Column H
 *                 - Weather ------------- Column O ( good: >88, fair: 88-68, bad: <68 )
 * VERSION: 1 
*/
import java.io.*;
import java.util.*;

public class Question3 {

	public static void main(String[] args) {
	try{	
		System.out.println("Printing to question3.csv please wait");
		BufferedWriter writer = new BufferedWriter(new FileWriter("question3.csv"));
		writer.write("StopID,Passenger_On_Weekday_GoodWeather,Passenger_On_Weekday_FairWeather,Passenger_On_Weekday_BadWeather,"
				+ "Passenger_On_Weekend_GoodWeather,Passenger_On_Weekend_FairWeather,Passenger_On_Weekend_BadWeather,"
				+ "Passenger_off_Weekday_GoodWeather,Passenger_off_Weekday_FairWeather,Passenger_off_Weekday_BadWeather,"
				+ "Passenger_off_Weekend_GoodWeather,Passenger_off_Weekend_FairWeather,Passenger_off_Weekend_BadWeather");
		writer.newLine();
		
		
		ArrayList<Integer> stopIds = getStops();
		Iterator <Integer> it = stopIds.iterator();
		while(it.hasNext()){
			Integer val=it.next();
			writer.write(val + ",");
			writer.write(avgPassengerActivities(val, "weekday", "good", "on") + ",");
			writer.write(avgPassengerActivities(val, "weekday", "fair", "on") + ",");
			writer.write(avgPassengerActivities(val, "weekday", "bad", "on") + ",");
			writer.write(avgPassengerActivities(val, "weekend", "good", "on") + ",");
			writer.write(avgPassengerActivities(val, "weekend", "fair", "on") + ",");
			writer.write(avgPassengerActivities(val, "weekend", "bad", "on") + ",");
			writer.write(avgPassengerActivities(val, "weekday", "good", "off") + ",");
			writer.write(avgPassengerActivities(val, "weekday", "fair", "off") + ",");
			writer.write(avgPassengerActivities(val, "weekday", "bad", "off") + ",");
			writer.write(avgPassengerActivities(val, "weekend", "good", "off") + ",");
			writer.write(avgPassengerActivities(val, "weekend", "fair", "off") + ",");
			writer.write(avgPassengerActivities(val, "weekend", "off", "off"));
			writer.newLine();
		}

		writer.close();
		System.out.println("Finished writing to document");
	}
	catch(IOException e){
	}
		
	}
	private static String avgPassengerActivities(Integer id, String day, String weather, String on) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Integer result = 0;
		int onOffIndex = 4; 
		if(on.equals("off")){
			onOffIndex = 5;
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("Passenger_Weather_Combined.csv"));
			String line = reader.readLine();

			while(line != null){
				String[] att = line.split(",");
				if(Integer.parseInt(att[2]) == id){
					if(att[7].equals(day)){
						double temp = Double.parseDouble(att[14]);
						if((weather.equals("good")) && (temp>88)){
							list.add(Integer.parseInt(att[onOffIndex]));
						}
						if((weather.equals("fair")) && (temp>=68 && temp<=88)){
							list.add(Integer.parseInt(att[onOffIndex]));
						}
						if((weather.equals("bad")) && (temp<68)){
							list.add(Integer.parseInt(att[onOffIndex]));
						}
					}
				}
				line=reader.readLine();
			}
			reader.close();
		}
		catch(IOException e){
		}
		
		Iterator <Integer> it = list.iterator();
		while(it.hasNext()){
			Integer val=it.next();
			result = result + val;
		}
		
		if(list.size() == 0){
			return "0";
		} else{
			result = result/list.size();
			return result.toString();
		}
	}

	private static ArrayList<Integer> getStops() {
		Set<Integer> temp = new TreeSet<Integer>();
		ArrayList<Integer> res  = new ArrayList<Integer>();
		try{
			BufferedReader reader = new BufferedReader(new FileReader("Passenger_Weather_Combined.csv"));
			String line = reader.readLine();
			while(line != null){
				String[]att = line.split(",");
				temp.add(Integer.parseInt(att[2]));
				line = reader.readLine();
			}
			reader.close();
		}
		catch(IOException e){
		}
		Iterator <Integer> it = temp.iterator();
		while(it.hasNext()){
			Integer val=it.next();
			res.add(val);
		}
		return res;	
	}

}