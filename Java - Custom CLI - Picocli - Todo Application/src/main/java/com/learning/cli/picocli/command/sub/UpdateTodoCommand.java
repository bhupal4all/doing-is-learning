package com.learning.cli.picocli.command.sub;

import com.learning.cli.mapdb.service.TodoService;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "update",
        aliases = {"modify"},
        header = "Update Task",
        description = "Updating the Task with new Message",
        version = "0.0.1"
)
public class UpdateTodoCommand implements Callable<Integer> {
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
            names = {"--id"},
            description = "Todo ID",
            required = true
    )
    private Long taskId;
    @CommandLine.Option(
            names = {"-m", "--message"},
            description = "New Todo Message",
            required = true
    )
    private String message;

    public UpdateTodoCommand() {
        this.service = new TodoService();
    }

    @Override
    public Integer call() throws Exception {
        boolean isUpdated = this.service.updateTask(taskId, message);
        System.out.println("Todo ID " + taskId + " is " + (isUpdated ? "Updated" : "Failed"));
        return SUCCESS;
    }
}
