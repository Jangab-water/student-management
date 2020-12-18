package original;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProfessorScore {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("로딩 성공!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final String URL = "jdbc:oracle:thin:@smhrdai.cwnxiom872oa.ap-northeast-2.rds.amazonaws.com:1521/ORCL";
	public static final String USER = "smhrd";
	public static final String PASSWORD = "hkread132";
	
	private Connection getConnection() {
	    Connection con = null;
	    
	    try {
	      con = DriverManager.getConnection(URL, USER, PASSWORD);
	    }catch(SQLException e) {
	      throw new RuntimeException(e.getMessage());
	    }
	    return con;
	  }
	
	public int UpdateProfessorScore(int studentId,  String professorName, int score) {
		Connection conn = null;
		int cnt = -1;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("접속 성공!");
			String SQL = "update P_Score set p_score = ? where Professor_p_name = ? and Student_s_id = ?";
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, score);
			ps.setString(2, professorName);
			ps.setInt(3, studentId);
			cnt = ps.executeUpdate();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	// 교수 명을 입력하면 학생들이 평가한 점수를 '리스트'로 확인 가능한 메소드
	public List<ProfessorScoreVO1> SelectProfessorScore() { 
	    String sql = "select * from p_score where professor_p_name = ?"; // 교수 이름을 기준으로 평점 출력
	    Scanner sc = new Scanner(System.in); 
	    List<ProfessorScoreVO1> professorScore = new ArrayList<ProfessorScoreVO1>();
	    Connection conn = null;
	    conn = getConnection();
	    System.out.println("확인할 교수 이름을 입력하세요.");
		String name = sc.next();
	    try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name); // SQL문에 해당 교수 이름이 들어감
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int student_s_id = rs.getInt("student_s_id");
				String student_s_name = rs.getString("student_s_name");
				String professor_p_name = rs.getString("professor_p_name");
				int course_c_id = rs.getInt("course_c_id");
				String course_c_name = rs.getString("course_c_name");
				String p_score = rs.getString("p_score");
				
				ProfessorScoreVO1 vo = new ProfessorScoreVO1(student_s_id, student_s_name,
						professor_p_name,course_c_id,course_c_name,p_score);
				
				professorScore.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return professorScore;
	 }
	public int updateStudentScore(int studentId,  String professorName, int score) {
		Connection conn = null;
		int cnt = -1;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String SQL = "update S_Score set s_score = ? where Professor_p_name = ? and Student_s_id = ?";
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, score);
			ps.setString(2, professorName);
			ps.setInt(3, studentId);
			cnt = ps.executeUpdate();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
		
	}
		
}


