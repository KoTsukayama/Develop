package com.exam.its.web.issue;

import com.exam.its.domain.issue.IssueEntity;
import com.exam.its.domain.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/issues")
    public String showList(Model model){

        model.addAttribute("issueList", issueService.findAll());
        return "issues/list";
    }
}


