import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY_CELL = ' ';

	private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private char currentPlayer = 'X';

	public TicTacToe() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = EMPTY_CELL;
			}
		}
	}

	public void printBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean makeMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			System.out.println("Invalid move, try again");
			return false;
		}
		if (board[row][col] != EMPTY_CELL) {
			System.out.println("Cell already taken, try again");
			return false;
		}
		board[row][col] = currentPlayer;
		if (checkWin()) {
			System.out.println(currentPlayer + " wins!");
			System.out.println("");
			return true;
		}
		if (checkDraw()) {
			System.out.println("Game over, it's a draw!");
			return true;
		}
		currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
		return false;
	}

	private boolean checkWin() {
		// row check
		for (int i = 0; i < BOARD_SIZE; i++) {
			boolean rowWin = false;
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == currentPlayer) {
					rowWin = true;
				} else {
					rowWin = false;
					break;
				}
			}

			if (rowWin) {
				return true;
			}
		}

		// column check
		for (int i = 0; i < BOARD_SIZE; i++) {
			boolean rowWin = false;
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[j][i] == currentPlayer) {
					rowWin = true;
				} else {
					rowWin = false;
					break;
				}
			}

			if (rowWin) {
				return true;
			}
		}

		// cross check
		if (board[1][1] == currentPlayer && (board[0][0] == currentPlayer && board[2][2] == currentPlayer)
				|| (board[0][2] == currentPlayer && board[2][0] == currentPlayer)) {
			return true;
		}

		return false;
	}

	private boolean checkDraw() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == EMPTY_CELL) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.printBoard();

		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.print("Enter row and column (0-2) for " + game.currentPlayer + ": ");
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				if (game.makeMove(row, col)) {
					game.printBoard();
					break;
				}
				game.printBoard();
			}
		}

		System.exit(0);
	}
}
