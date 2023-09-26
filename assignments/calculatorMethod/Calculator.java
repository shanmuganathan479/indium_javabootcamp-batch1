import java.util.Scanner;
public class CalculatorMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter first numbers");
        int a = scanner.nextInt();
        System.out.println("enter second number");
        int b = scanner.nextInt();
        System.out.println("Choose an operator: +, -, *, or /");
        char operator = scanner.next().charAt(0);

 

        switch (operator) {
            case '+':
                System.out.println(ADD(a,b));
                break;
            case '-':
                System.out.println(SUB(a,b));
                break;
            case '*':
                System.out.println(MUL(a,b));
                break;
            case '/':
                System.out.println(DIV(a,b));
                break;
        }
    }
    public static int ADD(int a, int b) {
        return a + b;
    }

 

    public static int SUB(int a, int b)
    {
        return a - b;
    }

 

    public static int MUL(int a, int b) {

 

        return a * b;
    }

 

    public static int DIV(int a, int b) {

 

        return a / b;
    }
}
