package bird;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	BackGround back=new BackGround(0,0);
	Bird bird=new Bird(600,200);

	FlyingObject[] objs=new FlyingObject[0];
	
	public MyPanel() {
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		back.paintSelf(g);
		bird.paintSelf(g);
		for(FlyingObject w:objs){
			w.paintSelf(g); 
			// 方法名是否存在取决于 变量的类型。方法体的内容取决于引用对象 内部的重写的方法体。这种现象多态
			//多态体现在哪？ 答案1：调用父类引用的方法的时候，指向了子类的重写方法体。
			//答案2：调用同一个类的重载方法，也叫多态。
			//答案3： 父类的一个引用，引用了一个子类对象。 FlyingObject a=new GoldBox();
			// 附：引用是啥啥意思？ 引用类型的一个变量，简称引用
			// 附： 引用类型是啥意思？ 除了基本数据类型以外的类型，全是引用类型。
		}
	}
	
	/*自定义一个方法，移动引擎*/
	public void moveAction(){
		new Thread(){
			public void run() {
				for(;;){
					//检测墙是否越界 检测 宝箱是否越界
					FlyingObject[] tmp=new FlyingObject[0];//保存活着的墙
					for(FlyingObject w:objs){
						if(!w.isOut()){
							tmp=Arrays.copyOf(tmp, tmp.length+1);
							tmp[tmp.length-1]=w;
						}
					}
					objs=tmp;
					//检测是否小鸟死亡
					if(bird.getStatus()==Config.BIRD_DEAD){
						JOptionPane.showMessageDialog(null, "小鸟死亡");
						return;
					}
					//检测小鸟撞地
					if(bird.hitGround()){
						bird.setStatus(Config.BIRD_DEAD);//
					}
					//检测小鸟撞墙、宝箱
					for(FlyingObject w:objs){
						if(bird.hit(w)){
							if(w instanceof Enemy){ // 判断对象的具体类型
								bird.setStatus(Config.BIRD_DYING);
							}else if(w instanceof Award){
								w.setStatus(Config.FLYINGOBJECT_DEAD);
							}
						}
					}
					//小鸟移动、背景移动，墙移动
					bird.move();
					back.move();
					for(FlyingObject w:objs){
						if(w!=null){
							w.move();
						}
					}
					repaint();// 调用MyPanel的repaint
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	
	// 自定义方法，手动控制引擎
	public void moveHandle(){
		//创建了键盘监听线程
		KeyAdapter l=new KeyAdapter(){
			// 当有键盘按键按下的时候，就会自动调用
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_UP && bird.getStatus()==Config.BIRD_LIVING){// 按上
						bird.moveUp();
						repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN && bird.getStatus()==Config.BIRD_LIVING){// 按上
					bird.moveDown();
					repaint();
				}
			}
		};
		this.setFocusable(true);// 光标聚焦
		this.addKeyListener(l);// 键盘监听线程启动，直接加给panel
	}
	
	/*创建障碍物墙的引擎*/
	public void createWallAction(){
		
		new Thread(){
			public void run() {
				for(;;){
					//System.out.println("目前墙的数量"+walls.length);
					
					// 扩容数组前，判断新生成的墙和已有的墙进行比较是否离得太近。
					Random r=new Random();
					int n=r.nextInt(3);
					
					FlyingObject w=null;
					
					switch(n){
						case 0:w=new Wall();break;
						case 1: w=new GoldBox();break;
						case 2: w=new Stone();break;
					}
					boolean isOk=true;
					for(FlyingObject tmp:objs){
						if(tmp.isClosed(w)){
							isOk=false;//该w不合格
							break;//只要有一个元素离新墙w很近，结束这个内循环，
						}
					}
					if(!isOk){
						continue;
					}
					objs=Arrays.copyOf(objs,objs.length+1);//
					objs[objs.length-1]=w;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		
	}
	
	// 建立鼠标移动引擎
	public void mouseMoveAction(){
		MouseMotionAdapter m=new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				int x=e.getX();
				int y=e.getY();
				bird.move(x,y);
				repaint();
			}
		};
		this.setFocusable(true);
		this.addMouseMotionListener(m);
	}
	

}
