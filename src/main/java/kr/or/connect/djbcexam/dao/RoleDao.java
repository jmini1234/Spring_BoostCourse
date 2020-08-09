package kr.or.connect.djbcexam.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import kr.or.connect.djbcexam.dto.Role;

public class RoleDao {
	public static String dburl = "jdbc:mysql://localhost/connectdb?useSSL=false";
	public static String dbUser = "root";
	public static String dbpasswd = "sjm1771033";
	
	public Role getRole(Integer roleId) {
		Role role = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "SELECT role_id, description FROM role WHERE role_id = ?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			rs = ps.executeQuery();
			if(rs.next()) {
				String description = rs.getString(1);
				int id = rs.getInt("role_id"); // rs.getInt(2); 
				role = new Role(id,description);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//먼저 닫아주기 
			//반드시 수행되는 구절 
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		
		return role;
	}
}
