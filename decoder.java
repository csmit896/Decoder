package test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class decoder {
	public static void main(String[] args) throws Exception {
        // Specify the path to your text file
        String filePath = "C:\\Users\\colem\\decoder\\src\\coding_qual_input.txt";
        // Create a File object
        File file = new File(filePath);
        // Create a 2D ArrayList to store the lines
        ArrayList<String> linesList = new ArrayList<>();
        try {
            // Create a Scanner to read from the file
            Scanner scanner = new Scanner(file);
            // Read the contents of the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                linesList.add(line);
            }
            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        }
        sortArrayList(linesList);
        System.out.println(getLastIndexString(createPyramid(getLastNumber(linesList),linesList)));
	}
    // Method to sort the ArrayList based on the number at the start of each line
    private static void sortArrayList(ArrayList<String> list) {
    	Collections.sort(list, new Comparator<String>() {
        @Override
        public int compare(String line1, String line2) {
        	// Extract the numbers from the start of each line
            int number1 = Integer.parseInt(line1.split(" ")[0]);
            int number2 = Integer.parseInt(line2.split(" ")[0]);

            // Compare the extracted numbers
            return Integer.compare(number1, number2);
            }
        });
    }
    private static int getLastNumber(ArrayList<String> list) {
        if (!list.isEmpty()) {
            String lastLine = list.get(list.size() - 1);
            return Integer.parseInt(lastLine.split(" ")[0]);
        } else {
            return -1; // Handle the case where the ArrayList is empty
        }
    }
    private static ArrayList<ArrayList<String>> createPyramid(int lastNumber, ArrayList<String> array) {
        ArrayList<ArrayList<String>> pyramid = new ArrayList<>();

        int currentNumber = 1;
        int row = 1;
        while (currentNumber <= lastNumber) {
            ArrayList<String> rowList = new ArrayList<>();
            for (int i = 0; i < row && currentNumber <= lastNumber; i++) {
                rowList.add(array.get(currentNumber-1).substring(array.get((currentNumber-1)).indexOf(" ")+1));
                currentNumber++;
            }
            pyramid.add(rowList);
            row++;
        }

        return pyramid;
    }
    private static String getLastIndexString(ArrayList<ArrayList<String>> pyramid) {
        StringBuilder result = new StringBuilder();

        for (ArrayList<String> row : pyramid) {
            int lastIndex = row.size() - 1;
            result.append(row.get(lastIndex)).append(" ");
        }

        return result.toString().trim();
    }
}
