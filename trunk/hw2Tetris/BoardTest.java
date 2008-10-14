import junit.framework.TestCase;


public class BoardTest extends TestCase {
    Board b,c;
    Piece pyr1, pyr2, pyr3, pyr4, s, sRotated, stick, squa, l1, st1, l2;

        // This shows how to build things in setUp() to re-use
        // across tests.
       
        // In this case, setUp() makes shapes,
        // and also a 3X6 board, with pyr placed at the bottom,
        // ready to be used by tests.
       
        protected void setUp() throws Exception {
	    b = new Board(3, 6);
	    c = new Board(5, 7);
               
                pyr1 = new Piece(Piece.PYRAMID_STR);
                pyr2 = pyr1.computeNextRotation();
                pyr3 = pyr2.computeNextRotation();
                pyr4 = pyr3.computeNextRotation();
               
                s = new Piece(Piece.S1_STR);
                sRotated = s.computeNextRotation();
               
                stick = new Piece(Piece.STICK_STR);
	        	st1 = stick.computeNextRotation();

                squa = new Piece(Piece.SQUARE_STR);
               
		l1 = new Piece(Piece.L1_STR);
		l2 = l1.computeNextRotation().computeNextRotation();
                b.place(pyr1, 0, 0);
		c.place(st1,0,0);
        }
       
        // Check the basic width/height/max after the one placement
        public void testSample1() {
                assertEquals(1, b.getColumnHeight(0));
                assertEquals(2, b.getColumnHeight(1));
                assertEquals(2, b.getMaxHeight());
                assertEquals(3, b.getRowWidth(0));
                assertEquals(1, b.getRowWidth(1));
                assertEquals(0, b.getRowWidth(2));
        }
       
        // Place sRotated into the board, then check some measures
        public void testSample2() {
                b.commit();
                int result = b.place(sRotated, 1, 1);
                assertEquals(Board.PLACE_OK, result);
                assertEquals(1, b.getColumnHeight(0));
                assertEquals(4, b.getColumnHeight(1));
                assertEquals(3, b.getColumnHeight(2));
                assertEquals(4, b.getMaxHeight());
        }
       
        // Makre  more tests, by putting together longer series of
        // place, clearRows, undo, place ... checking a few col/row/max
        // numbers that the board looks right after the operations.
       
        public void testSample3() {
                b.commit();
                b.place(sRotated, 1, 1);
                int result2 = b.place(stick, 0, 1);
                assertEquals(Board.PLACE_ROW_FILLED, result2);
                assertEquals(5, b.getColumnHeight(0));
                assertEquals(4, b.getColumnHeight(1));
                assertEquals(3, b.getColumnHeight(2));
                assertEquals(5, b.getMaxHeight());
        }
       
        public void testSample4() {
                b.commit();
                int result = b.place(pyr2, 0, 1);
                assertEquals(Board.PLACE_BAD, result);
                assertEquals(2, b.getMaxHeight());
                assertEquals(3, b.getRowWidth(0));
                assertEquals(1, b.getRowWidth(1));
                assertEquals(0, b.getRowWidth(2));
        }
       
        public void testSample5() {
                b.commit();
                int result = b.place(pyr3, 1, 1);
                assertEquals(Board.PLACE_OUT_BOUNDS, result);
                assertEquals(2, b.getMaxHeight());
                assertEquals(3, b.getRowWidth(0));
                assertEquals(1, b.getRowWidth(1));
                assertEquals(0, b.getRowWidth(2));
        }

    public void testSample6() {
	c.commit();
	int result = c.place(l2, 3, 0);
	assertEquals(Board.PLACE_ROW_FILLED, result);
	assertEquals(3, c.getMaxHeight());
	assertEquals(5, c.getRowWidth(0));
	assertEquals(1, c.getRowWidth(1));
	c.clearRows();
	assertEquals(2, c.getMaxHeight());
	assertEquals(1, c.getRowWidth(0));
	assertEquals(2, c.getRowWidth(1));
	assertEquals(0, c.getRowWidth(2));
        }

    public void testSample7() {
	c.commit();
	int result = b.place(squa, 0, 7);
	assertEquals(Board.PLACE_BAD, result);
	assertEquals(1, c.getMaxHeight());
	assertEquals(4, c.getRowWidth(0));
	assertEquals(0, c.getRowWidth(1));
	
        }
       
}

