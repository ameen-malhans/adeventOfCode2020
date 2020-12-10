import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Day5 {

	private static Set<Integer> seatIDSet = new TreeSet<Integer>();

	public static void main(String[] args) {

		try {

			File myObj = new File("/Users/ameengunny/Documents/AdventOfCode/new 1.txt");// "ameengunny/Documents/AdventOfCode/new
																						// 1.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String boardingPass = myReader.nextLine();
				char[] boardingPassChar = boardingPass.toCharArray();
				calculateSeatId(boardingPassChar, seatIDSet);
			}
			myReader.close();
			final Iterator itr = seatIDSet.iterator();
			Object lastElement = itr.next();
			while (itr.hasNext()) {
				lastElement = itr.next();
			}

			System.out.println("seatIDSet " + lastElement);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	private static void calculateSeatId(char[] boardingPassChar, Set<Integer> seatIDSet2) {
		int firstIndex = 0;
		int lastIndex = 127;
		int columnStartIndex = 0;
		int columnLastIndex = 7;
		int row = 0;
		int column = 0;
		for (int i = 0; i < boardingPassChar.length; i++) {
			Character letterReceived = boardingPassChar[i];
			if (letterReceived.equals('F')) {
				firstIndex = firstIndex;
				lastIndex = (int) Math.round(firstIndex + lastIndex / 2);

			} else if (letterReceived.equals('B')) {
				firstIndex = (int) Math.round(firstIndex + lastIndex / 2) + 1;
				lastIndex = lastIndex;
			} else if (letterReceived.equals('R')) {
				columnStartIndex = (int) Math.round(firstIndex + lastIndex / 2) + 1;
				columnLastIndex = columnLastIndex;
			} else if (letterReceived.equals('L')) {
				columnStartIndex = columnStartIndex;
				columnLastIndex = (int) Math.round(firstIndex + lastIndex / 2);
			}
			if ((i == 6) && letterReceived.equals('F')) {
				row = firstIndex;
			} else if ((i == 6) && letterReceived.equals('B')) {
				row = lastIndex;
			}
			if ((i == 9) && letterReceived.equals('R')) {
				column = columnLastIndex;
			} else if ((i == 9) && letterReceived.equals('L')) {
				column = columnStartIndex;
			}

		}
		int seatId = (int) (row * 8 + column);
		seatIDSet.add(seatId);

	}
}
