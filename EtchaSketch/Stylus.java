//import Scanner
import java.util.Scanner;

/**
 * Models a stylus that moves on the board
 * it is boardable 
 * it is always visible
 * string version of stylus is: *
 * @author jessica oathoudt
 *
 */
public class Stylus implements Boardable 
{

	/**
	 * instance variable board
	 * type Board holds the board the stylus will move on
	 */
	private Board board;
    /**
     * instance variable input
     * type Scanner, holds the user's input for direction to move the stylus
     * 
     */
	private Scanner input;

    /**
     * Constructor for Stylus
     * @param board - type board, holds the board the stylus will move on
     */
	public Stylus(Board board) 
	{
		this.board = board;
		//creates new scanner
		input = new Scanner(System.in);
        //this calls the stylus
        //places stylus on the board
        // 0,0 is the top left of the board (where it starts)
		this.board.placeElement(this, 0, 0);
	}
    
    /**
     * Based on the direction entered, calls the board move 
     * method it calls the Direction from Enum and the stylus as the
     * boardable element
     * @return boolean - false if direction entered is not a direction
     */
	public boolean move() 
	{
		System.out.println("\nDIRECTIONS: q = UP-LEFT, w = UP, e = UP-RIGHT, a = LEFT, d = RIGHT, "
				+ "z = DOWN-LEFT, x = DOWN, c = DOWN-RIGHT");
		System.out.print("Enter a direction to move: ");
		//takes the direction the user enters
		String dir = input.nextLine();
		//if its not equal to 1 character
		if (dir.length() != 1) 
		{
			//false
			return false;
		}
        //variable for the user's input (String dir)
		char d = dir.charAt(0);
		//switch based on the user's input
		switch (d) 
		{
		//if q,w,e,a,d,z,x,c
		//this is stylus
		//moves the stulus on the board
		case 'q':
			board.move(Direction.UP_LEFT, this);
			break;
		case 'w':
			board.move(Direction.UP, this);
			break;
		case 'e':
			board.move(Direction.UP_RIGHT, this);
			break;
		case 'a':
			board.move(Direction.LEFT, this);
			break;
		case 'd':
			board.move(Direction.RIGHT, this);
			break;
		case 'z':
			board.move(Direction.DOWN_LEFT, this);
			break;
		case 'x':
			board.move(Direction.DOWN, this);
			break;
		case 'c':
			board.move(Direction.DOWN_RIGHT, this);
			break;
		//if not these directions^ false
		default:
			return false;
		}
		return true;
	}

	/**
	 * method overload from boardable interface
	 * always true
	 */
	public boolean isVisible() 
	{
		return true;
	}

	/**
	 * string representation of stylus
	 */
	public String toString() 
	{
		return "*";
	}
}