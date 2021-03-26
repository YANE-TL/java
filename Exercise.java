package dome;

import java.util.Random;

public class Exercise {

	private class Equation {//算式内部类
		private int left,right,result;//左右操作数及结果
		char op;//操作符
		public int getLeft() {
			return left;
		}
		public void setLeft(int left) {
			this.left = left;
		}
		public int getRight() {
			return right;
		}
		public void setRight(int right) {
			this.right = right;
		}
		public int getResult() {
			return result;
		}
		public void setResult(int result) {
			this.result = result;
		}
		public char getOp() {
			return op;
		}
		public void setOp(char op) {
			this.op = op;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exercise e = new Exercise();
		e.generateExercise();
		e.printExercise();
	}
	
	private Equation[] exercise;//习题数据结构
	public Equation[] getExercise() {
		return exercise;
	}
	public void setExercise(Equation[] exercise) {
		this.exercise = exercise;
	}
    
	public String toString(Equation e) {//将算式转换成字符串
		return e.getLeft() +""+ e.getOp()+"" + e.getRight() + "=" + e.getResult();
	}
	
	public int generateRandom(int max,int min) {//生成随机整数
		Random r = new Random();
		return r.nextInt(max-min+1)+min;
	}
	
	public boolean isBetween(int value,int max,int min){//判断运算结果在某个值的范围内
		return value >= min && value <= max;
	}
	
	public boolean isEqual(Equation e1,Equation e2) {//判断两个算式是否相等
		boolean b = false;
		if(e1.getOp() != e2.getOp()) {
			b = false;
		}
		else {
			b = e1.getLeft() == e2.getLeft() && e1.getRight() == e2.getRight();
		}
		return b;
	}
	//计算方法
	public int calculateAddEquation(int left,int right) {
		return left + right;
	}
	public int calculateSubEquation(int left,int right) {
		return left - right;
	}
	//判断算式是否在习题集中存在
	public boolean occursIn(Equation e,Equation[] ex,int n) {
		boolean b = false;
		for(int i = 0;i < n;i++) {
			if(isEqual(e,ex[i])) {
				b = true;
				break;
			}
		}
		return b;
	}
	//生成算式
	public Equation generateAddEquation() {
		Equation e = new Equation();
		int left,right,result;
		while(true) {
			left=generateRandom(100,0);
			right=generateRandom(100,0);
			result=calculateAddEquation(left,right);
			if(isBetween(result,100,0)) {
				e.setLeft(left);
				e.setRight(right);
				e.setResult(result);
				e.setOp('+');
				break;
			}
		}
		return e;
	}
	public Equation generateSubEquation() {
		Equation e = new Equation();
		int left,right,result;
		while(true) {
			left=generateRandom(100,0);
			right=generateRandom(100,0);
			result=calculateSubEquation(left,right);
			if(isBetween(result,100,0)) {
				e.setLeft(left);
				e.setRight(right);
				e.setResult(result);
				e.setOp('-');
				break;
			}
		}
		return e;
	}
	
	
	public Equation generateEquation() {//随机算式
		Equation e = new Equation();
		Random r = new Random();
		if(r.nextInt(2)==1) {//判断随机生成的是加法还是减法
			e = generateAddEquation();//1代表加法
		}
		else {
			e = generateSubEquation();//0代表减法
		}
		return e;
	}
	
	
	//构造习题
	public void generateExercise() {//随机习题
		exercise = new Equation[50];
		for(int i=0;i<50;) {
			Equation e = generateEquation();
			if(occursIn(e,exercise,i)) {
				continue;
			}
			else {
				exercise[i] = e;
				i++;
			}
		}
	}
	public void generateAddExercise() {
		exercise = new Equation[50];
		for(int i=0;i<50;) {
			Equation e = generateAddEquation();
			if(occursIn(e,exercise,i)) {
				continue;
			}
			else {
				exercise[i] = e;
				i++;
			}
		}
	}
	public void generateSubExercise() {
		exercise = new Equation[50];
		for(int i=0;i<50;) {
			Equation e = generateSubEquation();
			if(occursIn(e,exercise,i)) {
				continue;
			}
			else {
				exercise[i] = e;
				i++;
			}
		}
	}
	
	public void printExercise() {//输出习题
		for(int i=0;i<50;i++) {
			System.out.print("("+ (i+1) +")"+ toString(exercise[i])+'\t');
			if((i+1)%5 == 0) {
				System.out.println();
			}
		}
	}
}
