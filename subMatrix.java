import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * Date: 11/10/19
 * Author: David Besonen
 * Finds the largest square sub-matrix of 1's in a larger matrix consisting of 1's and 0's
 */

public class subMatrix {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> answer = new ArrayList<Integer>();
		String input;

		// Reads in information from system input
		while ((input = scan.readLine()) != null) {
			boolean onePresent = false;
			boolean approved = false;
			String line = input;
			String[] tok = line.split(" ");
			int[][] matrix;
			ArrayList<Integer> result = new ArrayList<Integer>();

			// Finds the beginning of one matrice's information
			if (tok.length == 2) {
				matrix = new int[Integer.parseInt(tok[0])][Integer.parseInt(tok[1])];
				int row = Integer.parseInt(tok[0]);
				int col = Integer.parseInt(tok[1]);

				// Fills matrix[][] with input values
				for (int r = 0; r < row; r++) {
					line = scan.readLine();
					tok = line.split(" ");

					// Allows legitimate input of '0 0'
					if (tok.length > 1 && tok[0].equals("0") && tok[1].equals("0")) {
						approved = true;
					}

					for (int c = 0; c < col; c++) {
						matrix[r][c] = Integer.parseInt(tok[c]);

						// Checks if the matrix has a 1
						if (Integer.parseInt(tok[c]) == 1) {
							onePresent = true;
						}
					}
				}

				if (onePresent) {

					// Finds largest sub-matrix of 1's by moving down and right through matrix
					for (int r = 0; r < row; r++) {
						for (int c = 0; c < col; c++) {
							if (matrix[r][c] == 1) {
								int i = 1;
								while (r + i < row && c + i < col && matrix[r + i][c] == 1 && matrix[r][c + i] == 1
										&& matrix[r + i][c + i] == 1) {
									i++;
								}
								result.add(i);
							}
						}
					}
				}

				// If no 1's are present, adds '0' to result set
				else {
					result.add(0);
				}

				// If we are not at the end of the input or if there is a legitimate '0 0' entry
				if ((line.equals("0 0 ") == false && line.equals("0 0") == false) || approved == true) {
					int max = 0;

					for (int i = 0; i < result.size(); i++) {
						if (result.get(i) > max) {
							max = result.get(i);
						}
					}
					answer.add(max);
				}

				else if (approved == false) {
					break;
				}
			}
		}
		
		// Outputs result set
		for (int i = 0; i < answer.size(); i++) {
			System.out.println(answer.get(i));
		}
		scan.close();
	}
}
