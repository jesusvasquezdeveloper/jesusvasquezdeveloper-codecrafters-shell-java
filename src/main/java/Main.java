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

            } else if (input.startsWith("type")) {
                String commandToInspect = input.replace("type ", "");
                switch (commandToInspect) {
                    case "echo", "exit", "type":
                        System.out.println(commandToInspect + " is a shell builtin");
                        break;
                    default:
                        System.out.println(commandToInspect + ": not found");
                }

            } else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
