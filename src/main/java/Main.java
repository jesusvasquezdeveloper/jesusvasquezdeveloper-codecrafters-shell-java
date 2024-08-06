import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> SHELL_BUILTIN = List.of("echo", "type", "exit", "pwd");



        while (true) {
            System.out.print("$ ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if ("exit 0".equals(input)) {
                System.exit(0);
            } else if (input.startsWith("echo")) {
                String echoOutput = input.replace("echo ", "");
                System.out.println(echoOutput);

            } else if ("pwd".equals(input)) {
                System.out.println(System.getProperty("user.dir"));

            } else if (input.startsWith("type")) {
                String commandToInspect = input.replace("type ", "");
                String[] pathCommands = System.getenv("PATH").split(":");

                String commandPath;
                if (SHELL_BUILTIN.contains(commandToInspect)) {
                    System.out.println(commandToInspect + " is a shell builtin");
                } else if ((commandPath = findPathCommand(pathCommands, commandToInspect))!= null) {
                    System.out.println(commandToInspect + " is " + commandPath);
                } else {
                    System.out.println(commandToInspect + ": not found");
                }
            } else {
                String[] inputArgs = input.split(" ");
                String command = inputArgs[0];
                String[] pathCommands = System.getenv("PATH").split(":");

                String commandPath = findPathCommand(pathCommands, command);

                if (commandPath!= null) {
                    try {
                        inputArgs[0] = commandPath;

                        InputStream inputStream = Runtime.getRuntime().exec(
                                inputArgs
                        ).getInputStream();

                        inputStream.transferTo(System.out);

                    } catch (IOException e) {
                        System.out.println(e);
                    }

                } else {
                    System.out.println(input + ": command not found");
                }
            }
        }
    }

    private static String findPathCommand(String[] pathCommands, String commandToInspect) {
        for (String path: pathCommands) {
            File file = new File(path + "/" + commandToInspect);

            if (file.exists()) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }
}
