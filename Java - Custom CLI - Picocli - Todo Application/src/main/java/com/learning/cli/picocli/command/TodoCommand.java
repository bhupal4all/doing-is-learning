package com.learning.cli.picocli.command;

import com.learning.cli.picocli.command.sub.*;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "todo",
        header = "Todo CLI Application%n",
        description = "Description: Utility to capture the daily todo tasks and manage the tasks%n",
        version = "Version: 0.0.1",
        commandListHeading = "%nList of Todo Commands:%n",
        optionListHeading = "%nOptions:%n",
        helpCommand = true,
        mixinStandardHelpOptions = true,
        subcommandsRepeatable = true,
        subcommands = {
                ListTodoCommand.class,
                AddTodoCommand.class,
                UpdateTodoCommand.class,
                DoneTodoCommand.class,
                DeleteTodoCommand.class
        },
        footerHeading = "%nCopyright:%n",
        footer = "(C) Ranga Bhupal"
)
public class TodoCommand implements Callable<Integer> {
    public static final int SUCCESS = 0;
    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    public static void main(String[] args) {
        System.exit(new CommandLine(new TodoCommand()).execute(args));
    }

    @Override
    public Integer call() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
