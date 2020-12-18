package original;
import java.sql.*;

public class Studentinfo {
	
	//print student's information
	public void studentInfo() {

		String url = "jdbc:oracle:thin:@smhrdai.cwnxiom872oa.ap-northeast-2.rds.amazonaws.com:1521/ORCL";
		String user = "scott";
		String password = "tiger";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from student_db order by student_id";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			System.out.println("학생 정보 보기");

			// 학생 리스트 출력
			System.out.println("==========학생 리스트==========");
			
			// 출력문 실행
			while (rs.next()) {
				int student_id = rs.getInt("student_id");
				String student_name = rs.getString("student_name");
				String student_addr = rs.getString("student_addr");
				String student_tel = rs.getString("student_tel");
				System.out.format("%d\t %s\t %s\t %s\n", student_id, student_name, student_addr, student_tel);
			}
			System.out.println("============================");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
