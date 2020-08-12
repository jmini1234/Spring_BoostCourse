package kr.or.connect.djbcexam;

import java.util.List;

import kr.or.connect.djbcexam.dao.RoleDao;
import kr.or.connect.djbcexam.dto.Role;

public class JDBCExam3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoleDao dao = new RoleDao();
		List <Role> list = dao.getRoles();
		for(Role role:list) {
			System.out.println(role);
		}
	}

}
