package kr.or.connect.djbcexam;

import kr.or.connect.djbcexam.dao.RoleDao;
import kr.or.connect.djbcexam.dto.Role;

public class JDBCExam2 {

	public static void main(String[] args) {
		int roleId = 500;
		String description = "CTO";
		
		Role role = new Role(roleId, description);
		
		RoleDao dao = new RoleDao();
		int insertCount = dao.addRole(role);
		
		System.out.println(insertCount);

	}

}
