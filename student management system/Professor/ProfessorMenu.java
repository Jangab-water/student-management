package Professor;

import java.util.Scanner;

public class ProfessorMenu {

	public void ProfessorMenu() {

		Scanner scan = new Scanner(System.in);
		ProfessorDAO pf = new ProfessorDAO();


		loop1: while (true) {
			System.out.println("[1]���������� ����     [2]��������     [3]�л� ��������     [4]�ڷΰ���");
			int menuSel = scan.nextInt();
			switch (menuSel) {
			case 1: {
				System.out.println("���������� Ȯ��");
				pf.ScoreView();
					break;
				}
			
			case 2: {
				System.out.println("�л� ���� ����");
				pf.studentInfo();
				break;
			}
			case 3: {
				System.out.println("�л� ���� ����");
//				pf.manageStudentScore();
//				System.out.print("�й� �Է� >>");
//				int studentId=scan.nextInt();
//				System.out.print("���� �̸� �Է� >>");
//				String professorName=scan.next();
//				System.out.print("���� �Է�>>");
//				int score=scan.nextInt();
//				pf.UpdateProfessorScore(studentId, professorName, score);
				pf.manageStudentScore();
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
