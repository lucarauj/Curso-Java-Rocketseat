package br.com.todolist.task;

import br.com.todolist.user.UserRepository;
import br.com.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody Task task, HttpServletRequest request) {

        task.setIdUser((UUID) request.getAttribute("idUser"));
        var currentDate = LocalDateTime.now();

        if(task.getStartAt().isBefore(currentDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de início precisa ser futura");
        }

        if(task.getStartAt().isAfter(task.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data final precisa ser posterior à data de início");
        }

        var taskCreated = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }

    @GetMapping
    public List<Task> listByIdUser(HttpServletRequest request) {
        var taskList = taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));
        return taskList;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody Task task, HttpServletRequest request) {

        var taskResult = taskRepository.findById(id).orElse(null);

        if(taskResult == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task não encontrada");
        }

        var idUser = request.getAttribute("idUser");
        if(!taskResult.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task não pertence ao usuário");
        }

        Utils.CopyNonNullProperties(task, taskResult);
        var taskUpdate = taskRepository.save(taskResult);
        return ResponseEntity.status(HttpStatus.OK).body(taskUpdate);
    }
}
