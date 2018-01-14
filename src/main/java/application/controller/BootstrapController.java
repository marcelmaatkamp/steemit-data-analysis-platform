package application.controller;

import application.service.Bootstrap;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootstrapController {

    @Autowired
    Bootstrap bootstrap;

    @RequestMapping("/api/bootstrap/votes/{days}")
    public String boostrapVotesDays(@RequestParam(value = "days", defaultValue = "1") int days) throws SteemResponseException, SteemCommunicationException {
        int votes = bootstrap.votes(days);
        return "Imported " + votes + " votes";
    }

    @RequestMapping("/api/bootstrap/votes/last24h")
    public String boostrapVotesLast24h() throws SteemResponseException, SteemCommunicationException {
        return boostrapVotesDays(1);
    }

}
