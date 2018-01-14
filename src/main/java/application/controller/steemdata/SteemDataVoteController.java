package application.controller.steemdata;

import application.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class SteemDataVoteController {

    @Autowired
    MongoService mongoService;

    @RequestMapping("/api/steemdata/vote/today")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesToday() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfToday = now.with(LocalTime.MIDNIGHT);
        return mongoService.getVotesBetween(startOfToday, now);
    }

    @RequestMapping("/api/steemdata/vote/last24h")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesLast24h() {
        return steemDataVotesDays(1);
    }

    @RequestMapping("/api/steemdata/vote/{days}")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesDays(@RequestParam(value = "days", defaultValue = "1") int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime daysSinceNow = now.minus(days, ChronoUnit.DAYS);
        return mongoService.getVotesBetween(daysSinceNow, now);
    }


    @RequestMapping("/api/vote/steemdata/yesterday")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesYesterday() {
        LocalDateTime dayBeforeYesterday = LocalDate.now().minusDays(2).atStartOfDay();
        LocalDateTime yesterday = LocalDate.now().minusDays(1).atStartOfDay();
        return mongoService.getVotesBetween(dayBeforeYesterday, yesterday);
    }

}
