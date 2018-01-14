package application.controller;

import application.model.json.Vote;
import application.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class VoteController {

    @Autowired
    MongoService mongoService;

    @RequestMapping("/api/steemdata/vote/today")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesToday(@RequestParam(value="name", defaultValue="World") String name) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfToday = now.with(LocalTime.MIDNIGHT);
        return mongoService.getVotesBetween(startOfToday,now);
    }

    @RequestMapping("/api/steemdata/vote/last24h")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesLast24h(@RequestParam(value="name", defaultValue="World") String name) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minus(1, ChronoUnit.DAYS);
        return mongoService.getVotesBetween(yesterday,now);
    }


    @RequestMapping("/api/vote/steemdata/yesterday")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesYesterday(@RequestParam(value="name", defaultValue="World") String name) {
        LocalDateTime dayBeforeYesterday = LocalDate.now().minusDays(2).atStartOfDay();
        LocalDateTime yesterday = LocalDate.now().minusDays(1).atStartOfDay();
        return mongoService.getVotesBetween(dayBeforeYesterday, yesterday);
    }

}
