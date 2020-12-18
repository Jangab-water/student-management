
package original;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
	public static void main(String[] args) {

		
		StudentScoreList();
	}

	private static void StudentScoreList() {
		// �л� ���� ����Ʈ
		
		
		Scanner sc = new Scanner(System.in);
		System.out.print("�й� �Է� : ");
		int st_id = sc.nextInt();

		
		String url = "jdbc:oracle:thin:@smhrdai.ca6ktgfa4m7b.us-east-2.rds.amazonaws.com:1521/ORCL";// IP, ��Ʈ����, DB�̸�
		String id = "smhrd";
		String pw = "rha5164508";	
		
		String SQL = "select * from s_score a, student b, course c where a.student_s_id=b.s_id and c.c_name=a.course_c_name and b.s_id= ?";
			
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			System.out.println("���� ����!");
			System.out.println();

			PreparedStatement ps = conn.prepareStatement(SQL); 
			ps.setInt(1, st_id);
			ResultSet rs = ps.executeQuery();
			
			// �������� ���� ���. ���� ������ �ϱ�
			System.out.println("         ����                " + "����");
			System.out.println("��������������������������������������������������");
			
			String stt_sc = null; 
			String stt_cn = null;
			int tot = 0;
			
			ArrayList<Integer> alist = new ArrayList<Integer>();
			ArrayList clist = new ArrayList<>(); 
			while (rs.next()) { // �����Ͱ� ��ִ��� �𸣴� while���� rs.next()��� True������ ���

				stt_cn = rs.getString("c_name"); 						// ���� 
				stt_sc = rs.getString("s_score");						// ����

				clist.add(stt_cn);  									// ArrayList ����
				alist.add(Integer.parseInt(stt_sc));  					// ArrayList ����
			}

			
			//String ��������� ����ڱ��� �� ��� ���߱�
			
			for (int i=0; i<alist.size(); i++) {
				
				System.out.print(clist.get(i));					//����
				System.out.print(alist.get(i));										//����
				System.out.println();	
				tot += alist.get(i);												//���� ����
			}	
			System.out.println("��������������������������������������������������");
			System.out.println("\t    ���� : " + tot);								//����
			System.out.println("\t    ��� : " + tot/clist.size());					//���

 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}