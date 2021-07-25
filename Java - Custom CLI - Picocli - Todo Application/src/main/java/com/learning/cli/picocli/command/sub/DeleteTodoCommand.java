package com.learning.cli.picocli.command.sub;

import com.learning.cli.mapdb.service.TodoService;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "delete",
        aliases = {"remove"},
        header = "Delete Todo",
        description = "Deleting Todo From the List",
        version = "0.0.1"
)
public class DeleteTodoCommand implements Callable<Integer> {
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
            required = true,
            split = ","
    )
    private Long[] taskIds;

    public DeleteTodoCommand() {
        this.service = new TodoService();
    }

    @Override
    public Integer call() throws Exception {
        Arrays.asList(taskIds).forEach(this.service::deleteTask);
        System.out.println("Request Accepted");
        return SUCCESS;
    }
}
