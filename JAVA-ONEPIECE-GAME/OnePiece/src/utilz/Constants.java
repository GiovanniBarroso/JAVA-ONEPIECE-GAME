package utilz;

import main.Game;

public class Constants {
	
	public static class EnemyConstants {
		public static final int CRABBY = 0;

		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;

		public static final int CRABBY_WIDTH_DEFAULT = 72;
		public static final int CRABBY_HEIGHT_DEFAULT = 32;

		public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
		public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);

		public static final int CRABBY_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int CRABBY_DRAWOFFSET_Y = (int) (9 * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			switch (enemy_type) {
			case CRABBY:
				switch (enemy_state) {
				case IDLE:
					return 9;
				case RUNNING:
					return 6;
				case ATTACK:
					return 7;
				case HIT:
					return 4;
				case DEAD:
					return 5;
				}
			}

			return 0;

		}

	}
	public static class Environment{
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;
		
		public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
	}

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
			public static final int VOLUMEN_DEFAULT_WIDTH=28;
			public static final int VOLUMEN_DEFAULT_HEIGHT=44;
			public static final int SLIDER_WIDTH_DEFAULT=215;
			
			public static final int VOLUMEN_WIDTH=(int) (VOLUMEN_DEFAULT_WIDTH*Game.SCALE);
			public static final int VOLUMEN_HEIGHT=(int) (VOLUMEN_DEFAULT_HEIGHT*Game.SCALE);
			public static final int SLIDER_WIDHT=(int) (SLIDER_WIDTH_DEFAULT*Game.SCALE);
			
		}
	}

	public static class Directions {
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