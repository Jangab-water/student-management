package original;
import java.util.Scanner;

public class join {

	public static void join_ps() {
		Scanner sc = new Scanner(System.in);
		
		// ȸ������
		while (true) {
			System.out.println("������������������������ȸ�� ���� ����� �������ּ��䦡����������������������");
			System.out.println("[1]�л���   [2]������ >> ");
			int menu = sc.nextInt();

			if (y == c) {

				int cnt = student_0.student();// �л������Է�ȭ��
				if (cnt == -1) {
					System.out.println("���̵� �ߺ� �Ǿ����ϴ�.");
				}
			} else if (y == d) {
				int dnt = professor_0.professor();

				if (dnt == -1) {
					System.out.println("���̵� �ߺ� �Ǿ����ϴ�.");
				}

			}

			else {
				System.out.println(" �߸� �Է� �ϼ̽��ϴ�");
				System.out.println(" �ٽ� �ѹ� �Է��� �ּ��� ");
				System.out.println(" ");

			}

		}
	}

}
