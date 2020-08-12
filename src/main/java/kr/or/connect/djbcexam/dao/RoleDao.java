package kr.or.connect.djbcexam.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import kr.or.connect.djbcexam.dto.Role;

public class RoleDao {
	public static String dburl = "jdbc:mysql://localhost/connectdb?useSSL=false";
	public static String dbUser = "root";
	public static String dbpasswd = "sjm1771033";
	
	public List<Role> getRoles(){
		List<Role> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT description, role_id FROM role order by role_id";
		try(Connection conn = (Connection) DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql)) {
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					String description = rs.getString("description"); // description 넣어도 결과 같음
					int id = rs.getInt("role_id"); // 2 넣어도 결과 같음 
					Role role = new Role(id, description);
					list.add(role);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int deleteRole(int roleId) {
		int deleteCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 드라이버 매니저가 getConnection
			conn = (Connection) DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "DELETE FROM role WHERE role_id = ?";
			// connection의 상태 변화 -> by sql 문 
			ps = (PreparedStatement) conn.prepareStatement(sql);
			// 상태 변화 후 role_id 대입
			ps.setInt(1, roleId);
			// ps문 끝마쳤으면 실행
			deleteCount = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return deleteCount;
	}
	
	public int updateRole(Role role) {
		int updateCount=0;

		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "UPDATE role SET description=? WHERE role_id = ?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			
			ps.setString(1, role.getDescription());
			// role_id는 입력 받음  
			ps.setInt(2, role.getRoleId());
			
			updateCount = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
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
		}
		
		return updateCount;
	}
		
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
