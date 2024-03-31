package com.exam.its.domain.issue;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {
    public List<IssueEntity> findAll() {
        return List.of(
                new IssueEntity(1, "概要", "説明1"),
                new IssueEntity(2, "概要", "説明2"),
                new IssueEntity(3, "概要", "説明3")
        );
    }
}
