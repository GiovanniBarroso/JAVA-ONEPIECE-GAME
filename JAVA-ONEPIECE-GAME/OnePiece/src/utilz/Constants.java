package utilz;

import main.Game;

public class Constants {

	public static final float GRAVITY = 0.04f * Game.SCALE;
	public static final int ANI_SPEED = 10;

	public static class Projectiles {
		public static final int CANNON_BALL_DEFAULT_WIDTH = 15;
		public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;

		public static final int CANNON_BALL_WIDTH = (int) (Game.SCALE * CANNON_BALL_DEFAULT_WIDTH);
		public static final int CANNON_BALL_HEIGHT = (int) (Game.SCALE * CANNON_BALL_DEFAULT_HEIGHT);
		public static final float SPEED = 0.55f * Game.SCALE;
	}

	public static class ObjectConstants {

		public static final int RED_POTION = 0;
		public static final int BLUE_POTION = 1;
		public static final int BARREL = 2;
		public static final int BOX = 3;
		public static final int SPIKE = 4;
		public static final int CANNON_LEFT = 5;
		public static final int CANNON_RIGHT = 6;
		public static final int TREE_ONE = 7;
		public static final int TREE_TWO = 8;
		public static final int TREE_THREE = 9;

		public static final int RED_POTION_VALUE = 15;
		public static final int BLUE_POTION_VALUE = 40;

		public static final int CONTAINER_WIDTH_DEFAULT = 40;
		public static final int CONTAINER_HEIGHT_DEFAULT = 30;
		public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

		public static final int POTION_WIDTH_DEFAULT = 12;
		public static final int POTION_HEIGHT_DEFAULT = 16;
		public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
		public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);

		public static final int SPIKE_WIDTH_DEFAULT = 32;
		public static final int SPIKE_HEIGHT_DEFAULT = 32;
		public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
		public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);

		public static final int CANNON_WIDTH_DEFAULT = 40;
		public static final int CANNON_HEIGHT_DEFAULT = 26;
		public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
		public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);

		public static int GetSpriteAmount(int object_type) {
			switch (object_type) {
			case RED_POTION, BLUE_POTION:
				return 7;
			case BARREL, BOX:
				return 8;
			case CANNON_LEFT, CANNON_RIGHT:
				return 7;
			}
			return 1;
		}

		public static int GetTreeOffsetX(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (Game.TILES_SIZE / 2) - (GetTreeWidth(treeType) / 2);
			case TREE_TWO:
				return (int) (Game.TILES_SIZE / 2.5f);
			case TREE_THREE:
				return (int) (Game.TILES_SIZE / 1.65f);
			}

			return 0;
		}

		public static int GetTreeOffsetY(int treeType) {

			switch (treeType) {
			case TREE_ONE:
				return -GetTreeHeight(treeType) + Game.TILES_SIZE * 2;
			case TREE_TWO, TREE_THREE:
				return -GetTreeHeight(treeType) + (int) (Game.TILES_SIZE / 1.25f);
			}
			return 0;

		}

		public static int GetTreeWidth(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (int) (39 * Game.SCALE);
			case TREE_TWO:
				return (int) (62 * Game.SCALE);
			case TREE_THREE:
				return -(int) (62 * Game.SCALE);

			}
			return 0;
		}

		public static int GetTreeHeight(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (int) (int) (92 * Game.SCALE);
			case TREE_TWO, TREE_THREE:
				return (int) (54 * Game.SCALE);

			}
			return 0;
		}
	}

	public static class EnemyConstants {
		public static final int BUCANERO = 0;
		public static final int ESPADACHIN = 1;
		public static final int KUROHIGE = 2;

		public static final int IDLE = 0;
		public static final int ATTACK = 1;
		public static final int HIT2 = 2;
		public static final int DEAD = 2;
		public static final int DEAD2 = 3;

		public static final int BUCANERO_WIDTH_DEFAULT = 72;
		public static final int BUCANERO_HEIGHT_DEFAULT = 32;
		public static final int ESPADACHIN_WIDTH_DEFAULT = 72;
		public static final int ESPADACHIN__HEIGHT_DEFAULT = 32;
		public static final int KUROHIGE_WIDTH_DEFAULT = 72;
		public static final int KUROHIGE__HEIGHT_DEFAULT = 32;

		public static final int BUCANERO_WIDTH = (int) (BUCANERO_WIDTH_DEFAULT * Game.SCALE);
		public static final int BUCANERO_HEIGHT = (int) (BUCANERO_HEIGHT_DEFAULT * Game.SCALE);
		public static final int ESPADACHIN_WIDTH = (int) (BUCANERO_WIDTH_DEFAULT * Game.SCALE);
		public static final int ESPADACHIN_HEIGHT = (int) (BUCANERO_HEIGHT_DEFAULT * Game.SCALE);
		public static final int KUROHIGE_WIDTH = (int) (BUCANERO_WIDTH_DEFAULT * Game.SCALE);
		public static final int KUROHIGE_HEIGHT = (int) (BUCANERO_HEIGHT_DEFAULT * Game.SCALE);

		public static final int BUCANERO_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int BUCANERO_DRAWOFFSET_Y = (int) (9 * Game.SCALE);
		public static final int ESPADACHIN_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int ESPADACHIN_DRAWOFFSET_Y = (int) (9 * Game.SCALE);
		public static final int KUROHIGE_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int KUROHIGE_DRAWOFFSET_Y = (int) (9 * Game.SCALE);


		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			switch (enemy_type) {
			case BUCANERO:
				switch (enemy_state) {
				case IDLE:
					return 6;
				case ATTACK:
					return 4;
				case DEAD:
					return 4;
				}
			case ESPADACHIN:
				switch (enemy_state) {
				case IDLE:
					return 4;
				case ATTACK:
					return 4;
				case HIT2:
					return 4;
				case DEAD2:
					return 4;
				}
			case KUROHIGE:
				switch (enemy_state) {
				case IDLE:
					return 8;
				case ATTACK:
					return 12;
				case DEAD:
					return 9;
				}
			}

			return 0;

		}

		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case BUCANERO:
				return 1d0;
			case ESPADACHIN:
				return 10;
			case KUROHIGE:
				return 10;
			default:
				return 1;
			}
		}

		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case BUCANERO:
				return 20;
			case ESPADACHIN:
				return 20;
			case KUROHIGE:
				return 20;
			default:
				return 0;
			}
		}

	}

	public static class Environment {
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;

		public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
	}

	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDHT = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
		}

		public static class VolumeButtons {
			public static final int VOLUMEN_DEFAULT_WIDTH = 28;
			public static final int VOLUMEN_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_WIDTH_DEFAULT = 215;

			public static final int VOLUMEN_WIDTH = (int) (VOLUMEN_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUMEN_HEIGHT = (int) (VOLUMEN_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDHT = (int) (SLIDER_WIDTH_DEFAULT * Game.SCALE);

		}
	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;

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

		public static int getSpriteAmount(int player_action) {
			switch (player_action) {
			case ANDAR:
				return 6;
			case ATAQUE:
				return 11;
			case QUIETO:
			case AGACHARSE:
			case QUIETO_5S:
				return 4;
			case SALTO:
				return 3;
			case CORRERALANTE:
				return 7;
			case ESPECIAL:
				return 10;
			default:
				return 0;
			}
		}
	}
}