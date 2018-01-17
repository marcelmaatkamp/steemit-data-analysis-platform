package application.controller.neo4j;

import application.model.neo4j.Account;
import application.repository.neo4j.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    AccountRepository authorRepository;

    @RequestMapping("/id/{id}/{depth}")
    Account findById(@PathVariable("id") Account author, @Depth int depth) {
        return author;
    }

    @RequestMapping("/name/{name}/{depth}")
    Account findByName(@PathVariable("name") Account author, @Depth int depth) {
        return author;
    }


}
