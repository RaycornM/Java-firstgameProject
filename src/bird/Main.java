package bird;

import javax.swing.JFrame; // ctrl shift o
import javax.swing.JPanel;

public class Main {

	//  alt /
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(Config.JFRAME_WIDTH, Config.JFRAME_HEIGHT);
		frame.setLocationRelativeTo(null);// 屏幕居中
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 官方提供的面板的添加方法，只能添加组件，菜单，按钮输入框等。不能画一只鸟
		//JPanel panel=new JPanel();
		
		MyPanel p=new MyPanel();
		
		frame.add(p);// 窗口添加了面板，并循环调用了panel的paint方法
		
		p.createWallAction();//启动创建墙的引擎
		p.moveAction();// 启动移动引擎
		p.moveHandle();//启动手动控制引擎
		p.mouseMoveAction();//启动鼠标控制引擎
		frame.setVisible(true);
	}
	
}
