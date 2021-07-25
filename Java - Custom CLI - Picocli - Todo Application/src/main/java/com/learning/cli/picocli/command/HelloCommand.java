package com.learning.cli.picocli.command;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "hello",
        header = "Greeting CLI Command",
        description = "Command prints the Greetings Message",
        version = "1.0.0"
)
public class HelloCommand implements Callable<Integer> {

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
            names = {"-u", "--user"},
            description = "Requester Name",
            defaultValue = "World"
    )
    String user;

    @Override
    public Integer call() throws Exception {
        System.out.println("Welcome " + user);
        return 0;
    }
}
