package member_register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import member_register.jdbc.JdbcUtil;

public class CustomerDao {
	final String SELECT_ALL = "SELECT * FROM CUSTOMER ORDER BY name";
	final String INSERT = "INSERT INTO CUSTOMER (name,tel,email, address) VALUES(?,?,?,?)";
	final String SELECT = "SELECT * FROM CUSTOMER WHERE name=?";
	final String SELECT_NAME = "SELECT * FROM CUSTOMER WHERE NAME LIKE '%\\?\\%'";
	final String UPDATE = "UPDATE CUSTOMER SET tel=?, email=?, address=? WHERE  name=?";
	final String DELETE = "DELETE FROM CUSTOMER WHERE name=?";

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public CustomerDao() {

	}

	public CustomerDao(Connection conn) {
		this.conn = conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	// ____________________________________________________________________
	public Vector<Vector> getStudentList(CustomerDto dto) {
		Vector<Vector> list = new Vector<Vector>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
			
				String name =rs.getString("name");
				String tel=rs.getString("tel");
				String email=rs.getString("email");
				String address=rs.getString("address");

			
				Vector<String> list1=new Vector<>();
				list1.add(name);
				list1.add(tel);
				list1.add(email);
				list1.add(address);
				
				list.add(list1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
	
	
	public Vector<CustomerDto> selectAll() {
		Vector<CustomerDto> list = new Vector<CustomerDto>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				CustomerDto dto = new CustomerDto();
				dto.setName(rs.getString(1));
				dto.setTel(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setAddress(rs.getString(4));
			

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}

	public void insert(CustomerDto dto) {
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTel());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
	

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("입력성공");
				JOptionPane.showMessageDialog(null, "추가완료");
				conn.commit();
			} else {
				System.out.println("입력실패");	
				JOptionPane.showMessageDialog(null, "추가실패");
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	
	public Vector<Vector> selectOne(CustomerDto dto) {
		Vector<Vector> list = new Vector<>();
		try {
			pstmt = conn.prepareStatement(SELECT);
			pstmt.setString(1, dto.getName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name =rs.getString("name");
				String tel=rs.getString("tel");
				String email=rs.getString("email");
				String address=rs.getString("address");

			
				Vector<String> list1=new Vector<>();
				list1.add(name);
				list1.add(tel);
				list1.add(email);
				list1.add(address);
				
				list.add(list1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("검색완료");
		return list;
	}

	public void update(CustomerDto dto) {
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(1, dto.getTel());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(4, dto.getName());
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("데이터 입력성공");
			} else {
				System.out.println("데이서 입력 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(CustomerDto dto) {
		try {
			pstmt = conn.prepareStatement(DELETE);// 명령문
			pstmt.setString(1, dto.getName());// 물음표에 문장 완성문-dto의 물음표는 한개이기 때문에 1번// 위치에 넣는다.
			
			int cnt = pstmt.executeUpdate();// 요청문
			if (cnt > 0) {
				System.out.println("데이터 삭제성공");
			} else {
				System.out.println("데이서 삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("삭제가 완료 되었습니다.");
			JdbcUtil.close(pstmt);
		}
	}
}