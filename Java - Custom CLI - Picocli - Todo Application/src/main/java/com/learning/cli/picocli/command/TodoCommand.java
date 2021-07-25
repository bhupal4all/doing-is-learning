package com.learning.cli.picocli.command;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "todo",
        header = "Todo CLI Application",
        description = "Add/Edit/Delete/List Todo Tasks",
        version = "0.0.1"
)
public class TodoCommand implements Callable<Integer> {

    public static final int SUCCESS = 0;

    @CommandLine.Option(
            names = {"-h", "--help"},
            usageHelp = true
    )
    boolean help;

    @CommandLine.Option(
            names = {"-V", "--version"},
            versionHelp = true,
            description = "print version information and exit"
    )
    boolean version;

    @CommandLine.Option(
            names = {"-m", "--message"},
            description = "Todo Message",
            required = true
    )
    private String message;

    @Override
    public Integer call() throws Exception {

        System.out.println("Adding a Todo with Message.. " + message);

        return SUCCESS;
    }
}
