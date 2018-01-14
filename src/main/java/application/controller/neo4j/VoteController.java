package application.controller.neo4j;

import application.model.neo4j.Vote;
import application.repository.neo4j.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    VoteRepository voteRepository;

    @RequestMapping("/{id}")
    Vote showUserForm(@PathVariable("id") Vote vote) {
        return vote;
    }

    @RequestMapping("/all")
    Iterable<Vote> showAll() {
        return voteRepository.findAll();
    }

}
