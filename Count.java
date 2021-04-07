package ttt;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Count extends JFrame {
	public static int a,b,cint;//创建题目的两个随机整数a和b以及用0~1随机数来表示的算术运算符c
	public static String c = null;//以及算术运算符c(以字符串形式表示)
	
	public static  JPanel Countpanel;//创建计算面板
	public JPanel Buttonpanel;//创建按钮及结果面板
	public static JButton Clearbutton;//创建清空按钮
	public static JButton Resetbutton;//创建重置按钮
	public static JButton Printfbutton;//创建打印按钮
	public static JLabel CountLable;//创建正确率(标签),正确率在标签正呈现
	public static List<Integer> userlist = new ArrayList<>();//创建存储用户计算结果的集合
	public static List<JLabel> list = new ArrayList<>();//创建题目(标签)集合,题目在标签中呈现
	public static List<JTextField> list1 = new ArrayList<>();//创建用户输入计算结果的文本框集合
	public static List<Integer> list2 = new ArrayList<>();//创建所有题目正确答案集合
	public static int num ;//设置用户答对的题数
	public static int j=0;
	public static int k=0;
	public static int i=0;
	public Count(){
		//初始化按钮
		Clearbutton = new JButton("清空");
		Resetbutton = new JButton("重置");
		Printfbutton = new JButton("打印");
		
		//设置按钮大小
		Clearbutton.setSize(50,100);
		Resetbutton.setSize(50,100);
		Printfbutton.setSize(50,100);
		
		//初始化计数面板
		Countpanel = new JPanel();
		Countpanel.setLayout(new GridLayout(10, 5));
		
		//初始化按钮面板
		Buttonpanel = new JPanel();
		Buttonpanel.setLayout(new FlowLayout());
		Buttonpanel.setSize(200,50);
		Buttonpanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		Buttonpanel.add(Clearbutton);
		CountLable = new JLabel("                         ");
		CountLable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//将按钮添加至按钮及结果面板中
		Buttonpanel.add(Resetbutton);
		Buttonpanel.add(Printfbutton);
		Buttonpanel.add(CountLable);
		
		//为计数面板添加标签和文本框
		//CountLable();
				
		//设置窗体属性
		setLayout(new BorderLayout());
		setBounds(100,100,600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();
		//将计数面板,按钮面板放置在容器中指定位置
		container.add(Countpanel, BorderLayout.NORTH);
		container.add(Buttonpanel,BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		new Count().setVisible(true);
		CountLable();
		
	}
	//设置CountPanel面板中的组件
	public static void CountLable(){
		for(i=0;i<50;i++){
			JLabel label = new JLabel();
			//重点:在设置布局的条件下,调用setPreferredSize(new Dimension(80, 20))可以设置标签的大小
			//重点:而不能调用setsize()方法
			label.setPreferredSize(new Dimension(80, 20));
			label.setFont(new Font("楷体",Font.BOLD, 20));
			label.setBorder(BorderFactory.createLineBorder(Color.RED));//设置标签的边界
			list.add(label);//list为题目(标签)集合,题目在标签中呈现
			JTextField textField = new JTextField(4);
			list1.add(textField);//list1为用户输入计算结果的文本框集合
		}
		for(i = 0;i<100;i++){//利用if-else语句来间隔输出标签和文本框
			if(i%2 == 0){//如果余数为0,则添加标签
				Countpanel.add(list.get(j));
				j++;
			}else{//如果余数为1,则添加文本框
				Countpanel.add(list1.get(k));
				k++;
			}
			
		}
		CreateRandom();//调用创建随机题目的方法CreateRandom();
		myAddActionLIstener();//调用按钮的动作监听方法myAddActionLIstener()
	}	
	//随机创建算术题
	public static void CreateRandom(){
		//int a,b,cint;
		//String c = null;
		Random random = new Random();
		for(JLabel lable:list){//list为题目(标签)集合,题目在标签中呈现
			a = random.nextInt(101);
			b = random.nextInt(101);
			cint = random.nextInt(2);
			switch (cint) {
			case 0:
				c = "+";
				RandomRange();//创建规定算数题范围函数,即和不大于100,差不小于0
				lable.setText(a+c+b);
				list2.add(a+b);//list2为所有题目正确答案的集合
				break;
			default:
				c = "-";
				RandomRange();//创建规定算数题范围函数,即和不大于100,差不小于0
				lable.setText(a+c+b);
				list2.add(a-b);//list2为所有题目正确答案的集合
				break;
			}
		}
	}
	public static void RandomRange(){//创建规定算数题范围函数,即和不大于100,差不小于0
		Random r = new Random();
		switch (c) {
		case "+":
			while(a+b>100){
				a = r.nextInt(101);
				b = r.nextInt(101);
				if(a + b <= 100){
					
				}
			}
			break;
		default:
			while(a-b<0){
				a = r.nextInt(101);
				b = r.nextInt(101);
				if(a - b >= 0){
					break;
				}
			}
			break;
		}
	}
	//为按钮添加动作监听方法
	public static void myAddActionLIstener() {
		//为清空按钮添加动作监听
		Clearbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(JLabel clearlable:list){
					clearlable.setText("");
				}
				for(JTextField cleartextField : list1){
					cleartextField.setText("");
				}	
			}
		});
		//为重置按钮添加动作监听
		Resetbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateRandom();
				for(JTextField cleartextField : list1){
					cleartextField.setText("");
				}
			}
		});
		//为打印按钮添加监听
		Printfbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer i;
				Double result;
				num = 0; 
				//获取用户在文本框中输入的计算结果
				for(i = 0;i<list1.size();i++){//list为用户输入计算结果的文本框集合
					//Integer.parseInt()方法用于将字符串转换成数字
					userlist.add(Integer.parseInt(list1.get(i).getText()));
				}
				//计算用户做对的题目个数
				for(i = 0;i<userlist.size();i++){
					if(userlist.get(i) == list2.get(i)){//list2为所有题目正确答案的集合
						num++;                    //num为用户计算正确的个数
					}
				}
				//计算用户做题的正确率
				result = ((num*1.0)/(list.size())*100);
		        CountLable.setText(result + "%");	
			}
		});
	}
}