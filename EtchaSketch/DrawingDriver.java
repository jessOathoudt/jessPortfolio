import java.util.Scanner;

/**
 * Drawing driver class runs the program
 * prints the board and then moves the stylus based on the user's direction
 * and then prints the board again
 * does this until something other than a direction is entered
 * is calling on board, stylus and runs while the move is
 * correct.
 *
 * @author Jess Oathoudt
 */
public class DrawingDriver 
{
	/**
	 * Driver
	 * @param args - String
	 */
	public static void main(String[] args) 
	{
    	System.out.println("\nWelcome to Etcha Sketch!\n");
    	//new scanner
    	Scanner input = new Scanner(System.in);
    	boolean valid = false;

    	int height = 0;
    	int width = 0;
        //tests that the input for board dimensions is correct
    	do 
    	{
    		//exception for height and width input
    		try 
    		{
    			System.out.print("Enter the HEIGHT for the board: ");
    			height = Integer.parseInt(input.nextLine());
    			System.out.print("Enter the WIDTH for the board: ");
    			width = Integer.parseInt(input.nextLine());
                // cant be 0 or less and cant be greater than 100!
    			if (height <= 0 || height > 100 || width <= 0 || width > 100) 
    			{
    				throw new Exception();
    			}
    			valid = true;
    		//if they enter something other than numbers
    		} catch (IllegalArgumentException e) 
    		{
    			System.out.println("\nInvalid input, height and width should be numbers!\n");
    		//if the dimensions are too big or too small
    		} catch(Exception exp)
    		{
    			System.out.println("Invalid board measurements, dimensions are too small or too big!");
    		}
    	
    	//run this loop while board dimensions are invalid
    	} while (!valid);

    	Board gameBoard = new Board(height, width);
    	Stylus pen = new Stylus(gameBoard);


    	boolean beenMoved = true;
    	//this works while gameBoard.move is true
    	//false only if something other than a direction is entered
    	while (beenMoved) 
    	{
    		gameBoard.printBoard();
    		beenMoved = pen.move();
    		
    	}
    	//if something other than a direction is entered
    	System.out.println("\nFinishing your artwork...");
    	System.out.println("Thank you for using Jess' Etcha Sketch!");
	}
}