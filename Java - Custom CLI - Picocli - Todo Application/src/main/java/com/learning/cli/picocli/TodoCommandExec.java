package com.learning.cli.picocli;

import com.learning.cli.picocli.command.TodoCommand;
import picocli.CommandLine;

public class TodoCommandExec {
    public static void main(String[] args) {
        int exitCode = -1;

//        System.out.println("\n-----------------\nHelp\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("--help");
//
//        System.out.println("\n-----------------\nList Tasks Help\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("list", "-h");
//
//        System.out.println("\n-----------------\nAdd Help\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("add", "-h");
//
//        System.out.println("\n-----------------\nUpdate Help\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("update", "-h");
//
//        System.out.println("\n-----------------\nDone Help\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("done", "-h");
//
//        System.out.println("\n-----------------\nDelete Help\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("delete", "-h");
//
//        System.out.println("\n-----------------\nList Tasks\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("list", "--format=default");
//
//        System.out.println("\n-----------------\nAdd Task Help\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("add", "-V");
//
//        System.out.println("\n-----------------\nAdd Task\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("add", "--message=Task" + Math.round(Math.random() * 1000));
        exitCode = new CommandLine(new TodoCommand()).execute("add", "--create-date=2021-07-01", "-m=Task" + Math.round(Math.random() * 1000));
        if (exitCode == 0) new CommandLine(new TodoCommand()).execute("list", "--short");
//
//        System.out.println("\n-----------------\nList Completed Tasks in Short\n-----------------");
//        exitCode = new CommandLine(new TodoCommand()).execute("list", "--status=complete", "--short");

    }
}
