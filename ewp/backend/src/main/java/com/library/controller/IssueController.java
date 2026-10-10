package com.library.controller;

import com.library.entity.Issue;
import com.library.service.IssueService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "*")
public class IssueController {
    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<Issue> getAll() { return issueService.getAllIssues(); }

    @PostMapping("/issue")
    public Issue issueBook(@RequestParam Long bookId, @RequestParam Long studentId) {
        return issueService.issueBook(bookId, studentId);
    }

    @PutMapping("/return/{id}")
    public Issue returnBook(@PathVariable Long id) { return issueService.returnBook(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { issueService.deleteIssue(id); }
}
