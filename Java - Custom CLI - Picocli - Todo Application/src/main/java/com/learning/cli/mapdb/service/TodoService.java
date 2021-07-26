package com.learning.cli.mapdb.service;

import com.learning.cli.mapdb.entity.Todo;
import org.mapdb.Atomic;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class TodoService implements Serializable {
    public static final String TODO_MAPDB = "todo.mapdb";
    public static final String TODO = "todo";
    DB db = null;
    ConcurrentMap<Long, Todo> map = null;
    Comparator<Todo> todoComparator = (o1, o2) -> o1.getCreatedOn().compareTo(o2.getCreatedOn());

    public void start() {
        this.db = DBMaker.fileDB(TODO_MAPDB).make();
        this.map = db.hashMap(TODO, Serializer.LONG, Serializer.JAVA).createOrOpen();
    }

    public void shutdown() {
        this.db.close();
    }

    public List<Todo> getTasks() {
        this.start();
        List<Todo> collect = map.values().stream()
                .sorted(todoComparator)
                .collect(Collectors.toList());
        this.shutdown();
        return collect;
    }

    public List<Todo> getTasks(boolean isDone) {
        this.start();
        List<Todo> collect = map.values().stream()
                .sorted(todoComparator)
                .filter(todo -> todo.isDone() == isDone)
                .collect(Collectors.toList());
        this.shutdown();
        return collect;
    }

    public Todo getTaskById(Long taskId) {
        this.start();
        Todo todo = map.get(taskId);
        this.shutdown();
        return todo;
    }

    public Long createTask(String message) {
        Todo task = new Todo(message);
        return createTask(task);
    }

    public Long createTask(Todo task) {
        this.start();
        Atomic.Long id = db.atomicLong("id").createOrOpen();
        task.setId(id.getAndAdd(1));
        map.put(task.getId(), task);
        this.shutdown();
        return task.getId();
    }

    public boolean updateTask(Long taskId, String message) {
        Todo task = getTaskById(taskId);
        if (Objects.nonNull(task)) {
            task.setMessage(message);
            this.start();
            map.put(task.getId(), task);
            this.shutdown();
        }
        return true;
    }

    public boolean deleteTask(Long taskId) {
        this.start();
        map.remove(taskId);
        this.shutdown();
        return true;
    }

    public boolean completeTask(Long taskId) {
        Todo task = getTaskById(taskId);
        if (Objects.nonNull(task)) {
            task.setDone(true);
            task.setCompletedOn(new Date());
            this.start();
            map.put(task.getId(), task);
            this.shutdown();
        }
        return true;
    }

    public Long createTask(String message, Date createDate) {
        Todo task = new Todo(message);
        if (Objects.nonNull(createDate)) {
            task.setCreatedOn(createDate);
        }
        return createTask(task);
    }
}
