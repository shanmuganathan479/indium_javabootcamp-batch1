import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter Value 1 : ");

        float numberOne = reader.nextFloat();

        System.out.println("Enter Value 2 : ");

        float numberTwo = reader.nextFloat();

        System.out.println("These are available ");

        System.out.println("For Addition press 1");

        System.out.println("For Subraction press 2");

        System.out.println("For Multiplication press 3");

        System.out.println("For division press 4");

        System.out.println("what u want to do ?");

        int userChoice = reader.nextInt();

        switch(userChoice) {

            case 1: System.out.println(numberOne+numberTwo);

                    break;

            case 2: System.out.println(numberOne-numberTwo);

                    break;

            case 3: System.out.println(numberOne*numberTwo);

                    break;

            case 4: System.out.println(numberOne/numberTwo);

                    break;

            default: System.out.println("Enter a valid choice");

                    break;

        }    

        reader.close();

    }

}

 