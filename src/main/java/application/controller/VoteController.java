package application.controller;

import application.model.neo4j.Vote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/votes")
public class VoteController {

    @RequestMapping("/{id}")
    String showUserForm(@PathVariable("id") Vote vote, Model model) {
        model.addAttribute("vote", vote);
        return "userForm";
    }

    @RequestMapping("/all")
    ModelAndView showAll(Vote vote, ModelAndView modelAndView) {
        modelAndView.addObject("vote", vote);
        return modelAndView;
    }

}
