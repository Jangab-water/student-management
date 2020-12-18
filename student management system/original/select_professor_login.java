package original; //교수용 로그인

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

			ResultSet rs = ps.executeQuery(); // 완성된 SQL을 실행 -> 컴파일 안되고 (PreparedStatement에서 컴파일했기떄문에) 바로 실행
			while (rs.next()) {
				String P_NAME = rs.getString("P_NAME");
				System.out.println("로그인 성공 >>");
				System.out.println(P_NAME + "님 환영합니다");
				dnt = 1;
				return dnt; // dnt 1일떄 로그인성공
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dnt; // 0일떄 로그인 실패
	}

}
