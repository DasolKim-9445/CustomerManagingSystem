package member_register.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class JdbcUtil {//JDBC �����ϱ�
	

	public static Connection getConnection() {

		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "comstudy21";
		String password = "comstudy21";
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("����̹� �˻� ����!");
			//�����Ҷ� ����Ŭ ������ ������ �Ǿ�� jdbc ������ �˴ϴ�. 
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �˻� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
			e.printStackTrace();
		}
		return conn;

	}
	public static void close(Connection conn){
		if(conn!= null){
			try{
				conn.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

	
	public static void close(Statement stmt){
		if(stmt!= null){
			try{
				stmt.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs){
		if(rs!= null){
			try{
				rs.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	public static void close (Connection conn, Statement stmt, ResultSet rs){
		close(rs);
		close(stmt);
		close(conn);
	}
}
