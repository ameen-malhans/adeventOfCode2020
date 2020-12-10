import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Day5Java {
	private static SortedSet<Integer> seatIDSet = new TreeSet<Integer>();
	
	public static void main(String[] args) {
		try {
			File myObj = new File("/Users/ameengunny/Documents/AdventOfCode/new 1.txt");
			
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String boardingPass = myReader.nextLine();
				char[] boardingPassChar = boardingPass.toCharArray();
				
				seatIDSet.add(getSeatId(boardingPassChar));
			}
			myReader.close();
			
			System.out.println("************************************************************");
			System.out.println("What is the highest seat ID on a boarding pass? " + seatIDSet.last());
			System.out.println("************************************************************");
			
			System.out.println("");
			
			int nextSeatId = seatIDSet.first();
			for (Integer seatId : seatIDSet) {
				if(seatId == nextSeatId) {
					nextSeatId = seatId + 1;
				}else {
					System.out.println("************************************");
					System.out.println("What is the ID of your seat?"  + " " +nextSeatId);
					System.out.println("************************************");
					break;
					
				}
			}
			System.out.println("");
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		}
	}
	
	private static Integer getSeatId(char[] boardingPassChar) {
		int rowStartfirstIndex = 0;
		int rowlastIndex = 127;
		int columnStartIndex = 0;
		int columnLastIndex = 7;
		int row = 0;
		int column = 0;
		for (int i = 0; i < boardingPassChar.length; i++) {
			Character letterReceived = boardingPassChar[i];
			if (letterReceived.equals('F')) {
				rowlastIndex = (int) Math.round( (rowStartfirstIndex + rowlastIndex) / 2);
			} else if (letterReceived.equals('B')) {
				rowStartfirstIndex = (int) Math.round((rowStartfirstIndex + rowlastIndex) / 2) + 1;
			} else if (letterReceived.equals('L')) {
				columnLastIndex = (int) Math.round((columnStartIndex + columnLastIndex) / 2);
			} else if (letterReceived.equals('R')) {
				columnStartIndex = (int) Math.round((columnStartIndex + columnLastIndex) / 2) + 1;
			}
		}
		row = rowStartfirstIndex;
		column = columnStartIndex;

		return (int) (row * 8 + column);
	}
	
}
