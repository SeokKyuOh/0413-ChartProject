/*
 	JFreechart라는 외부 프로그램을 사용할 예정
 	설명 참고 http://www.tutorialspoint.com/jfreechart/
 	
 */
package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import db.DBManager;

public class AppMain extends JFrame implements ActionListener{
	JPanel p_center, p_east, p_south;
	JTable table;
	JScrollPane scroll;
	JButton bt_save, bt_graph;
	DBManager manager = DBManager.getInstance();
	Connection con;	//모델태어나기 전에 커넥션 되어야 하니까 여기.
	MyModel model;
	PieChartPanel pie;
	JFileChooser chooser;
	FileOutputStream fos;
	
	public AppMain() {
		con=manager.getConnection();
		p_center = new JPanel();
		p_east = new JPanel();
		p_south =new JPanel();
		table = new JTable(model = new MyModel(con));
		scroll = new JScrollPane(table);
		bt_save = new JButton("엑셀로 저장");
		bt_graph = new JButton("그래프 보기");
		pie = new PieChartPanel(con);		//new했을때 바로 붙게 하지 않지위해 생성자로 그래프 만들지 않게 만들었음.
		chooser = new JFileChooser();
		
		p_east.setPreferredSize(new Dimension(450, 450));
		p_center.setLayout(new BorderLayout());
		
		p_center.add(scroll);
		
		p_south.add(bt_save);
		p_south.add(bt_graph);
		
		add(p_center);
		add(p_east, BorderLayout.EAST);
		add(p_south, BorderLayout.SOUTH);
		
		//윈도우와 리스너 연결
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {		//윈도우 닫으면 커넥션도 종료한다.
				manager.disConnect(con);
				System.exit(0);	
			}
		});
		
		//버튼과 연결
		bt_save.addActionListener(this);
		bt_graph.addActionListener(this);
		
		
		setSize(950, 500);
		setVisible(true);
				
	}
	
	//엑셀로 저장
	public void save(){
		HSSFWorkbook workBook = new HSSFWorkbook();		//하나의 엑셀 파일과 같은 역할
		HSSFSheet sheet = workBook.createSheet("시험 응시 정보");
		
		for(int i=0;i<table.getRowCount();i++){
			//HSSFRow 를 생성함
			HSSFRow row = sheet.createRow(i);
			for(int a=0; a<table.getColumnCount();a++){
				//HSSFColumn을 생성할꺼야
				HSSFCell cell = row.createCell(a);
				//table.getValueAt(i, a);
				cell.setCellValue((String)table.getValueAt(i, a));
				System.out.print(table.getValueAt(i, a)+", ");
			}
			System.out.println("");
		}
		int result = chooser.showSaveDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION){
			//사용자는 확장자 .xls를 붙여야한다.
			File file = chooser.getSelectedFile();
			
			try {
				fos = new FileOutputStream(file);
				workBook.write(fos);
				JOptionPane.showMessageDialog(this, "엑셀 생성 완료");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(fos!=null){
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	//그래프 보기
	public void graph(){
		p_east.add(pie.showChart());
		p_east.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_save){
			save();
		}
		if(obj == bt_graph){
			graph();
		}
		
	}
	
	public static void main(String[] args) {
		new AppMain();
	}

}
