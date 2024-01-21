package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.launchcode.techjobsmvc.models.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("search", new Search());
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm, @ModelAttribute Search search){
        ArrayList<Job> jobs;
        jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("columns", columnChoices);
        search.setType(searchType);
        model.addAttribute("jobs", jobs);
        return "search";
    }

}

