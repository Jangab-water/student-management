package original; //������ �α���

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class select_professor_login {

	public int professor_login(int id) {
		String SQL = "select * from PROFESSOR where P_ID=?";

		String url = "jdbc:oracle:thin:@smhrdai.cgboj9u5u5ci.us-east-2.rds.amazonaws.com:1521/ORCL";

		String user = "smhrd";
		String password = "tjsdn112!";
		int dnt = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery(); // �ϼ��� SQL�� ���� -> ������ �ȵǰ� (PreparedStatement���� �������߱⋚����) �ٷ� ����
			while (rs.next()) {
				String P_NAME = rs.getString("P_NAME");
				System.out.println("�α��� ���� >>");
				System.out.println(P_NAME + "�� ȯ���մϴ�");
				dnt = 1;
				return dnt; // dnt 1�ϋ� �α��μ���
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dnt; // 0�ϋ� �α��� ����
	}

}
