package com.example.com.example.controllers;

import com.example.com.example.entity.Task;
import com.example.com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        for (Task t : taskIterable) {
            tasks.add(t);
        }

        model.addAttribute("tasks", tasks);

        return "index";
    }



    //Создание нового дела
    @PostMapping(value = "/")
    public String createTodo(@ModelAttribute(value = "val") String task) {

        taskRepository.save(new Task(task));

        return "index";
    }

    //возвращаем весь список
    @GetMapping(value = "/todolist")
    public Iterable<Task> getTodoList() {
        return taskRepository.findAll();
    }

    //возвращаем по ключу
    @GetMapping(value = "/todolist/{key}")
    public Task getById(@PathVariable("key") int id) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        for (Task t : taskIterable) {
            taskList.add(t);
        }
        for(Task t: taskList){
            if (t.getId() == id){
                return taskRepository.findByIdOvveride(id);
            }
        }
        return new Task(404, "Not Found");
    }

    //обновляем дело по ключу
    @PutMapping(value = "/todolist/")
    @Transactional
    public int mergeCaseById(@RequestBody Task task) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        for (Task t : taskIterable) {
            taskList.add(t);
        }
        for(Task t: taskList){
            if (t.getId() == task.getId()){
                Task temp = taskRepository.findById(task.getId()).get();
                temp.setText(task.getText());
                taskRepository.save(temp);
                return HttpStatus.OK.value();
            }
        }
        return HttpStatus.NOT_FOUND.value();
    }

    //Удаляем все дела
    @DeleteMapping(value = "/todolist")
    public int deleteAllCase() {
        taskRepository.deleteAll();
        return HttpStatus.OK.value();
    }

    //Удаляем дело по ключу
    @DeleteMapping(value = "/todolist/{key}")
    public int deleteCaseById(@PathVariable("key") int id) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        for (Task t : taskIterable) {
            taskList.add(t);
        }
        for(Task t: taskList){
            if (t.getId() == id){
                taskRepository.deleteById(id);
                return HttpStatus.OK.value();
            }
        }
        return HttpStatus.NOT_FOUND.value();
    }
    private int getSizeTodoList(){
        ArrayList<Task> arrayList = new ArrayList<>();
        for(Task t: taskRepository.findAll()){
            arrayList.add(t);
        }
        return arrayList.size();
    }
}


