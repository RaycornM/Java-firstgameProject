package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class GoldBox extends FlyingObject implements Award{
	
	
	public GoldBox() {
		Random ran=new Random();
		this.x=0;// 宝箱初始位置在窗口左侧
		this.y=ran.nextInt(Config.JFRAME_HEIGHT);
		this.width=60;
		this.height=60;
	}
	
	public void paintSelf(Graphics g){
		if(status== Config.FLYINGOBJECT_LIVING){
			g.drawImage(img_box,x,y,width,height,null);
		}
	}
	
	public void move(){
		this.x++;// 步调和背景一致
	}
	
	public boolean isClosed(FlyingObject w){
		if(this.x-350 < w.x && w.x <this.x+this.width+350){
			return true;
		}
		return false;
	}
	
	public boolean isOut(){
		if(this.x+this.width>Config.JFRAME_WIDTH){
			return true;
		}
		return false;
	}

}
