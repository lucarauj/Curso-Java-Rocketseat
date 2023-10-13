package br.com.todolist.task;

import br.com.todolist.user.User;
import br.com.todolist.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody Task task) {
        Task title = taskRepository.findByTitleIgnoreCase(task.getTitle());
        if(title != null) {
//          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Título já utilizado");
            return ResponseEntity.badRequest().body("Título já utilizado");
        }

//        Optional userId = userRepository.findById(task.getIdUser());
//        if(userId.isEmpty()) {
//          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
//            return ResponseEntity.badRequest().body("Usuário não encontrado");
//        }

        var taskCreated = taskRepository.save(task);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(taskCreated.getId()).toUri();

//      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        return ResponseEntity.created(location).body(taskCreated);
    }

    @GetMapping
    public List<Task> create() {
        return taskRepository.findAll();
    }
}
