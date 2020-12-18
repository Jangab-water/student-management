package original; // 교수용 회원가입

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class professor_0 {

	public static int professor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("성함을 입력해주세요 >>");
		String P_NAME = sc.next();
		System.out.println("교수 아이디 을 입력해주세요 >>");
		int P_ID = sc.nextInt();
		System.out.println("전화번호를 입력해주세요 >>");
		String P_TEL = sc.next();
		System.out.println("교수담당과목");
		String P_CLASS  = sc.next();
		System.out.println("학과이름 >>");
		String DEPARTMENT_D_NAME = sc.next();

		// ?-> 파라메터는 숫자 1번부터 간다1,2,3,4,5
		// 파라메타가 포함된 SQL 문장

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

			dnt = ps.executeUpdate(); // 완성된 SQL을 실행 -> 컴파일 안되고 (PreparedStatement에서 컴파일했기떄문에) 바로 실행

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

}
