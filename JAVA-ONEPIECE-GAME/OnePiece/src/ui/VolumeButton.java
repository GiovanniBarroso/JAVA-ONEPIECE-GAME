package ui;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.VolumeButtons.*; 
import utilz.LoadSave;

public class VolumeButton extends PauseButton{

	private BufferedImage imgs[];
	private BufferedImage slider;
	private int index=0;

	private boolean mouseOver,mousePressed;
	private int buttonX,minX,maxX;
	private float floatValue=0f;

	public VolumeButton(int x, int y, int width, int height) {
		super(x+width/2, y, VOLUMEN_WIDTH, height);
		bounds.x -= VOLUMEN_WIDTH / 2;
		buttonX = x + width / 2;
		this.x = x;
		this.width = width;
		minX = x + VOLUMEN_WIDTH / 2;
		maxX = x + width;
		loadImgs();

	}
	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
		imgs= new BufferedImage[3];
		for (int i = 0; i < imgs.length; i++) {
			imgs[i]= temp.getSubimage(i*VOLUMEN_DEFAULT_WIDTH, 0, VOLUMEN_DEFAULT_WIDTH, VOLUMEN_DEFAULT_HEIGHT);

			slider=temp.getSubimage(3*VOLUMEN_DEFAULT_WIDTH, 0, SLIDER_WIDTH_DEFAULT, VOLUMEN_DEFAULT_HEIGHT);

		}


	}

	public void update() {
		index=0;
		if(mouseOver)
			index=1;
		if(mousePressed)
			index=2;

	}

	public void draw(Graphics g) {

		g.drawImage(slider, x,y,width,height, null);
		g.drawImage(imgs[index], buttonX - VOLUMEN_WIDTH / 2, y,VOLUMEN_WIDTH,height, null);
	}

	public void changeX(int i) {
		if(i <minX) 
			buttonX=minX;
		else if (i>maxX) 
			buttonX=maxX;
		else
			buttonX=i;
		updateFloatValue();
		bounds.x= buttonX - VOLUMEN_WIDTH / 2; 
	}


	private void updateFloatValue() {
		float range=maxX-minX;
		float value= buttonX-minX;
		floatValue= value/range;
		
	}
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public float getFloatValue() {
		return floatValue;
	}
}



