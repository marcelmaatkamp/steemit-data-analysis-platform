package application.controller.steemdata;

import application.model.mongodb.AccountOperations.Vote;
import application.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController("/api/steemdata/vote")
public class SteemDataVoteController {

    @Autowired
    MongoService mongoService;

    @RequestMapping("/today")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesToday() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfToday = now.with(LocalTime.MIDNIGHT);
        return mongoService.getVotesBetween(startOfToday, now);
    }

    @RequestMapping("/last24h")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesLast24h() {
        return steemDataVotesDays(1);
    }

    @RequestMapping("/{days}")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesDays(@RequestParam(value = "days", defaultValue = "1") int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime daysSinceNow = now.minus(days, ChronoUnit.DAYS);
        return mongoService.getVotesBetween(daysSinceNow, now);
    }


    @RequestMapping("/yesterday")
    public List<application.model.mongodb.AccountOperations.Vote> steemDataVotesYesterday() {
        LocalDateTime dayBeforeYesterday = LocalDate.now().minusDays(2).atStartOfDay();
        LocalDateTime yesterday = LocalDate.now().minusDays(1).atStartOfDay();
        return mongoService.getVotesBetween(dayBeforeYesterday, yesterday);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    Page<Vote> list(Pageable pageable) {
        return mongoService.listAllByPage(pageable);
    }

}
