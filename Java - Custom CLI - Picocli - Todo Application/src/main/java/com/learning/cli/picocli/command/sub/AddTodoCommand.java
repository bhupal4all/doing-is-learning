package com.learning.cli.picocli.command.sub;

import com.learning.cli.mapdb.service.TodoService;
import picocli.CommandLine;

import java.util.Date;
import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "add",
        header = "Add Todo",
        description = "Adding Todo to the List of Tasks",
        optionListHeading = "%nOptions:%n",
        version = "Todo Add Version: 0.0.2",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*'
)
public class AddTodoCommand implements Callable<Integer> {
    public static final int SUCCESS = 0;
    TodoService service;

    @CommandLine.Option(
            names = {"-m", "--message"},
            description = "Todo Message",
            required = true
    )
    private String message;

    @CommandLine.Option(
            names = {"--create-date"},
            description = "Task Creation Date",
            required = false
    )
    private Date createDate;

    public AddTodoCommand() {
        this.service = new TodoService();
    }

    @Override
    public Integer call() throws Exception {
        Long taskId = this.service.createTask(message, createDate);
        System.out.println("New Task Created and ID is " + taskId);
        return SUCCESS;
    }
}
