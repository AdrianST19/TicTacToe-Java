import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		char[][] board = {{' ', ' ', ' '},
				  {' ', ' ', ' '}, 
				  {' ', ' ', ' '}};
		
		printBoard(board);
		
		while (true) {
			playerTurn(board, scanner);
			if (isGameFinished(board)){ // it manages to get out of this while loop if the player wins
				break;
			}
			printBoard(board);
			
			computerTurn(board);
			if (isGameFinished(board)){ // or if the computer wins, also if there are no valid moves left, the game will end in a tie
				break;
			}
			printBoard(board);
		}
		scanner.close();
	}
	
	private static void printBoard(char[][] board) {
		System.out.println(board[0][0] + "|" +  board[0][1] + "|" +  board[0][2] ); // prints the row of the board
		System.out.println("-+-+-");
		System.out.println(board[1][0] + "|" +  board[1][1] + "|" +  board[1][2] );
		System.out.println("-+-+-");
		System.out.println(board[2][0] + "|" +  board[2][1] + "|" +  board[2][2] );
	}
	
		private static void playerTurn(char[][] board, Scanner scanner) {
		String userInput;
		while (true) {
			System.out.println("Where would you like to play? (1-9)");
			userInput = scanner.nextLine();
			if (isValidMove(board, userInput)){
				break;
			} else {
				System.out.println(userInput + " is not a valid move.");
			}
		}
		playerChoice(board, userInput, 'X');
	}
	
	private static void playerChoice(char[][] board, String position, char symbol) {
		// if the player chooses a number from 1 to 9, the element of the board will be marked with an 'X'	
		if(position.equals("1")) board[0][0] = symbol;
		if(position.equals("2")) board[0][1] = symbol;	
		if(position.equals("3")) board[0][2] = symbol;	
		if(position.equals("4")) board[1][0] = symbol;	
		if(position.equals("5")) board[1][1] = symbol;	
		if(position.equals("6")) board[1][2] = symbol;	
		if(position.equals("7")) board[2][0] = symbol;	
		if(position.equals("8")) board[2][1] = symbol;	
		if(position.equals("9")) board[2][2] = symbol;	
	}
	
	private static boolean isValidMove(char[][] board, String position) {
		// if the element of the board is empty, then it will return true and if none of the elements are empty, it will return false and it will be a tie
		if(position.equals("1")) return (board[0][0] == ' ');
		if(position.equals("2")) return (board[0][1] == ' ');
		if(position.equals("3")) return (board[0][2] == ' ');
		if(position.equals("4")) return (board[1][0] == ' ');
		if(position.equals("5")) return (board[1][1] == ' ');
		if(position.equals("6")) return (board[1][2] == ' ');
		if(position.equals("7")) return (board[2][0] == ' ');
		if(position.equals("8")) return (board[2][1] == ' ');
		if(position.equals("9")) return (board[2][2] == ' ');
		else return false;

	}
	
	private static void computerTurn(char[][] board) {
		Random rand = new Random(); // the computer plays randomly, so it will choose any of the empty elements in the board
		int computerMove;
		while (true) {
			computerMove = rand.nextInt(9) + 1;
			if (isValidMove(board, Integer.toString(computerMove))) {
				break;
			}
		}
		System.out.println("Computer chose " + computerMove);
		playerChoice(board, Integer.toString(computerMove), 'O');
	}

	private static boolean hasContestantWon(char[][] board, char symbol) {
		// if the same row, column or diagonal is marked only with 'X' or '0', then we have a winner
		if ((board[0][0] == symbol && board [0][1] == symbol && board [0][2] == symbol) ||
		    (board[1][0] == symbol && board [1][1] == symbol && board [1][2] == symbol) ||
		    (board[2][0] == symbol && board [2][1] == symbol && board [2][2] == symbol) ||
			
		    (board[0][0] == symbol && board [1][0] == symbol && board [2][0] == symbol) ||
		    (board[0][1] == symbol && board [1][1] == symbol && board [2][1] == symbol) ||
	       	    (board[0][2] == symbol && board [1][2] == symbol && board [2][2] == symbol) ||
			
		    (board[0][0] == symbol && board [1][1] == symbol && board [2][2] == symbol) ||
		    (board[0][2] == symbol && board [1][1] == symbol && board [2][0] == symbol) ) {
			return true;
		}
		return false;
	}
	
	private static boolean isGameFinished(char[][] board) {
		
		if (hasContestantWon(board, 'X')) { // if the function returns true and the criterias which made this function true are met and are equal to 'X', the player wins
			printBoard(board);
			System.out.println("You won the game!");
			return true;
		}
		
		if (hasContestantWon(board, 'O')) { // the same with the computer, if the function returns true and the met criterias are equal to '0', the computer wins
			printBoard(board);
			System.out.println("Computer won the game!");
			return true;
		}
		
		int i=0;
		while(i<board.length) {
			int j=0;
			while(j<board[i].length) {
				if (board[i][j] == ' ') return false; // if 'hasContestantWon' returns false and there are still empty elements on the board it will return false, the game continues
				j++;
			}
			i++;
		}
		printBoard(board);
		System.out.println("The game ended in a tie!"); // if there are no elements left empty on the board and the function 'hasContestantWon' returns false, then it's a tie
		return true;
	}

}
  
