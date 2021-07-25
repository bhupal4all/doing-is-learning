package com.learning.cli.picocli;

import com.learning.cli.picocli.command.TodoCommand;
import picocli.CommandLine;

public class TodoCommandExec {
    public static void main(String[] args) {
        int exitCode = -1;

        System.out.println("\n-----------------\nHelp\n-----------------");
        exitCode = new CommandLine(new TodoCommand()).execute("--help");

        System.out.println("\n-----------------\nList Tasks Help\n-----------------");
        exitCode = new CommandLine(new TodoCommand()).execute("list", "-h");

        System.out.println("\n-----------------\nList Tasks\n-----------------");
        exitCode = new CommandLine(new TodoCommand()).execute("list", "--format=default");

        System.out.println("\n-----------------\nList Tasks in Short\n-----------------");
        exitCode = new CommandLine(new TodoCommand()).execute("add", "--message=" + (Math.random() * 1000));

        System.out.println("\n-----------------\nList Tasks in Short\n-----------------");
        exitCode = new CommandLine(new TodoCommand()).execute("list", "--format=short");

    }
}
