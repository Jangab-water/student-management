package Student;

import java.util.Scanner;

public class StudentMenu {

	public void StudentMenu() {
		Scanner scan = new Scanner(System.in);
		StudentDAO st = new StudentDAO();

		
		loop1:
		while (true) {
			System.out.println("��������������������������������������������������");
			System.out.println("[1]���������� ��   [2]���� ���� Ȯ��   [3]��������   [4]�ڷΰ���");
			int menuSel = scan.nextInt();
			
			switch (menuSel) {
			case 1: {
				st.ProfessorScoreMain();
				// ����
				break;
			}
			case 2: {
				System.out.println("���� ���� Ȯ��");
				st.StudentScoreList();
				break;
			}
			case 3: {
				System.out.println("��������Ȯ��");
				st.ScoreCal();
				// �̿�
				break;
			}
			case 4: {
				System.out.println("�ڷΰ���");
				break loop1;
			}
			default: {
				System.out.println("�߸��� �Է��Դϴ�.");
			}
			
			}

		}
	}

}
