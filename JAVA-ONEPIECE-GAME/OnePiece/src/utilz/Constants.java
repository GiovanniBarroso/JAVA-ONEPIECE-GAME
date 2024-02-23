package utilz;

import main.Game;

public class Constants {

	public static class UI{
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT= 140;
			public static final int B_HEIGHT_DEFAULT= 56;
			public static final int B_WIDHT=(int) (B_WIDTH_DEFAULT*Game.SCALE);
			public static final int B_HEIGHT=(int) (B_HEIGHT_DEFAULT*Game.SCALE);
		}
		public static class PauseButtons{
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}
		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE=56;
			public static final int URM_SIZE=(int) (URM_DEFAULT_SIZE*Game.SCALE);
		}
		public static class VolumeButtons{
			public static final int VOLUMEN_DEFAULT_WIDHT=28;
			public static final int VOLUMEN_DEFAULT_HEIHGT=44;
			public static final int SLIDER_WIDHT_DEFAULT=215;
			
			public static final int VOLUMEN_WIDHT=(int) (VOLUMEN_DEFAULT_WIDHT*Game.SCALE);
			public static final int VOLUMEN_HEIGHT=(int) (VOLUMEN_DEFAULT_HEIHGT*Game.SCALE);
			public static final int SLIDER_WIDHT=(int) (SLIDER_WIDHT_DEFAULT*Game.SCALE);
			
		}
	}

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

		//INVERSAS

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