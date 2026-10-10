package com.library.service;

import com.library.entity.Book;
import com.library.entity.Issue;
import com.library.entity.Student;
import com.library.repository.BookRepository;
import com.library.repository.IssueRepository;
import com.library.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public IssueService(IssueRepository issueRepository, BookRepository bookRepository, StudentRepository studentRepository) {
        this.issueRepository = issueRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue issueBook(Long bookId, Long studentId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (book == null || student == null || book.getQuantity() <= 0) return null;
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        Issue issue = new Issue();
        issue.setBook(book);
        issue.setStudent(student);
        issue.setIssueDate(LocalDate.now());
        issue.setReturned(false);
        return issueRepository.save(issue);
    }

    public Issue returnBook(Long issueId) {
        Issue issue = issueRepository.findById(issueId).orElse(null);
        if (issue == null || issue.isReturned()) return null;
        issue.setReturned(true);
        issue.setReturnDate(LocalDate.now());
        Book book = issue.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        return issueRepository.save(issue);
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }
}
