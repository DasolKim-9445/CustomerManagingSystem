package member_register.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class JdbcUtil {//JDBC 연동하기
	

	public static Connection getConnection() {

		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "comstudy21";
		String password = "comstudy21";
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 검색 성공!");
			//접속할때 오라클 리스너 실행이 되어야 jdbc 연동이 됩니다. 
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
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
