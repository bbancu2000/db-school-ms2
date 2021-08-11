package user.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.user.domain.User;
import user.user.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hi")
    public String hello(){
        return "Hi from users";
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/insert")
    public ResponseEntity save(@RequestParam(name = "firstname") String firstname,
                               @RequestParam(name = "lastname") String lastname) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(new User(firstname,lastname)));
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("DELETED !");
    }



}
