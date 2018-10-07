package gameOfLife;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int row = 60;
		final int col = 60;
		final int[][] matrix = new int[row][col];
		for (int x = 0; x < row; x++) {
			for (int y = 0; y < col; y++) {
				matrix[x][y] = (int) (Math.random());
			}
		}
		new GameOfLifeFrame(row, col, matrix);
	}

}
