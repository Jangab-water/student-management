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

	// 로그인 로직: 신선우
	public void login() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("────────────로그인 방식을 선택해주세요────────────");
			System.out.print("[1]학생용   [2]교수용   [3]뒤로가기>> ");
			int menu = sc.nextInt();
			if (menu == 1) {
				System.out.println("────학생용──── ");

				System.out.print(" ID를 입력해주세요 >>");
				int id_st = sc.nextInt();

				int cnt = student_login(id_st);
				if (cnt > 0) {
					StudentMenu stm = new StudentMenu();
					stm.StudentMenu();
				} else {
					System.out.println("로그인 실패. 다시입력해주세요.");
				}

			} else if (menu == 2) {
				System.out.println("────교수용────");
				System.out.print(" ID를 입력해주세요 >> ");
				int id_pf = sc.nextInt();
				int cnt = professor_login(id_pf);
				if (cnt > 0) {
					ProfessorMenu pfm = new ProfessorMenu();
					pfm.ProfessorMenu();
				} else {
					System.out.println("로그인 실패.  다시입력해주세요.");
				}
			} else if (menu == 3) {
				break;
			}

			else {
				System.out.println(" 잘못 입력 하셨습니다");
				System.out.println(" 다시 한번 입력해 주세요");

			}

		}
	}

	// 학생용 로그인: 신선우
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
				System.out.println("로그인 성공 ");
				System.out.println("─────────────────────────");
				System.out.println(S_NAME + "님 환영합니다");
				cnt = 1;
				return cnt;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return cnt;
	}

	// 교수용 로그인: 신선우
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
				System.out.println("로그인 성공 ");
				System.out.println("─────────────────────────");
				System.out.println(P_NAME + "님 환영합니다");
				dnt = 1;
				return dnt;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dnt;
	}

	// 학생용 회원가입: 신선우
	public int student() {
		Scanner sc = new Scanner(System.in);
		System.out.println("─────────────────────────");
		System.out.println("학번입력을 입력하세요 >>");
		int S_ID = sc.nextInt();
		System.out.println("이름입력을 입력하세요 >>");
		String S_NAME = sc.next();
		System.out.println("학년을 입력하세요 >>");
		String S_GRADE = sc.next();
		System.out.println("전화번호를 입력하세요 >>");
		String S_TEL = sc.next();
		System.out.println("주소를 입력하세요 >>");
		String S_ADDR = sc.next();
		System.out.println("학과이름 >>");
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
				System.out.println("저장되었습니다");
			} else {
				System.out.println("저장되지 않았습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// 교수용 회원가입
	public int professor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("─────────────────────────");
		System.out.println("성함을 입력해주세요 >>");
		String P_NAME = sc.next();
		System.out.println("교수 아이디 을 입력해주세요 >>");
		int P_ID = sc.nextInt();
		System.out.println("전화번호를 입력해주세요 >>");
		String P_TEL = sc.next();
		System.out.println("교수담당과목");
		String P_CLASS = sc.next();
		System.out.println("학과이름 >>");
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
				System.out.println("저장 되었습니다");
			} else {
				System.out.println("저장되지 않았습니다 ");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dnt;
	}

	// 회원가입: 신선우
	public void join_ps() {
		Scanner sc = new Scanner(System.in);
		// 회원가입
		while (true) {
			System.out.println("────────────회원 가입 방식을 선택해주세요────────────");
			System.out.print("[1]학생용   [2]교수용   [3]뒤로가기>> ");
			int menu = sc.nextInt();

			if (menu == 1) {

				int cnt = student();
				if (cnt == -1) {
					System.out.println("아이디가 중복 되었습니다.");
				}
			} else if (menu == 2) {
				int dnt = professor();

				if (dnt == -1) {
					System.out.println("아이디가 중복 되었습니다.");
				}

			} else if (menu == 3) {
				break;
			}

			else {
				System.out.println(" 잘못 입력 하셨습니다");
				System.out.println(" 다시 한번 입력해 주세요 ");
				System.out.println(" ");

			}

		}
	}
}
