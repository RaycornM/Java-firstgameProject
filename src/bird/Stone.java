package bird;

import java.awt.Graphics;
import java.util.Random;

public class Stone extends FlyingObject implements Award{

	
	public Stone() {
		Random ran=new Random();
		this.x=0;// 宝箱初始位置在窗口左侧
		this.y=ran.nextInt(Config.JFRAME_HEIGHT);
		this.width=60;
		this.height=60;
	}
	
	public void paintSelf(Graphics g){
		if(status== Config.FLYINGOBJECT_LIVING){
			g.drawImage(img_stone, x, y, width, height, null);
		}
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
