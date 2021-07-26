package com.learning.cli.picocli.command;

import com.learning.cli.picocli.command.sub.*;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "todo",
        header = "Todo CLI Application",
        description = "Add/Edit/Delete/List Todo Tasks",
        version = "Version: 0.0.1",
        commandListHeading = "List of Todo Commands:%n",
        optionListHeading = "%nOptions:%n",
        mixinStandardHelpOptions = true,
        subcommandsRepeatable = true,
        subcommands = {
                ListTodoCommand.class,
                AddTodoCommand.class,
                UpdateTodoCommand.class,
                DoneTodoCommand.class,
                DeleteTodoCommand.class
        }
)
public class TodoCommand implements Callable<Integer> {

    public static final int SUCCESS = 0;

    public static void main(String[] args) {
        new CommandLine(new TodoCommand()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        return SUCCESS;
    }
}
