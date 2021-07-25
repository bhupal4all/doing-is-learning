package com.learning.cli.picocli;

import com.learning.cli.picocli.command.TodoCommand;
import picocli.CommandLine;

public class TodoCommandExec {
    public static void main(String[] args) {
        int exitCode = -1;

        System.out.println("\n-----------------\nHelp\n-----------------");
        exitCode = new CommandLine(new TodoCommand()).execute("--help");

        System.out.println("\n-----------------\nVersion\n-----------------");
        exitCode = new CommandLine(new TodoCommand()).execute("--version");

        System.out.println("\n-----------------\nOption message\n-----------------");
        exitCode = new CommandLine(new TodoCommand()).execute("--message=Complete Program");

    }
}
