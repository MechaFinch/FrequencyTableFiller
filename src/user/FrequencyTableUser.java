package user;

import java.util.ArrayList;
import java.util.Random;

import frequencytable.FrequencyTable;

/**
 * Main class with anything testing the frequency table
 * 
 * @author Alex Pickering
 */
public class FrequencyTableUser {
	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> data = new ArrayList<>();
		Random rand = new Random();
		
		//Generate 9x9 data
		for(int i = 0; i < 9; i++) {
			ArrayList<Double> internalData = new ArrayList<>();
			
			for(int j = 0; j < 9; j++) {
				internalData.add(((double) rand.nextInt(100)) / 10); //0-10
			}
			
			data.add(internalData);
		}
		
		//Find row sums
		for(ArrayList<Double> row : data) {
			double sum = 0;
			
			for(double d : row) {
				sum += d;
			}
			
			row.add(sum);
		}
		
		//Find column sums
		ArrayList<Double> sumColumn = new ArrayList<>();
		
		for(int col = 0; col < data.get(0).size(); col++) {
			double sum = 0;
			
			for(int row = 0; row < data.size(); row++) {
				sum += data.get(row).get(col);
			}
			
			sumColumn.add(sum);
		}
		
		data.add(sumColumn);
		
		FrequencyTable testTable = new FrequencyTable(data);
		
		System.out.println(testTable);
	}
}
