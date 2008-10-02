import junit.framework.TestCase;

import java.util.*;

import com.sun.corba.se.spi.ior.MakeImmutable;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest extends TestCase {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest o2bject is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	
	private Piece pyr1, pyr2, pyr3, pyr4, pyr5;
	private Piece stk1, stk2, stk3;
	private Piece lu1, lu2, lu3, lu4, lu5;
	private Piece ld1, ld2, ld3, ld4, ld5;
	private Piece su1, su2, su3;
	private Piece sd1, sd2, sd3;
	private Piece sqr1, sqr2;
	private Piece[] arreglo_piezas;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		arreglo_piezas = Piece.getPieces();
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		pyr5 = pyr4.computeNextRotation();
		
		stk1 = new Piece(Piece.STICK_STR);
		stk2 = stk1.computeNextRotation();
		stk3 = stk2.computeNextRotation();
		
		lu1 = new Piece(Piece.L1_STR);
		lu2 = lu1.computeNextRotation();
		lu3 = lu2.computeNextRotation();
		lu4 = lu3.computeNextRotation();
		lu5 = lu4.computeNextRotation();
		
		ld1 = new Piece(Piece.L2_STR);
		ld2 = ld1.computeNextRotation();
		ld3 = ld2.computeNextRotation();
		ld4 = ld3.computeNextRotation();
		ld5 = ld4.computeNextRotation();
		
		su1 = new Piece(Piece.S1_STR);
		su2 = su1.computeNextRotation();
		su3 = su2.computeNextRotation();
		
		sd1 = new Piece(Piece.S2_STR);
		sd2 = sd1.computeNextRotation();
		sd3 = sd2.computeNextRotation();
		
		sqr1 = new Piece(Piece.SQUARE_STR);
		sqr2 = sqr1.computeNextRotation();
		
	}
	
	//test para probar el width y el height de cada 
	//rotacion para cada pieza 
	
	// Here are some sample tests to get you started
	
	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		
		assertEquals(3, pyr3.getWidth());
		assertEquals(2, pyr3.getHeight());
		
		assertEquals(2, pyr4.getWidth());
		assertEquals(3, pyr4.getHeight());
		
		assertEquals(3, pyr5.getWidth());
		assertEquals(2, pyr5.getHeight());
	
		//ahora con el stick
		assertEquals(1, stk1.getWidth());
		assertEquals(4, stk1.getHeight());
		
		assertEquals(4, stk2.getWidth());
		assertEquals(1, stk2.getHeight());
		
		assertEquals(1, stk3.getWidth());
		assertEquals(4, stk3.getHeight());
		
		//ahora con L1
		assertEquals(2, lu1.getWidth());
		assertEquals(3, lu1.getHeight());
		
		assertEquals(3, lu2.getWidth());
		assertEquals(2, lu2.getHeight());
		
		assertEquals(2, lu3.getWidth());
		assertEquals(3, lu3.getHeight());
		
		assertEquals(3, lu4.getWidth());
		assertEquals(2, lu4.getHeight());
		
		assertEquals(2, lu5.getWidth());
		assertEquals(3, lu5.getHeight());
		
		//ahora con L2
		
		assertEquals(2, ld1.getWidth());
		assertEquals(3, ld1.getHeight());
		
		assertEquals(3, ld2.getWidth());
		assertEquals(2, ld2.getHeight());
		
		assertEquals(2, ld3.getWidth());
		assertEquals(3, ld3.getHeight());
		
		assertEquals(3, ld4.getWidth());
		assertEquals(2, ld4.getHeight());
		
		assertEquals(2, ld5.getWidth());
		assertEquals(3, ld5.getHeight());
		
		//ahora con S1
		
		assertEquals(3, su1.getWidth());
		assertEquals(2, su1.getHeight());
		
		assertEquals(2, su2.getWidth());
		assertEquals(3, su2.getHeight());
		
		assertEquals(3, su3.getWidth());
		assertEquals(2, su3.getHeight());
		
		//ahora con S2
		
		assertEquals(3, sd1.getWidth());
		assertEquals(2, sd1.getHeight());
		
		assertEquals(2, sd2.getWidth());
		assertEquals(3, sd2.getHeight());
		
		assertEquals(3, sd3.getWidth());
		assertEquals(2, sd3.getHeight());
		
		//ahora con square
		
		assertEquals(2, sqr1.getWidth());
		assertEquals(2, sqr1.getHeight());
		
		assertEquals(2, sqr2.getWidth());
		assertEquals(2, sqr2.getHeight());
		
	}
	
	// Test the skirt returned by a few pieces
	
	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0}, stk3.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0,0,0,0}, stk2.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0}, lu5.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1, 1}, lu4.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {1, 1, 0}, ld2.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 2}, ld3.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, su1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, su2.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {1, 0, 0}, sd3.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1}, sd2.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0}, sqr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, sqr2.getSkirt()));
	}
	
	
	/*
	 * Prueba de que las rotaciones computeNextRotation y fastRotation 
	 * funcionen igual con todas las rotaciones
	 */
	public void testequals1() {
		Piece a = arreglo_piezas[1];
		Piece b = arreglo_piezas[1];
		
		Piece p1 = a.computeNextRotation();
		Piece p2 = b.fastRotation();
		assertTrue(p1.equals(p2));
	}
	
	public void testequals2() {
		
		Piece a = arreglo_piezas[2];
		Piece b = arreglo_piezas[2];
		
		Piece p1 = a.computeNextRotation();
		Piece p2 = b.fastRotation();
		assertTrue(p1.equals(p2));
	}

	public void testequals3() {
		
		Piece a = arreglo_piezas[3];
		Piece b = arreglo_piezas[3];
		
		Piece p1 = a.computeNextRotation();
		Piece p2 = b.fastRotation();
		assertTrue(p1.equals(p2));
	}
	
	public void testequals4() {
		
		Piece a = arreglo_piezas[4];
		Piece b = arreglo_piezas[4];
		
		Piece p1 = a.computeNextRotation();
		Piece p2 = b.fastRotation();
		assertTrue(p1.equals(p2));
	}
	public void testequals5() {
	
	Piece a = arreglo_piezas[5];
	Piece b = arreglo_piezas[5];
	
	Piece p1 = a.computeNextRotation();
	Piece p2 = b.fastRotation();
	assertTrue(p1.equals(p2));
}
	
	public void testequals6() {
	
	Piece a = arreglo_piezas[6];
	Piece b = arreglo_piezas[6];
	
	Piece p1 = a.computeNextRotation();
	Piece p2 = b.fastRotation();
	assertTrue(p1.equals(p2));
}
		
	public void test1() {
		
		Piece[] arreglo_piezas = Piece.getPieces();
		Piece u = new Piece("0 0	1 0	 2 0  3 0");
		Piece b = arreglo_piezas[0].fastRotation();
		assertTrue(u.equals(b));
	}
	
	public void test2() {
		
		Piece[] arreglo_piezas = Piece.getPieces();
		Piece u = new Piece("0 0	1 0	 2 0  0 1");
		Piece b = arreglo_piezas[2].computeNextRotation().computeNextRotation().computeNextRotation();
		assertTrue(u.equals(b));
	}

    public void test3() {
		
		Piece[] arreglo_piezas = Piece.getPieces();
		Piece u = new Piece("0 0	1 0	 2 0  2 1");
		Piece b = arreglo_piezas[1].computeNextRotation();
		assertTrue(u.equals(b));
	}
}
