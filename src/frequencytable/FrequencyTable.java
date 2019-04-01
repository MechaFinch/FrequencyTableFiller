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
		
		if(rowLength < 1 || matrix.size() < 1) {
			throw new IllegalArgumentException("Table is too small. Minimum 1x1, current " + rowLength + "x" + matrix.size());
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
	 * Determines if there are unknowns in the table
	 * 
	 * @return Whether or not the table has unkowns
	 */
	public boolean hasUnknowns() {
		for(ArrayList<Double> row : matrix) {
			for(double d : row) {
				if(d < 0) return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Finds a solveable point in the table
	 * 
	 * @return The coordinates of a point to solve in the form [row, col]
	 */
	private int[] findSolveablePoint() {
		int numRows = matrix.size(),
			numCols = matrix.get(0).size();
		
		//Find a point that can be solved by its row
		for(int row = 0; row < numRows; row++) {
			int blanks = 0;
			
			for(Double d : matrix.get(row)) {
				if(d < 0) blanks++;
			}
			
			//We found a point!
			if(blanks == 1) {
				return new int[] {
					row,
					matrix.get(row).indexOf(-1d)
				};
			}
		}
		
		//No row found, try columns
		//TODO
		
		return null; //Could not find any solveable blanks
	}
	
	/**
	 * Solves this FrequencyTable, filling out any blanks it can
	 * 
	 * @return If all blanks were solved
	 */
	public boolean solve() {
		while(hasUnknowns()) {
			//Find a solveable blank
			int[] blankPoint = findSolveablePoint();
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		for(ArrayList<Double> row : matrix) {
			s += ",\n[";
			
			for(int i = 0; i < row.size(); i++) {
				String str = Double.toString(row.get(i));
				str = str.substring(0, str.length() > 5 ? 5 : str.length());
				
				s += (i == 0 ? "" : ", ") + str;
			}
			
			s += "]";
		}
		
		return s.substring(2);
	}
}
