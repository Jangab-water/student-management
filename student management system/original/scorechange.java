package original;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class scorechange {
	
	public static void ChangeScore() {
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:oracle:thin:@smhrdai.cpdgsj9aizuv.us-east-2.rds.amazonaws.com:1521/ORCL";
		String user = "smhrd";
		String password = "qwer1224";
		
		
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			
			String sql = "update s_score set s_score= ? where student_s_id =? and course_c_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int cnt = 1;
			while(cnt==1) {
			System.out.print("������ �����ϰ��� �ϴ� �л��� �й��� �Է��ϼ��� >> ");
			int id = sc.nextInt();
			System.out.print("���� ���� �� �����ϰ��� �ϴ� ������ �����ϼ��� >> ");
			String course = sc.next();
			System.out.print("������ ������ �Է��ϼ��� >> ");
			int score = sc.nextInt();
			ps.setInt(1, score);
			ps.setInt(2, id);
			ps.setNString(3, course);
			ResultSet rs = ps.executeQuery();
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			
			System.out.print("��� �����Ͻðڽ��ϱ�?  (Y/N) >> ");
			String str = sc.next();
			if(str.toUpperCase().equals("Y")) {
				continue;
			}else if(str.toUpperCase().equals("N")) {
				System.out.println("���� ���α׷��� �����մϴ�.");
				break;
				
			}
			if(cnt==0) {
				break;
			}
				
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
