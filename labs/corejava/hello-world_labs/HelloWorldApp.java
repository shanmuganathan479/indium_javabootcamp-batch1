/**
* First Java Program Demo
*/
public class HelloWorldApp {
    // main method
    public static void main(String[] args) {
        /* print statement
        * demo
      */
        System.out.println("Hello World!!!");

 

        try {
            // sleep for 5 mins
            Thread.sleep(1000*60*5);
        }catch(InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
