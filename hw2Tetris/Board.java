// Board.java

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int widths[];
	private int height;
	private int heights[];
	private int maxHeight;
	private boolean[][] grid;
	private boolean DEBUG = true;
	boolean committed;
	
	
	// Here a few trivial methods are provided:
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		
		committed = true;
		
		for (int i = 0; i < width; i++) {
			grid[i] = new boolean[height];
		}
		
		this.widths = new int[height];
		this.heights = new int[width];
		this.maxHeight = 0;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = false;
			}
		}
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {
		this.maxHeight = heights[0];
		for (int i = 1; i < heights.length; i++) {
			this.maxHeight = Math.max(this.maxHeight,heights[i]);
		}
		return this.maxHeight; 
		
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		if (DEBUG) {
			int tmp_maxHeight = 0;
			int tmp_curHeight = 0;
			int tmp_widths[] = new int[height];
			for (int i = 0; i < this.widths.length; i++) {
				tmp_curHeight = 0;				
				for (int j = 0; j < this.heights.length; j++) {
					if (grid[i][j]) {
						tmp_curHeight++;
						tmp_widths[i]++;
					}					
				}
				if (heights[i] != tmp_curHeight)
					throw new RuntimeException("Heights array is corrupted, pos: " + Integer.toString(i));
				tmp_maxHeight = Math.max(tmp_maxHeight, tmp_curHeight);
			}
			if (tmp_maxHeight != maxHeight)
				throw new RuntimeException("MaxHeight is corrupted: is " + Integer.toString(maxHeight) + " should be " + Integer.toString(tmp_maxHeight));
			if (widths != tmp_widths)
				throw new RuntimeException("Widhts array is corrupted");
		}
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		if (x<0 || x>this.width)
			return -1;
		return getColumnHeight(x) + piece.getSkirt()[0]; // YOUR CODE HERE
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		if (x < 0 || x > this.height)
			return -1; //TODO esta bien -1?
		return this.heights[x] ; 
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		if (y < 0 || y > this.width)
			return -1; //TODO error?
		 return this.widths[y]; 
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		if ((x>=0 && x<this.width) &&
			(y>=0 && y<this.height))	
			return this.grid[x][y];
		return true; // YOUR CODE HERE
	}
	
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
			
		int result = PLACE_OK;
		
		// YOUR CODE HERE
		
		return result;
	}
	
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
		DEBUG = false;
		int rowsCleared = 0;
		
		for (int i = 0; i < height; i++) {
			
			if(width == widths[i])
			{
				DEBUG = true;
				rowsCleared++;
				for (int k = i; k < height; k++) 
					for (int l = 0; l < width; l++)
						if(k!=width-1)
							grid[l][k] = grid[l][k+1];
						else
							grid[l][k] = false;
							
				i--;
			}
		}

		sanityCheck();
		return rowsCleared;
	}



	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		// YOUR CODE HERE
	}
	
	
	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		committed = true;
	}


	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
}


