package kr.or.connect.djbcexam;

import kr.or.connect.djbcexam.dao.RoleDao;
import kr.or.connect.djbcexam.dto.Role;

public class JDBCExam1 {
	public static void main(String[] args) {
		RoleDao dao = new RoleDao();
		Role role = dao.getRole(100);
		System.out.println(role);
	}
}
