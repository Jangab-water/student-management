package original; //�л��� �α���

import java.sql.*;
import java.util.Scanner;

public class select_stdunt_login {

	public static int student_login(int id) {
		String SQL = "select * from STUDENT where S_ID=?"; // ? ->1234
		String url = "jdbc:oracle:thin:@smhrdai.cwnxiom872oa.ap-northeast-2.rds.amazonaws.com:1521/ORCL";
		String user = "smhrd";
		String password = "tjsdn112!";
		int cnt = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String S_NAME = rs.getString("S_NAME");
				System.out.println("�α��� ���� >> ");
				System.out.println(S_NAME + "�� ȯ���մϴ�");
				cnt = 1;
				return cnt;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return cnt;
	}
}
