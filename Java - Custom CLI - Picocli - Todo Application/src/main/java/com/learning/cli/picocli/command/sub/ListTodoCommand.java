package com.learning.cli.picocli.command.sub;

import com.learning.cli.mapdb.entity.Todo;
import com.learning.cli.mapdb.service.TodoService;
import picocli.CommandLine;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;


@CommandLine.Command(
        name = "list",
        header = "Listing Tasks",
        description = "Listing Tasks as per criteria",
        version = "0.0.1"
)
public class ListTodoCommand implements Callable<Integer> {
    public static final int SUCCESS = 0;
    private final String defaultFormat = "Task ID: %5d\t%s\nMessage: %s";
    private final String shortFormat = "%4d\t%3s\t%s";

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
            description = "Todo ID"
    )
    private Long taskId;
    @CommandLine.Option(
            names = {"-s", "--status"},
            description = "Status of the Tasks",
            defaultValue = "all"
    )
    private String status;
    @CommandLine.Option(
            names = {"-f", "--format"},
            description = "Format of the Tasks List",
            defaultValue = "default"
    )

    private String format;

    public ListTodoCommand() {
        this.service = new TodoService();
    }

    @Override
    public Integer call() throws Exception {

        BiFunction<String, Todo, String> formatBiFunction = (format, todo) -> {
            if ("short".equals(format)) {
                return String.format(shortFormat, todo.getId(), todo.isDone() ? "[x]" : "[ ]", todo.getMessage());
            } else {
                return String.format(defaultFormat, todo.getId(), todo.isDone() ? "[x]" : "[ ]", todo.getMessage());
            }
        };

        if (Objects.nonNull(taskId)) {
            Todo task = this.service.getTaskById(taskId);
            if (task == null) {
                System.err.println("Task Not Found");
            } else {
                System.out.println(formatBiFunction.apply(format, task));
            }
        } else {
            this.service.getTasks()
                    .stream()
                    .map(todo -> formatBiFunction.apply(format, todo))
                    .forEach(System.out::println);
        }
        return SUCCESS;
    }
}
