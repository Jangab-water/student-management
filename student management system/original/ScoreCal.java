package original;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreCal {

	public static void main(String[] args) {
		
		ScoreCal();
	}

	private static void ScoreCal() {
		// ���� ���� ����
		Scanner sc = new Scanner(System.in);
		System.out.print("�й� �Է� : ");
		int stt_pc = sc.nextInt();
		
		
		String url = "jdbc:oracle:thin:@smhrdai.ca6ktgfa4m7b.us-east-2.rds.amazonaws.com:1521/ORCL";// IP, ��Ʈ����, DB�̸�
		String id = "smhrd";
		String pw = "rha5164508";	

		String SQL ="select * from s_score a, student b, course c where a.student_s_id=b.s_id and "
				+ "c.c_name=a.course_c_name and c.c_id=a.course_c_id"
				+ "	and b.s_id= ?";	


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			System.out.println("���� ����!");

			PreparedStatement ps = conn.prepareStatement(SQL); 
			ps.setInt(1, stt_pc);
			ResultSet rs = ps.executeQuery();
			
			ArrayList plist = new ArrayList();


			String stt_nm = null; 											// �ҷ��� ���� ����ȭ. 0���� �ʱ�ȭ
			int tot2 = 20;													// ���� ���� ����!
			int total = 0;													// �̼� ���� ���� ���� (�ʱ�ȭ)
			int result= 0;													// ���� ���� �ʱ�ȭ
			
			while (rs.next()) { // �����Ͱ� ��ִ��� �𸣴� while���� rs.next()��� True������ ���

				result+= rs.getInt("c_point"); 								// ���� �̼���  ������ �̾Ƽ� �� ����

			}
		
			System.out.println("���� ���� : " + tot2);
			System.out.println("�̼� ���� : " + result);
			System.out.println("���� ���� : " + (tot2-result));
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}