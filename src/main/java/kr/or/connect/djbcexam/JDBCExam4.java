package kr.or.connect.djbcexam;

import kr.or.connect.djbcexam.dao.RoleDao;

public class JDBCExam4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int role_id = 500;
		
		RoleDao dao = new RoleDao();
		int deleteCount = dao.deleteRole(role_id);
		
		System.out.println(deleteCount);
	}

}
