package com.learning.cli.picocli.command.sub;

import com.learning.cli.mapdb.entity.Todo;
import com.learning.cli.mapdb.service.TodoService;
import com.learning.cli.picocli.command.sub.options.ListTodoFormatOptions;
import picocli.CommandLine;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;


@CommandLine.Command(
        name = "list",
        header = "Listing Tasks",
        description = "Listing Tasks as per criteria",
        optionListHeading = "%nOptions:%n",
        version = "Version: 0.0.1",
        mixinStandardHelpOptions = true
)
public class ListTodoCommand implements Callable<Integer> {
    public static final int SUCCESS = 0;
    private final String defaultListFormat = "Task ID: %5d %s\nDate:%s\nMessage: %s";
    private final String shortListFormat = "%4d %s %s %s";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
    TodoService service;


    @CommandLine.Option(
            names = {"--id"},
            description = "Todo ID"
    )
    private Long[] taskId;
    @CommandLine.Option(
            names = {"-s", "--status"},
            description = "Status of the Tasks %n Options are ${COMPLETION-CANDIDATES}",
            defaultValue = "all"
    )
    private Status status;
    @CommandLine.Option(
            names = {"-f", "--format"},
            description = "Format of the Tasks List %n Options are ${COMPLETION-CANDIDATES}",
            completionCandidates = ListTodoFormatOptions.class,
            defaultValue = "all"
    )
    private String format;
    @CommandLine.Option(
            names = {"--short"},
            description = "Format as Short"
    )
    private boolean shortFormat;

    public ListTodoCommand() {
        this.service = new TodoService();
    }

    @Override
    public Integer call() throws Exception {
        String listFormat = shortFormat ? "short" : format;

        Function<Status, List<Todo>> taskFunction = status -> {
            if (Status.complete.equals(status)) {
                return service.getTasks(true);
            } else if (Status.not.equals(status) || Status.notcomplete.equals(status)) {
                return service.getTasks(false);
            } else {
                return service.getTasks();
            }
        };

        BiFunction<String, Todo, String> formatBiFunction = (format, todo) -> {
            if ("short".equals(format)) {
                return String.format(shortListFormat,
                        todo.getId(), todo.isDone() ? "[x]" : "[ ]", dateFormat.format(todo.getCreatedOn()), todo.getMessage());
            } else {
                return String.format(defaultListFormat,
                        todo.getId(), todo.isDone() ? "[x]" : "[ ]", dateFormat.format(todo.getCreatedOn()), todo.getMessage());
            }
        };

        if (Objects.nonNull(taskId)) {
            Arrays.asList(taskId).forEach(id -> {
                Todo task = this.service.getTaskById(id);
                if (task == null) {
                    System.err.println(id + " Task Not Found");
                } else {
                    System.out.println(formatBiFunction.apply(listFormat, task));
                }
            });
        } else {
            taskFunction.apply(status).stream()
                    .map(todo -> formatBiFunction.apply(listFormat, todo))
                    .forEach(System.out::println);
        }
        return SUCCESS;
    }

    enum Status {all, complete, not, notcomplete}
}
