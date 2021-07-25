package com.learning.cli.picocli.command.sub;

import com.learning.cli.mapdb.service.TodoService;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "add",
        header = "Add Todo",
        description = "Adding Todo to the List of Tasks",
        version = "0.0.1"
)
public class AddTodoCommand implements Callable<Integer> {
    public static final int SUCCESS = 0;
    TodoService service;

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

    public AddTodoCommand() {
        this.service = new TodoService();
    }

    @Override
    public Integer call() throws Exception {
        Long taskId = this.service.createTask(message);
        System.out.println("New Task Created and ID is " + taskId);
        return SUCCESS;
    }
}
