package com.example.gr1.controller;

import com.example.gr1.model.Task;
import com.example.gr1.model.TaskStatus;
import com.example.gr1.repository.TaskRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PutMapping("/add")
    public Task add(@RequestParam String description, 
                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(TaskStatus.OPEN);
        task.setDueDate(dueDate);
        return taskRepository.save(task);
    }
    
    @PatchMapping("/update-due-date/{id}")
    public Task updateDueDate(@PathVariable Long id, 
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDueDate(dueDate);
        return taskRepository.save(task);
    }

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/show")
    public List<Task> show() {
        return taskRepository.findAll();
    }

    @PostMapping("/complete/{id}")
    public Task completeTask(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setStatus(TaskStatus.COMPLETED);
        return taskRepository.save(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
