
public class Chessboard {

	public static class Field {
		
		private char row;
		private byte column;
		private Chesspiece piece = null;
		private boolean marked = false;
	
		public Field(char row, byte column) {
			this.row = row;
			this.column = column;
		}
		
		public void put(Chesspiece piece) { this.piece = piece; }
		
		public Chesspiece take() { 
			Chesspiece cp = this.piece;
			piece = null;
			return cp; 
		}
		
		public void mark() { marked = true; }
		
		public void unmark() { marked = false; }
		
		public String toString() {
			String s = (marked)? "xx" : "--";
			return (piece == null)? s : piece.toString ();
		}
		
	}
	
	public static final int NUMBER_OF_ROWS = 8;
	public static final int NUMBER_OF_COLUMNS = 8;
	public static final int FIRST_ROW = 'a';
	public static final int FIRST_COLUMN = 1;
	private Field[][] fields;
	
	public Chessboard () {
		fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		char row = 0;
		byte column = 0;
		
		for (int r = 0; r < NUMBER_OF_ROWS; r++) {
			row = (char) (FIRST_ROW + r);
			column = FIRST_COLUMN;
			for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				fields[r][c] = new Field (row, column);
				column++;
			}
		}
	
	}
	
	public String toString () {
		String str = new String("");
		char row = 0;
		byte column = 0;
		
		str += "  ";
		for(int i = FIRST_COLUMN; i <= NUMBER_OF_COLUMNS; i++) {
			str += (i + "  ");
		}
		str += '\n';
		
		for(int r = 0; r < NUMBER_OF_ROWS; r++) {
			row = (char) (FIRST_ROW + r);
			column = FIRST_COLUMN;
			str += row + " ";
			for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				str += fields[r][c].toString();
				str += " ";
				column++;
			}
			str += "\n";
		}
		
		return str;
	}
	
	public boolean isValidField (char row, byte column) {
		if(row >= FIRST_ROW && row < (FIRST_ROW + NUMBER_OF_ROWS) && column >= FIRST_COLUMN && column < (FIRST_COLUMN + NUMBER_OF_COLUMNS))
			return true;
		else
			return false;
	}
	
	
	// CHESSPIECE CLASSES
	public abstract class Chesspiece {
		private char color;
		// w - white, b - black
		private char name;
		// K - King, Q - Queen, R - Rook, B - Bishop, N - Knight,
		// P – Pawn
		protected char row = 0;
		protected byte column = -1;
		
		protected Chesspiece (char color, char name) {
			this.color = color;
			this.name = name;
		}
		
		public String toString () {
			return "" + color + name;
		}
		
		public boolean isOnBoard () {
			return Chessboard.this.isValidField (row, column);
		}
		
		public void moveTo (char row, byte column) throws NotValidFieldException {
			
			if (!Chessboard.this.isValidField (row, column))
				throw new NotValidFieldException ("bad field: " + row + column );
			
			this.row = row;
			this.column = column;
			int r = row - FIRST_ROW;
			int c = column - FIRST_COLUMN;
			Chessboard.this.fields[r][c].put(this);
		}
		
		public Chesspiece moveOut () {
			int r = this.row - FIRST_ROW;
			int c = this.column - FIRST_COLUMN;
			Chesspiece cp;
			cp = Chessboard.this.fields[r][c].take();
			this.row = 0;
			this.column = -1;
			return cp;
		}
		
		public abstract void markReachableFields ();
		public abstract void unmarkReachableFields ();
	}
	
	// TODO: 
	// Check difference between nested/unnested classes
	// Check difference between static/non-static functions/variables
	// Move Chesspiece classes to separate file?
	
	public class Pawn extends Chesspiece {
		public Pawn (char color, char name) {
			super (color, name);
		}
	
		public void markReachableFields () {
			byte col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
		}
		
		public void unmarkReachableFields () {
			byte col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
		}
	
	}
	
	public class Rook extends Chesspiece {
		public Rook(char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			byte col = FIRST_COLUMN;
			int r = row - FIRST_ROW;
			for(int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				if(Chessboard.this.isValidField(this.row, col)) {
					Chessboard.this.fields[r][c].mark ();
				}
				col++;
			}

			char ro = FIRST_ROW;
			int c = column - FIRST_COLUMN;
			for(r = 0; r < NUMBER_OF_ROWS; r++) {
				if(Chessboard.this.isValidField(ro, this.column)) {
					Chessboard.this.fields[r][c].mark ();
				}
				ro++;
			}
		}
		
		public void unmarkReachableFields () {
			byte col = FIRST_COLUMN;
			int r = row - FIRST_ROW;
			for(int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				if(Chessboard.this.isValidField(this.row, col)) {
					Chessboard.this.fields[r][c].unmark ();
				}
				col++;
			}

			char ro = FIRST_ROW;
			int c = column - FIRST_COLUMN;
			for(r = 0; r < NUMBER_OF_ROWS; r++) {
				if(Chessboard.this.isValidField(ro, this.column)) {
					Chessboard.this.fields[r][c].unmark ();
				}
				ro++;
			}
		}
		
	}
	
	public class Knight extends Chesspiece {
		public Knight(char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			
			char ro = (char) (row + 1);
			byte col = (byte) (column - 2);
			
			for(int i = 0; i < 8; i++) {
			
				if (Chessboard.this.isValidField (ro, col)) {
					int r = ro - FIRST_ROW;
					int c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				
				if(i <= 2) {
					switch(i) {
					case(0): ro -= 2; break;
					case(1): col += 4; break;
					case(2): ro += 2; break;
					}
				} else if(i >= 3 && i <= 7) {
					switch(i) {
					case(3): ro++; col--; break;
					case(4): col -= 2; break;
					case(5): ro -= 4; break;
					case(6): col += 2; break;
					}
				}
				
			}
			
		}
		
		public void unmarkReachableFields () {
			
			char ro = (char) (row + 1);
			byte col = (byte) (column - 2);
			
			for(int i = 0; i < 8; i++) {
			
				if (Chessboard.this.isValidField (ro, col)) {
					int r = ro - FIRST_ROW;
					int c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				
				if(i <= 2) {
					switch(i) {
					case(0): ro -= 2; break;
					case(1): col += 4; break;
					case(2): ro += 2; break;
					}
				} else if(i >= 3 && i <= 7) {
					switch(i) {
					case(3): ro++; col--; break;
					case(4): col -= 2; break;
					case(5): ro -= 4; break;
					case(6): col += 2; break;
					}
				}
				
			}
			
		}
		
		
	}
	
	public class Bishop extends Chesspiece {
		public Bishop (char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			
			char ro = row;
			byte col = column;
			int r = row - FIRST_ROW;
			int c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].mark ();
				ro++; col++; r++; c++;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].mark ();
				ro--; col--; r--; c--;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].mark ();
				ro++; col--; r++; c--;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].mark ();
				ro--; col++; r--; c++;
			}
			
		}
		
		public void unmarkReachableFields () {
			
			char ro = row;
			byte col = column;
			int r = row - FIRST_ROW;
			int c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].unmark ();
				ro++; col++; r++; c++;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].unmark ();
				ro--; col--; r--; c--;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].unmark ();
				ro++; col--; r++; c--;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].unmark ();
				ro--; col++; r--; c++;
			}
			
		}
		
	}
	
	public class Queen extends Chesspiece {
		public Queen (char color, char name) {
			super (color, name);
		}
		
		public void markReachableFields () {
			// horizontal & vertical moves
			byte col = FIRST_COLUMN;
			int r = row - FIRST_ROW;
			for(int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				if(Chessboard.this.isValidField(this.row, col)) {
					Chessboard.this.fields[r][c].mark ();
				}
				col++;
			}

			char ro = FIRST_ROW;
			int c = column - FIRST_COLUMN;
			for(r = 0; r < NUMBER_OF_ROWS; r++) {
				if(Chessboard.this.isValidField(ro, this.column)) {
					Chessboard.this.fields[r][c].mark ();
				}
				ro++;
			}
			// diagonal moves
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].mark ();
				ro++; col++; r++; c++;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].mark ();
				ro--; col--; r--; c--;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].mark ();
				ro++; col--; r++; c--;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].mark ();
				ro--; col++; r--; c++;
			}
			
		}
		
		public void unmarkReachableFields () {
			// horizontal & vertical moves
			byte col = FIRST_COLUMN;
			int r = row - FIRST_ROW;
			for(int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				if(Chessboard.this.isValidField(this.row, col)) {
					Chessboard.this.fields[r][c].unmark ();
				}
				col++;
			}

			char ro = FIRST_ROW;
			int c = column - FIRST_COLUMN;
			for(r = 0; r < NUMBER_OF_ROWS; r++) {
				if(Chessboard.this.isValidField(ro, this.column)) {
					Chessboard.this.fields[r][c].unmark ();
				}
				ro++;
			}
			// diagonal moves
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].unmark ();
				ro++; col++; r++; c++;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].unmark ();
				ro--; col--; r--; c--;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].unmark ();
				ro++; col--; r++; c--;
			}
			
			ro = row;
			col = column;
			r = row - FIRST_ROW;
			c = column - FIRST_COLUMN;
			while(Chessboard.this.isValidField(ro, col)) {
				Chessboard.this.fields[r][c].unmark ();
				ro--; col++; r--; c++;
			}
			
		}
		
		
		
	}
	
	public class King extends Chesspiece {
		public King (char color, char name) {
			super (color, name);
		}
		// FIX BUG HERE...
		public void markReachableFields () {
			
			byte col = (byte)(column + 1);
			if (Chessboard.this.isValidField (row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
			col = (byte)(column - 1);
			if (Chessboard.this.isValidField (row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
			char ro = (char)(row + 1);
			if (Chessboard.this.isValidField (ro, column)) {
				int r = ro - FIRST_ROW;
				int c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			
			ro = (char)(row - 1);
			if (Chessboard.this.isValidField (ro, column)) {
				int r = ro - FIRST_ROW;
				int c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}

		}
		
		public void unmarkReachableFields () {
			
			byte col = (byte)(column + 1);
			if (Chessboard.this.isValidField (row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			
			col = (byte)(column - 1);
			if (Chessboard.this.isValidField (row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			
			char ro = (char)(row + 1);
			if (Chessboard.this.isValidField (ro, column)) {
				int r = ro - FIRST_ROW;
				int c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			
			ro = (char)(row - 1);
			if (Chessboard.this.isValidField (ro, column)) {
				int r = ro - FIRST_ROW;
				int c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}

		}
		
		
	}
	
	
	public class NotValidFieldException extends Exception {
		public NotValidFieldException(String str) {
			System.out.println(str);
		}	
	}

	
}
