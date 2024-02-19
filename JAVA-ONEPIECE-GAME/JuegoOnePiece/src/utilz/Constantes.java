package utilz;

public class Constantes {

	public static class Direction {
		public static final int LEFT=0;
		public static final int UP=1;
		public static final int RIGHT=2;
		public static final int DOWN=3;

	}
	public static class PlayerConstants {
		public static final int QUIETO = 0;
		public static final int ANDAR = 1;
		public static final int AGACHARSE = 2;
		public static final int ATAQUE = 3;
		public static final int CORRERALANTE = 4;
		public static final int CORRERATRAS = 5;
		public static final int HERIDO = 6;
		public static final int JUMPING = 7;
		public static final int DEFENDER = 8;
		public static final int KAMINARI = 9;


		public static int getSpriteAmount(int player_action) {
			switch (player_action) {
			case ANDAR:
				return 7;
			case CORRERATRAS:
				return 3;
			case ATAQUE:
			case QUIETO:
			case JUMPING:
				return 6;
			case AGACHARSE:
				return 3;
			case CORRERALANTE:
				return 2;
			case HERIDO:
				return 4;
			case KAMINARI :
				return 11;
			case DEFENDER :
				return 5;
			default:
				return 0; 
			}
		}
	}
}
