package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 在将一系列类型归类为一个父类的时候，遵循的原则
 * 
 * 1、所有子类共有的一般属性都归到父类，不包括子类独有的属性
 * 2、所有子类的静态属性都合并到父类，包括子类独有的
 * 3、构造方法是给属性赋值的，每个子类的属性的值不可能完全一样，
 * 所以，各个子类拥有自己独立的构造方法
 * 
 */
public abstract class FlyingObject {

	protected  int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected static Image img_wall;
	protected static Image img_box;
	protected static Image img_stone;
	
	protected int status;
	
	static{
		try {
			img_wall=ImageIO.read(new File("wall.jpg"));
			img_box=ImageIO.read(new File("food_box.png"));
			img_stone=ImageIO.read(new File("food_stone.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void paintSelf(Graphics g);
	public abstract boolean isOut();
	public abstract void move();
	public abstract boolean isClosed(FlyingObject w);

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
