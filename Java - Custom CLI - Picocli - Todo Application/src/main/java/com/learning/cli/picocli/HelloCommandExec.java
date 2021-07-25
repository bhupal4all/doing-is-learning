package com.learning.cli.picocli;

import com.learning.cli.picocli.command.HelloCommand;
import picocli.CommandLine;

public class HelloCommandExec {
    public static void main(String[] args) {
        int exitCode = -1;

        System.out.println("\n-----------------\nHelp\n-----------------");
        exitCode = new CommandLine(new HelloCommand()).execute("--help");

        System.out.println("\n-----------------\nVersion\n-----------------");
        exitCode = new CommandLine(new HelloCommand()).execute("--version");

        System.out.println("\n-----------------\nDefault\n-----------------");
        exitCode = new CommandLine(new HelloCommand()).execute();

        System.out.println("\n-----------------\nWith Option -u\n-----------------");
        exitCode = new CommandLine(new HelloCommand()).execute("-u=Bhupal");

        System.out.println("\n-----------------\nWith Full Option --user\n-----------------");
        exitCode = new CommandLine(new HelloCommand()).execute("--user=Bhupal");

    }
}
