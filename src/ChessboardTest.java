
public class ChessboardTest {

	public static void main(String[] args) throws Chessboard.NotValidFieldException {

		Chessboard chessBoard = new Chessboard ();
		System.out.println (chessBoard + "\n");
		Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6];
		
		char r = 'c';
		byte c = 6;
		
		// PAWN: test putting on, marking, moving around and putting off
		/*
		pieces[0] = chessBoard.new Pawn ('w', 'P');
		pieces[0].moveTo(r, c);
		System.out.println (chessBoard + "\n");
		
		pieces[0].markReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[0].unmarkReachableFields();
		
		r = 'f';
		c = 2;
		pieces[0].moveOut();
		pieces[0].moveTo(r, c);
		System.out.println (chessBoard + "\n");
		pieces[0].markReachableFields();
		System.out.println (chessBoard + "\n");
		
		pieces[0].unmarkReachableFields();
		pieces[0].moveOut();
		*/
		
		/*
		// test ROOK
		r = 'f';
		c = 4;
		pieces[1] = chessBoard.new Rook ('b', 'R');
		pieces[1].moveTo(r, c);
		System.out.println (chessBoard + "\n");
		pieces[1].markReachableFields();
		System.out.println (chessBoard + "\n");
		
		
		r = 'a';
		c = 4;
		pieces[1].unmarkReachableFields();
		pieces[1].moveOut();
		pieces[1].moveTo(r, c);
		System.out.println (chessBoard + "\n");
		pieces[1].markReachableFields();
		System.out.println (chessBoard + "\n");
		
		pieces[1].unmarkReachableFields();
		pieces[1].moveOut();
		*/
		
		/*
		// test KNIGHT
		r = 'e';
		c = 5;
		pieces[2] = chessBoard.new Knight ('w', 'N');
		pieces[2].moveTo(r, c);
		System.out.println (chessBoard + "\n");
		pieces[2].markReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[2].unmarkReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[2].moveOut();
		*/
		
		/*
		// test BISHOP
		r = 'e';
		c = 2;
		pieces[3] = chessBoard.new Bishop ('w', 'B');
		pieces[3].moveTo(r, c);
		System.out.println (chessBoard + "\n");
		pieces[3].markReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[3].unmarkReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[3].moveOut();
		*/
		
		/*
		// test QUEEN
		r = 'e';
		c = 4;
		pieces[4] = chessBoard.new Queen ('w', 'Q');
		pieces[4].moveTo(r, c);
		System.out.println (chessBoard + "\n");
		pieces[4].markReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[4].unmarkReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[4].moveOut();
		*/
		
		/*
		// test KING
		r = 'a';
		c = 1;
		pieces[5] = chessBoard.new King ('b', 'K');
		pieces[5].moveTo(r, c);
		System.out.println (chessBoard + "\n");
		pieces[5].markReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[5].unmarkReachableFields();
		System.out.println (chessBoard + "\n");
		pieces[5].moveOut();
		*/
		
		
		
		
		
		
	}
	
	
	
}
