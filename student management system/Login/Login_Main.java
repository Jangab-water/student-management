package Login;

import java.util.Scanner;

public class Login_Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LoginDAO ld = new LoginDAO();

		while (true) {
			System.out.println("������������������������Ʈ ���б� �������� ���α׷�������������������");
			System.out.print("[1]�α���   [2]ȸ������   [3]���α׷� ���� >> ");
			int menu = sc.nextInt();
			if (menu == 1) {
				ld.login();
			}

			else if (menu == 2) {
				ld.join_ps();
			} else if (menu == 3) {
				System.out.println("�ý����� �����մϴ�");
				System.exit(0);
			}

			else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�");
			}

		}

	}
}
