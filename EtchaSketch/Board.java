//imports arrayList and HashMap
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Models a playing board for Etcha Sketch
 * Creates board, allows caller to move boardable objects,
 * Checks is cells are valid, 
 * @author jessica oathoudt
 *
 */
public class Board 
{
	/**
	 * instance variable creates the board out of a double array of Cells
	 */
	private Cell[][] board;
	/**
	 * instance variable holds the desired height of the board
	 */
	private int height;
	/**
	 * instance variable holds the desired width of the board
	 */
	private int width;
	/**
	 * instance variable that finds location in the board based on key.
	 */
	private HashMap<Boardable, Cell> elementPlace;

	/**
	 * Creates the board and fills it with either "#", "*", or " "
	 * @param height - stores the height of the board
	 * @param width - stores the width of the board
	 */
	public Board(int height, int width) 
	{
		this.height = height;
		this.width = width;
		//makes the board with height and width
		board = new Cell[height][width];
		int row = height;
		int col = width;
		//for all rows
		if(row<1 || col < 1 || row>100 || col>100) {
			throw new IllegalArgumentException("Row and Colum must be greater than 1 and less than 100");
		}
		for (row = 0; row < height; row = row+1) 
		{
			//for all columns
			for (col = 0; col < width; col=col+1) 
			{
				//fill board
				board[row][col] = new Cell(row, col);
			}
		}
		//creates Hashmap
		elementPlace = new HashMap<Boardable, Cell>();
	}
	
	/**
	 * Moves elem on the board based on Direction. Returns true if valid movement and false if not
	 * @param dir - direction of movement
	 * @param elem - a Boardable element, so it can go on the board (will be Stylus)
	 * @return boolean - true if cell is valid, false if not
	 */
	public boolean move(Direction dir, Boardable elem) 
	{
		//finds the elem
		Cell cell = elementPlace.get(elem);
		//make column variable
		int x = cell.col;
		//make row variable
		int y = cell.row;
		//switching on dir
		switch (dir) 
		{
		//changes based on Direction
		//moves the elem on the Board
		case UP:
			y = y - 1;
			break;
		case DOWN:
			y = y + 1;
			break;
		case RIGHT:
			x = x + 1;
			break;
		case LEFT:
			x = x - 1;
			break;
		case UP_LEFT:
			x = x - 1;
			y = y - 1;
			break;
		case UP_RIGHT:
			x = x + 1;
			y = y - 1;
			break;
		case DOWN_LEFT:
			x = x - 1;
			y = y + 1;
			break;
		case DOWN_RIGHT:
			x = x + 1;
			y = y + 1;
			break;
		}
        
        // Check if the new cell is valid
		if (y >= 0 && y < height && x >= 0 && x < width) 
		{
            // Remove pen from past elem
			board[cell.row][cell.col].removeElement(elem);
            // Add pen back to new location
			placeElement(elem, y, x);
			return true;
		}
		else 
		{
			return false;
		}
	}

    //true if element is placed on the board
	/**
	 * Method that puts elem on the board if valid (adds the stylus to the board)
	 * @param elem - this is a Boardable object (Stylus)
	 * @param row - row of board
	 * @param col - column of board
	 * @return boolean - returns true if elem added and false if not
	 */
	public boolean placeElement(Boardable elem, int row, int col) 
	{
		if (row >= 0 && row < height && col >= 0 && col < width) 
    	{
    		//if its inside the board dimensions
        	//put overrides 
			elementPlace.put(elem, board[row][col]);
            //add to board
			board[row][col].addElement(elem);
			return true;
    	} else 
    	{
    		// if its outside the board dimensions
    		return false;
    	}
	}

	/**
	 * Nested Class Cell models a cell in the Board. Adds elem to cell, removes elem from cell, and 
	 * turns cell to a string
	 * @author jessica oathoudt
	 *
	 */
	private class Cell 
	{
    	/**
    	 * instance variable - holds the row
    	 */
		private int row;
        /**
    	 * instance variable - holds the column
    	 */
		private int col;
        /**
    	 * instance variable - boolean that is true when Cell is visible and false if not.
    	 */
		private boolean isVisible;
        /**
    	 * instance variable - array list of boardable elements
    	 */
		private ArrayList<Boardable> elements;

        /**
         * Cell constructor, sets the rows and columns and elements
         * @param row - horizontal cells
         * @param col - vertical cells
         */
		public Cell(int row, int col) 
		{
			this.row = row;
			this.col = col;
			elements = new ArrayList<Boardable>();
		}
        
        /**
         * Adds boardable element to board if elem is visible.
         * @param elem - boardable object (Stylus)
         */
		public void addElement(Boardable elem) 
		{
			if (elem.isVisible()) 
			{
            	//sets isVisible to true
				isVisible = true;
			}
			elements.add(elem);
		}

        /**
         * Removes elem from board
         * @param elem - boardable object
         * @return boolean - true if elem removed from ArrayList elements
         */
		public boolean removeElement(Boardable elem) 
		{
			return elements.remove(elem);
		}

        /**
         * Method that changes the string representation of cell based on isVisible boolean
         * 
         * @return - String "#" if isVisible is false, " " if it's true and "*" if the elem is in it
         */
		public String toString() 
		{
        	//if false (not visible)
			if (!isVisible) 
			{
            	//if not visible (false)
				return "#";
			} else 
			{
            	//if ArrayList elements is empty (visible)
				if (elements.size() == 0) 
				{
					return " ";
                // if elem is in cell
				} else 
				{
					return elements.get(elements.size() - 1).toString();
				}
			}
		}
	}
    /**
     * prints the board to console with height and width
     * "#" if not visible
     */
	public void printBoard() 
	{
		System.out.println();
    	//loop through height
		for (int i = 0; i < height; i++) 
		{
        	//loop through width
			for (int j = 0; j < width; j++) 
			{
            	//print the board
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
