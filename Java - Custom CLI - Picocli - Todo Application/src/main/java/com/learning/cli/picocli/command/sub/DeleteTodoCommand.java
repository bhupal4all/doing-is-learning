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
        optionListHeading = "%nOptions:%n",
        version = "Todo Delete Version: 0.0.1",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*'
)
public class DeleteTodoCommand implements Callable<Integer> {
    public static final int SUCCESS = 0;
    TodoService service;

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
