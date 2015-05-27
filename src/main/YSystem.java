package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import java.util.*;

public class YSystem extends JFrame{
	Box box1, box2, box3, box4;
	Label label1, label2, label3, label4, label5;
	TextField tf1,tf2;
	JComboBox combox;
	Button button1,button2,button3;
	ScrollPane spanel;
	TextArea text;
	Panel panel;
	
	String op[]={"正常收费","打八折","打七折","打五折","满300返100"};
	double sum;
	
	@SuppressWarnings("unchecked")
	YSystem(String s){
		super();
		setTitle(s);
		
		label1=new Label("单价：");
		label2=new Label("数量：");
		label3=new Label("收费方式：");
		box1=Box.createVerticalBox();
		box1.add(label1);
		box1.add(label2);
		box1.add(label3);
		
		tf1=new TextField();
		//tf1.setForeground(Color.red);
		tf2=new TextField();
		//tf2.setForeground(Color.red);
		combox=new JComboBox(op);
		box2=Box.createVerticalBox();
		box2.add(tf1);
		box2.add(tf2);
		box2.add(combox);
		
		button1=new Button("确定");
		button2=new Button("重置");
		button3=new Button("退出");
		box3=Box.createVerticalBox();
		box3.add(button1);
		box3.add(button2);
		box3.add(button3);
		
		box4=Box.createHorizontalBox();
		box4.add(box1);
		box4.add(box2);
		box4.add(box3);
		
		text=new TextArea(6,16);
		text.setEditable(false);
		text.setFont(new Font("",Font.BOLD,16));
		
		//text.setBackground(Color.cyan);
		spanel=new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
		spanel.add(text);
		
		label4=new Label("总计：");
		Font f=new Font("",Font.BOLD,25);
		label4.setFont(f);
		//label4.setForeground(Color.red);
		
		label5=new Label("                                ");
		label5.setFont(new Font("",Font.BOLD,18));
		panel=new Panel();
		panel.add(label4);
		panel.add(label5);
		
		add(BorderLayout.NORTH,box4);
		add(BorderLayout.CENTER,text);
		add(BorderLayout.SOUTH,panel);
		
		button3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		button2.addActionListener(new ReSet());
		button1.addActionListener(new Acquire());
		
		setBounds(200,200,600,400);
		setVisible(true);
		validate();
		
		text.append("\t\t\t"+(new Date()).toString()+"\n");
		
			
	}
	class ReSet implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			tf1.setText(null);
			tf2.setText(null);
			text.setText(null);
			label5.setText(null);
			
			}
		
	 }
	
	
	class Acquire implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String price1=tf1.getText();
			String num1=tf2.getText();
		    String operator1=(String)combox.getSelectedItem();
			
			double price2=Double.parseDouble(price1);
			double num2=Double.parseDouble(num1);
			
		    JiSuan js=new JiSuan();
			double result1=js.jisuan(price2,num2,operator1);
			text.append("\n单价："+price1+" 数量："+num1+
			"  合计："+result1);
			
			//String result3=null;
			//result3=result3+result1;
			sum=sum+result1;
			String result3=String.valueOf(sum);
			label5.setText(result3+"元");
			
			tf1.setText(null);
			tf2.setText(null);
			
		}
		
	}

	
	class JiSuan{
		public double  jisuan(double price3,double num3,String operator2){
			double result2=0.0;
			if("正常收费".equals(operator2)){
				result2=result2+(price3*num3);
			}else if("打八折".equals(operator2)){
				result2=result2+(price3*num3*0.8);
			}else if("打七折".equals(operator2)){
				result2=result2+(price3*num3*0.7);}
			else if("打五折".equals(operator2)){
				result2=result2+(price3*num3*0.5);}
			else if("满300返100".equals(operator2)){
				int dou=(int)(price3*num3);
				result2=result2+(price3*num3-((dou/300)*100));
				}
			
			return result2;
		}
		
	}
}


class TestYSystem{
	public static void main(String[] args){
		new YSystem("商场收银系统");
	}
}
