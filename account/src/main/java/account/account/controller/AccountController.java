package account.account.controller;

import account.account.domain.Account;
import account.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/hi")
    public String hello() {
        return "Hi from accounts";
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
    }

    @GetMapping("/insert")
    public ResponseEntity save(@RequestParam(name = "firstname") String firstname,
                               @RequestParam(name = "lastname") String lastname) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.save(new Account(firstname, lastname)));
    }

    @GetMapping("/invoke")
    public ResponseEntity invokeMethod() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.invokeEndpoint());
    }

    @PostMapping("/create-user")
    public ResponseEntity createUser(@RequestParam(name = "firstname", required = true) String firstname,
                                     @RequestParam(name = "lastname", required = true) String lastname) throws IOException {
        accountService.invokeEndpointCreateUser(firstname, lastname);
        return ResponseEntity.status(HttpStatus.CREATED).body("User has been created !");
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity invokeEndpointDeleteUser(@PathVariable("id") Long id) throws IOException {
        accountService.invokeEndpointDeleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Deleted !");
    }

}
