import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {
            System.out.print("$ ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if ("exit 0".equals(input)) {
                System.exit(0);
            } else if (input.startsWith("echo")) {
                String echoOutput = input.replace("echo ", "");
                System.out.println(echoOutput);

            } else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
