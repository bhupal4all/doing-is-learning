package com.learning.cli.picocli.command.sub.options;

import java.util.ArrayList;
import java.util.Arrays;

public class ListTodoFormatOptions extends ArrayList<String> {
    public ListTodoFormatOptions() {
        super(Arrays.asList("default", "short"));
    }
}
