package com.learning.cli.mapdb;

import com.learning.cli.mapdb.entity.Todo;
import com.learning.cli.mapdb.service.TodoService;

import java.util.Date;

public class MapDBApplication {
    public static void main(String[] args) {

        TodoService service = new TodoService();

        service.createTask("Default Task : " + new Date());
        Long taskOneId = service.createTask("Task One");
        System.out.println(service.getTaskById(taskOneId));

        service.updateTask(taskOneId, "Task One: Message Updated");
        System.out.println(service.getTaskById(taskOneId));

        service.completeTask(taskOneId);
        System.out.println(service.getTaskById(taskOneId));

        service.deleteTask(taskOneId);
        Todo taskById = service.getTaskById(taskOneId);
        System.out.println("taskById = " + taskById);

        System.out.println("* * * All Tasks * * *");
        service.getTasks().stream().forEach(System.out::println);

        service.shutdown();
    }

}
