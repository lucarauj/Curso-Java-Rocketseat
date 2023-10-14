package br.com.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        User username = repository.findByUsername(user.getUsername());
        if(username != null) {
//          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username já utilizado");
            return ResponseEntity.badRequest().body("Username já utilizado");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordHashred);
        var userCreated = repository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();

//      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        return ResponseEntity.created(location).body(userCreated);
    }
}
