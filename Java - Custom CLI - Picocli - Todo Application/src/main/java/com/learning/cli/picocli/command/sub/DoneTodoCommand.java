package com.learning.cli.picocli.command.sub;

import com.learning.cli.mapdb.service.TodoService;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "done",
        header = "Todo Completed",
        description = "Update Task as Completed",
        optionListHeading = "%nOptions:%n",
        version = "Todo Done Version: 0.0.1",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*'
)
public class DoneTodoCommand implements Callable<Integer> {
    public static final int SUCCESS = 0;
    TodoService service = null;

    @CommandLine.Option(
            names = {"--id"},
            description = "Todo ID",
            required = true,
            paramLabel = "TodoID"
    )
    private Long[] taskIds;

    public DoneTodoCommand() {
        this.service = new TodoService();
    }

    @Override
    public Integer call() throws Exception {
        Arrays.stream(taskIds).forEach(this.service::completeTask);
        System.out.println("Request Accepted");
        return SUCCESS;
    }
}
