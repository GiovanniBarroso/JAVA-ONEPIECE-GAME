package ui;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.VolumeButtons.*; 
import utilz.LoadSave;

public class VolumeButtons extends PauseButton{
	
	private BufferedImage imgs[];
	private BufferedImage slider;
	private int index=0;

	private boolean mouseOver,mousePressed;
	private int buttonX,minX,maxX;
	
	public VolumeButtons(int x, int y, int width, int height) {
		super(x+width/2, y, VOLUMEN_WIDHT, height);
		buttonX=x+width/2;
		this.x=x;
		this.width=width;
		loadImgs();
		minX=x;
		maxX=x+width;

	}
	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUMNE_BUTTONS);
		imgs= new BufferedImage[3];
		for (int i = 0; i < imgs.length; i++) {
			imgs[i]= temp.getSubimage(i*VOLUMEN_DEFAULT_WIDHT, 0, VOLUMEN_DEFAULT_WIDHT, VOLUMEN_DEFAULT_HEIHGT);
	
		slider=temp.getSubimage(3*VOLUMEN_DEFAULT_WIDHT, 0, SLIDER_WIDHT_DEFAULT, VOLUMEN_DEFAULT_HEIHGT);
		
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
		g.drawImage(imgs[index], buttonX,y,VOLUMEN_WIDHT,height, null);
	}

	public void changeX(int i) {
		if(i <minX) {
			buttonX=minX;
		}else if (i>maxX) {
			buttonX=maxX;
		}else
			buttonX=i;
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

}



