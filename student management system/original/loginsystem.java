package original;

import java.util.Scanner;

public class loginsystem {

	public void login() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("�������������������������α��� ����� �������ּ��䦡����������������������");
			System.out.print("[1]�л���   [2]������ >> ");
			int menu = sc.nextInt();
			if (menu == 1) {
				System.out.println("���������л��릡������ ");

				System.out.print(" ID�� �Է����ּ��� >>");
				int id_st = sc.nextInt();
				int cnt = select_stdunt_login.student_login(id_st);
				if (cnt > 0) {
				} else {
					System.out.println("�α��� ����. �ٽ��Է����ּ���.");
				}

			} else if (menu == 2) {
				System.out.println("�������������릡������");
				System.out.print(" ID�� �Է����ּ��� >> ");
				int id_pf = sc.nextInt();
				int cnt = select_professor_login.professor_login(id_pf);
				if (cnt > 0) {
				} else {
					System.out.println("�α��� ����  �ٽ��Է����ּ��� >>");
				}
			} else {
				System.out.println(" �߸� �Է� �ϼ̽��ϴ�");
				System.out.println(" �ٽ� �ѹ� �Է��� �ּ���");

			}

		}
	}
}
