package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Wall extends FlyingObject implements Enemy{
	
	private int dir;//代表方向
	
	public Wall() {
		Random ran=new Random();
		//给宽度高度赋值
		this.width=ran.nextInt(100)+20; // 20-120
		this.height=200+ran.nextInt(100);  //200-300
		
		//dir决定在上还是下生成墙
		dir=ran.nextInt(2);// 0 1
		switch (dir) {
			case 0: this.y=0;break;// 墙在上
			case 1: this.y=Config.JFRAME_HEIGHT-this.height;break;//墙在下
		}
		
		this.x=0;
	}
	
	public void paintSelf(Graphics g){
		g.drawImage(img_wall, x, y, width, height, null);
	}
	
	public void move(){
		this.x+=2;
	}
	
	/* 拿当前墙和给定墙进行比较，是否离的太近，拿当前墙作为参照物，
	 * 参照物最左边还往左150，到最右边还往右150，是自己的势力范围
	 * 当前方法传入的参数，是另一面墙，如果他和参照物靠近，则返回真。
	 * **/
	public boolean isClosed(FlyingObject w){
		if(this.x-350 < w.x && w.x <this.x+this.width+350){
			return true;
		}
		return false;
	}
	
	
	
	public boolean isOut(){
		if(this.x+this.width >Config.JFRAME_WIDTH){
			return true;
		}
		return false;
	}
	

}
