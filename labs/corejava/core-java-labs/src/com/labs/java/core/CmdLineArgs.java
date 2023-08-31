public class CmdLineArgs {
    public static void main(String[] args) {
        if(args.length ==0) {
            System.out.println("no command line arguments passed");
//            return;
            System.exit(0);
        }
        System.out.println(args[0].length());
        System.out.println(args[1].toLowerCase());
        System.out.println(args[2]);
        System.out.println(args[3]);
        System.out.println(args[4].substring(4,11));
        System.out.println(args[5].split(","));

    }
}