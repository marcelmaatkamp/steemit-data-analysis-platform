package application.controller.neo4j;

import application.model.mongodb.AccountOperations.Vote;
import application.repository.neo4j.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class Neo4jController {

    // https://docs.spring.io/spring-data/neo4j/docs/current/reference/html/
    // https://docs.spring.io/spring-data/neo4j/docs/current-SNAPSHOT/reference/html/

    @Autowired
    VoteRepository voteRepository;

    @RequestMapping("/api/neo4j/vote")
    public Iterable<application.model.neo4j.Vote> votes() {
        return voteRepository.findAll();
    }

    @RequestMapping("/api/neo4j/vote/{day}")
    public List<Vote> votesToday(@RequestParam(value = "days", defaultValue = "1") int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime daysSinceNow = now.minus(days, ChronoUnit.DAYS);
        return null;
    }

    // MATCH (n) RETURN n LIMIT 1000;
    @RequestMapping("/api/neo4j/all/limit/{items}")
    public List<Vote> getAll(@RequestParam(value = "items", defaultValue = "1000") int limit) {
        return null;
    }

    // MATCH (n) DETACH DELETE n;
    @RequestMapping("/api/neo4j/delete")
    public List<Vote> deleteAll() {
        return null;
    }
}
