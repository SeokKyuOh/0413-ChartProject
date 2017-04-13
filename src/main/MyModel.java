/*JTable�� ������ �������� ����*/
package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{
	Vector<String> columnName;
	Vector<Vector> data = new Vector<Vector>();
	Connection con;
	
	public MyModel(Connection con) {		//���������� �̿� ���� ����� �����ڿ� ��ü�� �ִ´ٴ� �ǹ̿��� ������ �����̶�� �Ѵ�.
		this.con = con;
		
		columnName = new Vector<String>();
		columnName.add("score_id");
		columnName.add("�г�");
		columnName.add("����");
		columnName.add("����");
		columnName.add("����");
		columnName.add("����");
		
		getList();
	}
	
	/*----------------------------
	 	��� ���ڵ� ��������
	  ----------------------------*/
	public void getList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from score order by score_id asc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){		//dto�� ����ϴ� ���� ������ vector
				Vector vec = new Vector();	//VO������
				//���� vec�� ���� �����ʹ� ���ڵ� 1��
				vec.add(rs.getString("score_id"));
				vec.add(rs.getString("grade"));
				vec.add(rs.getString("gender"));
				vec.add(rs.getString("kor"));
				vec.add(rs.getString("eng"));
				vec.add(rs.getString("math"));
				
				data.add(vec);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public int getColumnCount() {
		return columnName.size();
	}

	public int getRowCount() {
		return data.size();
	}
	
	public String getColumnName(int col) {
		return columnName.elementAt(col);
	}
	
	
	public boolean isCellEditable(int row, int col) {
		boolean flag = true;
		if(col == 0){		//score_id�� ���� ���ϰ� 0��°�� ���Ƶ�
			flag = false;
		}	
		return flag;
	}
	
	public void setValueAt(Object value, int row, int col) {
		Vector vec = data.elementAt(row);
		vec.set(col, value);
	}
	
	public Object getValueAt(int row, int col) {
		return data.elementAt(row).elementAt(col);
		
	}

}