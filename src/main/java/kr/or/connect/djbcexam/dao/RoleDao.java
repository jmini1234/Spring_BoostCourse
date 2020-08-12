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
	
	public int addRole(Role role) {
		int insertCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = (Connection) DriverManager.getConnection(dburl, dbUser, dbpasswd);
			
			String sql = "INSERT INTO role (role_id, description) VALUES (?, ?)";
			
			ps = (PreparedStatement) conn.prepareStatement(sql);
			
			ps.setInt(1, role.getRoleId());
			ps.setString(2, role.getDescription());
			
			insertCount = ps.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//먼저 닫아주기 
			//반드시 수행되는 구절 
			if(ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return insertCount;
	}
	
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
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		
		return role;
	}
}
