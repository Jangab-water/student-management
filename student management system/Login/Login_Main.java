package Login;

import java.util.Scanner;

public class Login_Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LoginDAO ld = new LoginDAO();

		while (true) {
			System.out.println("──────────스마트 대학교 성적관리 프로그램─────────");
			System.out.print("[1]로그인   [2]회원가입   [3]프로그램 종료 >> ");
			int menu = sc.nextInt();
			if (menu == 1) {
				ld.login();
			}

			else if (menu == 2) {
				ld.join_ps();
			} else if (menu == 3) {
				System.out.println("시스템을 종료합니다");
				System.exit(0);
			}

			else {
				System.out.println("잘못 입력하셨습니다");
			}

		}

	}
}
