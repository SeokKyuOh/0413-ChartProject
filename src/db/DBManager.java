/*
	1. ������ �� ���� �α�
		�긦 ����� �����ν� �����ͺ��̽� ���� ������ �ߺ��ؼ� �������� �ʱ� ����
		(DB������ �ϴ� ������ Ŭ��������..)
		Ŭ�������� �̰� ���� ��� ���������� ���������.
	
	2. �ν��Ͻ��� ������ �Ѱ��� �ֺ���
		���ø����̼� ���� �� �����Ǵ� Connection��ü�� �ϳ��� �����ϱ� ����
		
*/
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	//�����ڸ� ���Ƶױ� ������ �긦 ������ �� �ִ� getter�� �ʿ��ϴ�.
	static private DBManager instance;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "bread";
	private String password = "bread";
	private Connection con;
	
	
	private DBManager(){
		/*
			1.����̹� �ε�
			2.����
			3.������ ����
			4.�ݳ�, ����
		*/
		try {
			Class.forName(driver);  //���⼭ class�� �ϳ��� �ڷ���. class�� ���� ���� class��.
			con = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	static public DBManager getInstance() {
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection(){
		return con;
	}
	public void disConnect(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
