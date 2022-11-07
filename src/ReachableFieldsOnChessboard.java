import java.util.Random;

public class ReachableFieldsOnChessboard {

	public static void main(String[] args) throws Chessboard.NotValidFieldException {

		Chessboard chessBoard = new Chessboard ();
		System.out.println (chessBoard + "\n");
		Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6];
		pieces[0] = chessBoard.new Pawn ('w', 'P');
		pieces[1] = chessBoard.new Rook ('b', 'R');
		pieces[2] = chessBoard.new Queen ('w', 'Q');
		pieces[3] = chessBoard.new Bishop ('w', 'B');
		pieces[4] = chessBoard.new King ('b', 'K');
		pieces[5] = chessBoard.new Knight ('w', 'N');
		
		Random rand = new Random();

		char ro = (char)(Chessboard.FIRST_ROW + rand.nextInt(8));
		byte col = (byte)(Chessboard.FIRST_COLUMN + rand.nextInt(8));
		
		
		for(int i = 0; i < pieces.length; i++) {
			
			ro = (char)(Chessboard.FIRST_ROW + rand.nextInt(8));
			col = (byte)(Chessboard.FIRST_COLUMN + rand.nextInt(8));
			
			pieces[i].moveTo(ro, col);
			System.out.println (chessBoard + "\n");
			pieces[i].markReachableFields();
			System.out.println (chessBoard + "\n");
			pieces[i].unmarkReachableFields();
			System.out.println (chessBoard + "\n");
			pieces[i].moveOut();
			
		}
		
		
		
		
		
	}
	

}
