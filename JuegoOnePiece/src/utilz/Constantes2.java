package utilz;

public class Constantes2 {

	public static class Direction {
		public static final int LEFT=0;
		public static final int UP=1;
		public static final int RIGHT=2;
		public static final int DOWN=3;

	}
	public static class PlayerConstants {
		public static final int QUIETO = 0;
		public static final int QUIETO5SEG = 1;
		public static final int CAMINAR = 2;
		public static final int ABAJO = 3;
		public static final int SALTO = 4;
		public static final int CORRERALANTE = 5;
		public static final int ATAQUE = 6;
		public static final int ESPECIAL = 7;
		
		public static final int QUIETO2 = 8;
		public static final int QUIETO5SEG2 = 9;
		public static final int CAMINAR2 = 10;
		public static final int ABAJO2 = 11;
		public static final int SALTO2 = 12;
		public static final int CORRERALANTE2 = 13;
		public static final int ATAQUE2 = 14;
		public static final int ESPECIAL2 = 15;
		


		public static int getSpriteAmount(int player_action) {
			switch (player_action) {
			
			case CAMINAR:
				return 6;
			case CORRERALANTE:
				return 7;
			case QUIETO5SEG:
			case QUIETO:
			case SALTO:
				return 4;
			case ABAJO:
				return 3;
			case ATAQUE:
				return 11;
			case ESPECIAL :
				return 10;
				
				//CASE PARA POSICIONES INVERSAS
			case CAMINAR2:
				return 6;
			case CORRERALANTE2:
				return 7;
			case QUIETO5SEG2:
			case QUIETO2:
			case SALTO2:
				return 4;
			case ABAJO2:
				return 3;
			case ATAQUE2:
				return 11;
			case ESPECIAL2:
				return 10;
			default:
				return 0; 
			}
		}
	}
}
