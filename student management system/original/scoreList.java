package original;

import java.sql.*;
import java.util.Scanner;

public class scoreList {

	//  학생점수입력
	public void manageStudentScore() {
		Scanner scan = new Scanner(System.in);


		String url = "jdbc:oracle:thin:@smhrdai.cpdgsj9aizuv.us-east-2.rds.amazonaws.com:1521/ORCL";
		String user = "smhrd";
		String password = "qwer1224";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			// 학번=?인 학생 모두 불러오기
			String sql = "select * from student order by s_id";

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			// 학생 리스트 출력
			System.out.println("==========학생 리스트==========");
			// 출력문 실행
			while (rs.next()) {
				int student_id = rs.getInt("s_id");
				String student_name = rs.getString("s_name");
				System.out.format("%d\t %s\n", student_id, student_name);
			}
			System.out.println("============================");

			System.out.print("찾고자 하는 학생의 학번을 입력하세요 >> ");
			int inputStudentId = scan.nextInt();

			String sql2 = "select s_id, s_name from student where s_id=?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, inputStudentId);
			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next() == false) {
				System.out.println("찾으시는 학생이 없습니다");

			} else {
				int student_id = rs2.getInt("s_id");
				String student_name = rs2.getString("s_name");
				System.out.format("찾으시는 학생의 학번과 이름은 %d, %s입니다.\n", student_id, student_name);

				String sql3 = "select s_score, course_c_name from s_score where student_s_id =?";
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setInt(1, inputStudentId);
				ResultSet rs3 = ps3.executeQuery();
				System.out.println("검색결과 >> ");
				while (rs3.next()) {

					int s_score = rs3.getInt("s_score");
					String course_c_name = rs3.getString("course_c_name");
					System.out.println(course_c_name + "의 점수는" + s_score + "점 입니다.");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}