package original; // ������ ȸ������

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class professor_0 {

	public static int professor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �Է����ּ��� >>");
		String P_NAME = sc.next();
		System.out.println("���� ���̵� �� �Է����ּ��� >>");
		int P_ID = sc.nextInt();
		System.out.println("��ȭ��ȣ�� �Է����ּ��� >>");
		String P_TEL = sc.next();
		System.out.println("����������");
		String P_CLASS  = sc.next();
		System.out.println("�а��̸� >>");
		String DEPARTMENT_D_NAME = sc.next();

		// ?-> �Ķ���ʹ� ���� 1������ ����1,2,3,4,5
		// �Ķ��Ÿ�� ���Ե� SQL ����

		int dnt = 0;

		String url = "jdbc:oracle:thin:@smhrdai.cgboj9u5u5ci.us-east-2.rds.amazonaws.com:1521/ORCL";
		String user = "smhrd";
		String password = "tjsdn112!";

		try {
			String SQL1 = "select * from PROFESSOR where p_id=?";
			Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement ps1 = conn.prepareStatement(SQL1);
			ps1.setInt(1, P_ID);
			ResultSet rs1 = ps1.executeQuery();

			while (rs1.next()) {
				
				return -1;
			}

			String SQL = "insert into PROFESSOR values(?,?,?,?,?)";

			Class.forName("oracle.jdbc.driver.OracleDriver");

			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.setString(1, P_NAME);
			ps.setInt(2,  P_ID);
			ps.setString(3, P_TEL);
			ps.setString(4,  P_CLASS);
			ps.setString(5, DEPARTMENT_D_NAME);

			dnt = ps.executeUpdate(); // �ϼ��� SQL�� ���� -> ������ �ȵǰ� (PreparedStatement���� �������߱⋚����) �ٷ� ����

			if (dnt == 1) {
				System.out.println("���� �Ǿ����ϴ�");
			} else {
				System.out.println("������� �ʾҽ��ϴ� ");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dnt;
	}

}
