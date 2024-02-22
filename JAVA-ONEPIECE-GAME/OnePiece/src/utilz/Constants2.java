package utilz;

public class Constants2 {

	public static class Direction {
		public static final int LEFT=0;
		public static final int UP=1;
		public static final int RIGHT=2;
		public static final int DOWN=3;

	}

	public static class PlayerConstants {
		public static final int QUIETO = 0;
		public static final int ANDAR = 1;
		public static final int CORRER = 2;
		public static final int SALTO = 3;	
		public static final int ABAJO = 4;
		public static final int CUBRIRSE = 5;
		public static final int HERIDO = 6;	
		public static final int ATAQUE = 7;
		public static final int ESPECIAL = 8;

		//INVERSAS

		public static final int QUIETO1 = 9;
		public static final int ANDAR1 = 10;
		public static final int CORRER1 = 11;
		public static final int SALTO1 = 12;	
		public static final int ABAJO1 = 13;
		public static final int CUBRIRSE1 = 14;
		public static final int HERIDO1 = 15;	
		public static final int ATAQUE1 = 16;
		public static final int ESPECIAL1 = 17;


		public static int getSpriteAmount(int player_action) {
			switch (player_action) {

			case ANDAR:
			case ANDAR1:
			case HERIDO:
			case HERIDO1:
				return 8;
			case ATAQUE:
			case ATAQUE1:
				return 10;
			case QUIETO:
			case QUIETO1:
			case CUBRIRSE:
			case CUBRIRSE1:
			case SALTO:
			case SALTO1:
				return 6;
			case ABAJO:
			case ABAJO1:
				return 3;
			case CORRER:
			case CORRER1:
				return 4;
			case ESPECIAL:
			case ESPECIAL1:
				return 12;
			default:
				return 0; 
			}
		}
	}
}