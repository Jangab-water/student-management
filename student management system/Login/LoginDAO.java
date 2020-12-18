package Login;

import java.sql.*;
import java.util.Scanner;

import Professor.ProfessorMenu;
import Student.StudentMenu;

public class LoginDAO {
	
	public static final String URL = "jdbc:oracle:thin:@smhrdai.cwnxiom872oa.ap-northeast-2.rds.amazonaws.com:1521/ORCL";
	public static final String USER = "smhrd";
	public static final String PASSWORD = "hkread132";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {

		}
	}
	
	

	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return conn;
	}

	// �α��� ����: �ż���
	public void login() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("�������������������������α��� ����� �������ּ��䦡����������������������");
			System.out.print("[1]�л���   [2]������   [3]�ڷΰ���>> ");
			int menu = sc.nextInt();
			if (menu == 1) {
				System.out.println("���������л��릡������ ");

				System.out.print(" ID�� �Է����ּ��� >>");
				int id_st = sc.nextInt();

				int cnt = student_login(id_st);
				if (cnt > 0) {
					StudentMenu stm = new StudentMenu();
					stm.StudentMenu();
				} else {
					System.out.println("�α��� ����. �ٽ��Է����ּ���.");
				}

			} else if (menu == 2) {
				System.out.println("�������������릡������");
				System.out.print(" ID�� �Է����ּ��� >> ");
				int id_pf = sc.nextInt();
				int cnt = professor_login(id_pf);
				if (cnt > 0) {
					ProfessorMenu pfm = new ProfessorMenu();
					pfm.ProfessorMenu();
				} else {
					System.out.println("�α��� ����.  �ٽ��Է����ּ���.");
				}
			} else if (menu == 3) {
				break;
			}

			else {
				System.out.println(" �߸� �Է� �ϼ̽��ϴ�");
				System.out.println(" �ٽ� �ѹ� �Է��� �ּ���");

			}

		}
	}

	// �л��� �α���: �ż���
	public int student_login(int id) {
		String SQL = "select * from STUDENT where S_ID=?"; // ? ->1234
		int cnt = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String S_NAME = rs.getString("S_NAME");
				System.out.println("�α��� ���� ");
				System.out.println("��������������������������������������������������");
				System.out.println(S_NAME + "�� ȯ���մϴ�");
				cnt = 1;
				return cnt;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return cnt;
	}

	// ������ �α���: �ż���
	public int professor_login(int id) {
		String SQL = "select * from PROFESSOR where P_ID=?";
		int dnt = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String P_NAME = rs.getString("P_NAME");
				System.out.println("�α��� ���� ");
				System.out.println("��������������������������������������������������");
				System.out.println(P_NAME + "�� ȯ���մϴ�");
				dnt = 1;
				return dnt;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dnt;
	}

	// �л��� ȸ������: �ż���
	public int student() {
		Scanner sc = new Scanner(System.in);
		System.out.println("��������������������������������������������������");
		System.out.println("�й��Է��� �Է��ϼ��� >>");
		int S_ID = sc.nextInt();
		System.out.println("�̸��Է��� �Է��ϼ��� >>");
		String S_NAME = sc.next();
		System.out.println("�г��� �Է��ϼ��� >>");
		String S_GRADE = sc.next();
		System.out.println("��ȭ��ȣ�� �Է��ϼ��� >>");
		String S_TEL = sc.next();
		System.out.println("�ּҸ� �Է��ϼ��� >>");
		String S_ADDR = sc.next();
		System.out.println("�а��̸� >>");
		String DEPARTMENT_D_NAME = sc.next();

		int cnt = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			String SQL1 = "select * from STUDENT where S_ID=?";
			PreparedStatement ps1 = conn.prepareStatement(SQL1);
			ps1.setInt(1, S_ID);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				// cnt=1;
				return -1;
			}

			String SQL = "insert into STUDENT values(?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, S_ID);
			ps.setString(2, S_NAME);
			ps.setString(3, S_GRADE);
			ps.setString(4, S_TEL);
			ps.setString(5, S_ADDR);
			ps.setString(6, DEPARTMENT_D_NAME);
			cnt = ps.executeUpdate();

			if (cnt == 1) {
				System.out.println("����Ǿ����ϴ�");
			} else {
				System.out.println("������� �ʾҽ��ϴ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// ������ ȸ������
	public int professor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("��������������������������������������������������");
		System.out.println("������ �Է����ּ��� >>");
		String P_NAME = sc.next();
		System.out.println("���� ���̵� �� �Է����ּ��� >>");
		int P_ID = sc.nextInt();
		System.out.println("��ȭ��ȣ�� �Է����ּ��� >>");
		String P_TEL = sc.next();
		System.out.println("����������");
		String P_CLASS = sc.next();
		System.out.println("�а��̸� >>");
		String DEPARTMENT_D_NAME = sc.next();

		int dnt = 0;

		Connection conn = null;
		String SQL1 = "select * from PROFESSOR where p_id=?";

		try {
			conn = getConnection();
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
			ps.setInt(2, P_ID);
			ps.setString(3, P_TEL);
			ps.setString(4, P_CLASS);
			ps.setString(5, DEPARTMENT_D_NAME);

			dnt = ps.executeUpdate();

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

	// ȸ������: �ż���
	public void join_ps() {
		Scanner sc = new Scanner(System.in);
		// ȸ������
		while (true) {
			System.out.println("������������������������ȸ�� ���� ����� �������ּ��䦡����������������������");
			System.out.print("[1]�л���   [2]������   [3]�ڷΰ���>> ");
			int menu = sc.nextInt();

			if (menu == 1) {

				int cnt = student();
				if (cnt == -1) {
					System.out.println("���̵� �ߺ� �Ǿ����ϴ�.");
				}
			} else if (menu == 2) {
				int dnt = professor();

				if (dnt == -1) {
					System.out.println("���̵� �ߺ� �Ǿ����ϴ�.");
				}

			} else if (menu == 3) {
				break;
			}

			else {
				System.out.println(" �߸� �Է� �ϼ̽��ϴ�");
				System.out.println(" �ٽ� �ѹ� �Է��� �ּ��� ");
				System.out.println(" ");

			}

		}
	}
}
