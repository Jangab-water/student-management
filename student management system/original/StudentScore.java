package original;
import java.util.Scanner;

public class StudentScore {
	
	public void Scoremanage(){
		Scanner sc=new Scanner(System.in);
		System.out.println("�л� ���� ����â�Դϴ�. ");
		System.out.println("���Ͻô� ���񽺸� �������ּ���.");
		System.out.println("[1]�л��� ���� ����Ʈ    [2]�б⺰ ���� ����Ʈ   [3]�ڷΰ���");
		int menu=sc.nextInt();
		
		switch(menu)
		{
		case 1:
		{
			scoreList.manageStudentScore();
			break;
		}
		case 2:
		{
//			???
			break;
		}
		case 3:
		{
			System.out.println("�ڷ� ����");
			break;
		}
		default:
		{
			System.out.println("�߸��� �Է��Դϴ�.");
		}
		}
	}

}
