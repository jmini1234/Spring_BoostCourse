package kr.or.connect.djbcexam;

import kr.or.connect.djbcexam.dao.RoleDao;
import kr.or.connect.djbcexam.dto.Role;

public class JDBCExam5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int role_id = 500;
		String description = "CEO";
		Role role = new Role(role_id, description);
		
		RoleDao dao = new RoleDao();
		
		int updateCount = dao.updateRole(role);
		
		System.out.println(updateCount);
	}

}
