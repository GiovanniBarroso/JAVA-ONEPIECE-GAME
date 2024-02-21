package utilz;

public class Constants {

	public static class Direction {
		public static final int LEFT=0;
		public static final int UP=1;
		public static final int RIGHT=2;
		public static final int DOWN=3;

	}
	public static class PlayerConstants {
		public static final int QUIETO = 0;
		public static final int ANDAR = 1;
		public static final int ATAQUE = 2;
		public static final int AGACHARSE = 3;	
		public static final int CORRERALANTE = 4;
		public static final int ESPECIAL = 5;
		public static final int QUIETO_5S = 6;	
		public static final int SALTO = 7;
		//inversas
		public static final int QUIETO1 = 8;
		public static final int ANDAR1 = 9;
		public static final int ATAQUE1 = 10;
		public static final int AGACHARSE1 = 11;	
		public static final int CORRERALANTE1 = 12;
		public static final int ESPECIAL1 = 13;
		public static final int QUIETO_5S1 = 14;	
		public static final int SALTO1 = 15;


		public static int getSpriteAmount(int player_action) {
			switch (player_action) {
			case ANDAR:
			case ANDAR1:
				return 6;
			case ATAQUE:
			case ATAQUE1:
				return 11;
			case QUIETO:
			case QUIETO1:
			case AGACHARSE:
			case AGACHARSE1:
			case QUIETO_5S:
			case QUIETO_5S1:
				return 4;
			case SALTO:
			case SALTO1:
				return 3;
			case CORRERALANTE:
			case CORRERALANTE1:
				return 7;
			case ESPECIAL:
			case ESPECIAL1:
				return 10;
			default:
				return 0; 
			}
		}
	}
}