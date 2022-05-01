import java.util.Scanner;

/**
 * comprehensively and automatically test all components of the entire system
 * @author jessica oathoudt
 *
 */
public class TestDriver 
{
	/**
	 * driver
	 * @param args - string
	 */
	public static void main(String [] args)
	{
		//new instance of TestDriver
		TestDriver test = new TestDriver();
		//calls emptyBoard method
		test.testEmptyBoard();
		//calls testStylusPlacement method
		test.testStylusPlacement();
		//calls testOneMove method
		test.testDownMove();
		//calls noBoardStylusPlacement method
		test.noBoardStylusPlacementTest();
		//calls noStylusOnBoard method
		test.noStylusBoardTest();
		//calls the testBoard method
		test.testBoard();
		//calls movingOutofBounds method
		test.movingOutOfBoundsTest();
		//calls stylusAsBoardableElem method
		test.stylusAsBoardableElement();
	}

	/**
	 * Automatically runs the stylus through the entire board
	 * making all the cells visible and " "
	 * has an expected outcome and an actual outcome - should be the same
	 */
	public void testEmptyBoard() 
	{
	//go through board and make it all on
	//should return an empty board (nothing)
		System.out.println("******Empty Board Test******\n");
		System.out.println("Expected Outcome:");
		System.out.print("\n         *\n\n\n\n\n\n\n\n\n\n\n");
		// new board with height and width of 10
		Board game = new Board(10, 10);
		//new stylus on board at 0,0
		Stylus stylus = new Stylus(game);
		
		//while the pen is moving right on the board
		do 
		{
			//move all the way down
			while(game.move(Direction.DOWN, stylus));
			//move one right
			game.move(Direction.RIGHT, stylus);
			//move all the way right
			while(game.move(Direction.UP, stylus));
		}
		//while the stylus can move right
		while(game.move(Direction.RIGHT, stylus));	
		//print
		System.out.println("Actual Outcome:");
		game.printBoard();	
	}
	
	/**
	 * tests the stylus placement on the board
	 * if it is placed outside the board bounds it should be incorrect
	 * if it is placed inside the board bounds it is correct
	 */
	public void testStylusPlacement() 
	{
		System.out.println("\n******Stylus Placement Test******\n");
		//new board of size 10,10
		Board game = new Board(10, 10);
		//new stylus placed on game
		Stylus stylus = new Stylus(game);
		//this should be false
		boolean checker = game.placeElement(stylus, 11, 11);
		//what we want at the end
		System.out.println("Expected Outcome: ");
		System.out.println("Correct, stylus was not placed at 11,11 on a board of size 10,10");
		System.out.println("true");
		System.out.println("Stylus was placed at 9,9 on a board of size 10,10\n");
		System.out.println("Actual Outcome:");
		//this should not happen
		if(checker) 
		{
			//should not print
			System.out.println("Error, stylus was placed at 11,11 on a board of size 10,10");
		}
		//should happen
		if(!checker) 
		{
			System.out.println("Correct, stylus was not placed at 11,11 on a board of size 10,10");
		}
		//this should be true
		checker = game.placeElement(stylus, 9, 9);
		//should not happen
		if(!checker) 
		{
			//should not print
			System.out.println("Stylus Placement Error (Should have been placed)");
		}
		//if true
		else 
		{
			//print true
			System.out.println(checker);
			System.out.println("Stylus was placed at 9,9 on a board of size 10,10\n");
		}
	}
	/**
	 * tests a single move to make sure it works
	 * expected and actual outcome should be the same
	 */
	public void testDownMove() 
	{
		System.out.println("******Test Down Move Test******\n");
		//making a board
		Board board = new Board(10,10);
		//pen on that board
		Stylus pen = new Stylus(board);
		//should be true
		boolean check = board.move(Direction.DOWN, pen);
		//if not true
		if(!check) 
		{
			System.out.println("Single Move Error (the pen did not move)");
		}
		//what we want
		System.out.println("Expected Outcome: \n");
		System.out.print(" #########\n*#########\n##########\n##########\n##########\n##########\n##########\n"
				+ "##########\n##########\n##########\n");
		//what i got
		System.out.println("\nActual Outcome: ");
		board.printBoard();			
	}
	
	/**
	 * tests placing the stylus on a board with no dimensions
	 * tests placing the stylus on a board with dimensions
	 * expected and actual outcome should be the same
	 * **/
	public void noBoardStylusPlacementTest() 
	{
		System.out.println("\n******No Board Stylus Placement Test******\n");
		System.out.println("Expected Outcome: ");
		System.out.println("Correct, pen was placed on the valid board");
		System.out.println("Correct, pen was not placed because board was not made with invalid dimensions");
		System.out.println("\nActual Outcome: ");
		try 
		{
			Board a = new Board(10,10);
			Stylus penOn = new Stylus(a);
			System.out.println("Correct, pen was placed on the valid board");
		} catch(IllegalArgumentException exp) 
		{
			System.out.println("Error, pen should have been placed on valid board");
		}
		try 
		{
			Board b = new Board(0,0);
			Stylus penOff = new Stylus(b);
			System.out.println("Error, b has invalid dimensions so penOff cannot be placed");
			b.printBoard();
		} catch(IllegalArgumentException exp) 
		{
			System.out.println("Correct, pen was not placed because board was not made with invalid dimensions");
		}
	}
	
	/**
	 * tests the placement of stylus on a board
	 * if the stylus is moved on a different board then the one it placed on
	 * it will be an error
	 * 
	 * if the stylus is moved on a board it was placed on, it will prompt print correct
	 * 
	 * excpected and actual outcome should be the same
	 */
	public void noStylusBoardTest() 
	{
		System.out.println("\n******No Stylus on Board Test****** \n");
		//what should print after tests
		System.out.println("Expected Outcome: ");
		System.out.println("Correct, the Stylus is not moving on board b");
		System.out.println("Correct, the Stylus did not move on board b and did move down one on board a");
		//making two different boards
		Board a = new Board(10,10);
		Board b = new Board(10,10);
		//making a pen and placing it on board a
		Stylus pen = new Stylus(a);
		System.out.println("\nActual Outcome: ");
		try 
		{
			//if the pen moved on board b
			if(b.move(Direction.DOWN, pen)) 
			{
				System.out.println("Error, pen is moving on board b when it should not");
			}
			//catch the error
		}catch(NullPointerException exp) 
		{
			//print correct statement because it should be caught
			System.out.println("Correct, the Stylus is not moving on board b");
		}
		//testing the placement of stylues
		if(a.move(Direction.DOWN, pen))
		{
			System.out.println("Correct, the Stylus did not move on board b and did move down one on board a");
		}
	}
	
	/**
	 * Tests the making of the board. If the dimensions are invalid (greater than or = to 0 and less than or equal to 100)
	 * the board is not made
	 * 
	 * if the dimensions are valid then the board is made
	 */
	public void testBoard() 
	{
		System.out.println("\n******Board Dimension Test****** \n");
		//what we want to show
		System.out.println("Expected Outcome: ");
		System.out.println("Correct, 0,0 are invalid board dimensions");
		System.out.println("Correct, 101,101 are invalid board dimensions");
		System.out.println("Correct, valid dimensions!");
		System.out.println("\nActual Outcome: ");
		try 
		{
			//if invalid dimension then throw exception from Board 
			Board a = new Board(0,0);
			//this should not print
			System.out.println("Error, the board dimensions are too small");
		}catch(IllegalArgumentException exp) 
		{
			//catch the exception and print message
			System.out.println("Correct, 0,0 are invalid board dimensions");
		}
		try 
		{
			//if invalid dimension then throw exception from Board 
			Board b = new Board(101,101);
			//this should not print
			System.out.println("Error, the board dimensions are too big");
		}catch(IllegalArgumentException exp) 
		{
			//catch the exception and print message
			System.out.println("Correct, 101,101 are invalid board dimensions");
		}
		//board with valid dimensions
		try 
		{
			Board c = new Board(10,10);
			//should print because correct
			System.out.println("Correct, valid dimensions!");
		}catch(IllegalArgumentException exp) 
		{
			//this should not print
			System.out.println("Error, valid board dimensions but did not make board");
		}
	}
	
	/**
	 * the user can move in a direction that would take the stylus off the board
	 * checker should be false and the pen should not move if the direction is out off the board
	 * 
	 * expected and actual should be the same
	 */
	public void movingOutOfBoundsTest() 
	{
		System.out.println("\n******Moving Off The Board Test******\n");
		//what I want to print
		System.out.println("Expected Outcome:");
		System.out.println("Correct, moving left at 0,0 is out of board bounds so the pen is not moving");
		//what is actually printing
		System.out.println("\nActual Outcome: ");
		//new board size 10,10
		Board gameBoard = new Board(10,10);
		//stylus is auto placed at 0,0
		Stylus pen = new Stylus(gameBoard);
		//this is false
		boolean checker = gameBoard.move(Direction.LEFT, pen);
		//should not happen
		if(checker) 
		{
			System.out.println("Error, the pen should not be moving and it is");
		}
		//should happen
		if(!checker);
			System.out.println("Correct, moving left at 0,0 is out of board bounds so the pen is not moving");
	}
	
	/**
	 * Checks to see if object of type Stylus is visible. It should always be visible
	 * if not visible print error message
	 * 
	 * Expected and actual outcome should be the same
	 */
	public void stylusAsBoardableElement() 
	{
		System.out.println("\n******Stylus Visibility Test******\n");
		//what I expect
		System.out.println("Expected Outcome: ");
		System.out.println("Correct, pen is Boardable and is visible");
		//what I get
		System.out.println("\nActual Outcome: ");
		//make new board
		Board board = new Board(10,10);
		//make new pen
		Stylus pen = new Stylus(board);
		//checker is true
		boolean checker = pen.isVisible();
		//if not true error
		if(!checker) 
		{
			System.out.println("Error, pen is a Boardable element and should be visible");
		}
		//if true
		else 
		{
			System.out.println("Correct, pen is Boardable and is visible");
		}
	}
}