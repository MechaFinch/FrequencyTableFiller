package frequencytable;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A Frequency Table for use in probability analytics
 * 
 * @author Alex Pickering
 */
public class FrequencyTable {
	//Contains the data
	ArrayList<ArrayList<Double>> matrix;
	
	//Contains the titles of the rows and columns respectively
	ArrayList<String> rowTitles,
					  colTitles;
	
	/**
	 * Creates a FrequencyTable
	 * 
	 * @param matrix The data to populate the table with (-1 = unknown)
	 * @param rowTitles The titles of each row
	 * @param colTitles The titles of each column
	 * @throws IllegalArgumentException If the table is too small
	 */
	public FrequencyTable(ArrayList<ArrayList<Double>> matrix, ArrayList<String> rowTitles, ArrayList<String> colTitles) throws IllegalArgumentException {
		this.matrix = matrix;
		this.rowTitles = rowTitles;
		this.colTitles = colTitles;
		
		//Normalize row lengths
		int rowLength = Collections.max(matrix, (rowA, rowB) -> rowA.size() - rowB.size()).size();
		
		if(rowLength < 2 || matrix.size() < 2) {
			throw new IllegalArgumentException("Table is too small. Minimum 2x2, current " + rowLength + "x" + matrix.size());
		}
		
		for(ArrayList<Double> row : matrix) {
			while(row.size() < rowLength) row.add(-1d);
		}
		
		//Normalize title lengths
		if(rowTitles.size() > matrix.size()) {
			rowTitles = (ArrayList<String>) rowTitles.subList(0, matrix.size());
		}
		
		while(rowTitles.size() < matrix.size()) rowTitles.add("");
		
		if(colTitles.size() > rowLength) {
			colTitles = (ArrayList<String>) colTitles.subList(0, rowLength);
		}
		
		while(colTitles.size() < rowLength) colTitles.add("");
	}
	
	/**
	 * Creates a FrequencyTable from data
	 * 
	 * @param matrix The data for the table
	 */
	public FrequencyTable(ArrayList<ArrayList<Double>> matrix) {
		this(matrix, new ArrayList<String>(), new ArrayList<String>());
	}
	
	/**
	 * Creates an empty FrequencyTable
	 * 
	 * @param rows The number of rows
	 * @param cols The number of columns
	 * @param rowTitles The titles of the rows
	 * @param colTitles The titles of the columns
	 * @return An empty FrequencyTable
	 */
	public static FrequencyTable createEmptyTable(int rows, int cols, ArrayList<String> rowTitles, ArrayList<String> colTitles) {
		ArrayList<ArrayList<Double>> mat = new ArrayList<>();
		
		for(int r = 0; r < rows; r++) {
			ArrayList<Double> row = new ArrayList<>();
			
			for(int c = 0; c < cols; c++) {
				row.add(-1d);
			}
			
			mat.add(row);
		}
		
		return new FrequencyTable(mat, rowTitles, colTitles);
	}
	
	/**
	 * Creates an empty FrequencyTable
	 * 
	 * @param rows The number of rows
	 * @param cols The number of columns
	 * @return An empty FrequencyTable
	 */
	public static FrequencyTable createEmptyTable(int rows, int cols) {
		return createEmptyTable(rows, cols, new ArrayList<String>(), new ArrayList<String>());
	}
	
	/**
	 * Determines if the values of the table are valid
	 * Unknowns are assumed correct
	 * 
	 * @return If the table is valid
	 */
	public boolean isValid() {
		//Test each row
		for(ArrayList<Double> row : matrix) {
			int sum = 0;
			
			for(int i = 0; i < row.size() - 1; i++) {
				sum += row.get(i);
			}
			
			if(sum != row.get(row.size() - 1)) return false;
		}
		
		//Test each column
		
		
		return true;
	}
	
	/**
	 * Solves this FrequencyTable, filling out any blanks it can
	 * 
	 * @return If all blanks were solved
	 */
	public boolean solve() {
		return true;
	}
}
