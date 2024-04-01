package com.exam.its.web.issue;

import com.exam.its.domain.issue.IssueEntity;
import com.exam.its.domain.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public String showList(Model model){

        model.addAttribute("issueList", issueService.findAll());
        return "issues/list";
    }

    // GET /issues/creationForm
    @GetMapping("/creationForm")
    public String showCreationForm(){
        return "issues/creationForm";
    }

    // POST /isssue
    @PostMapping
    public String create(IssueForm form, Model model){
        // TODO　データの永続化
        return showList(model); // TODO リロードボタン対策が必要
    }
}


