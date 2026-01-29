package com.jobtracker.job_tracker.repository;

import com.jobtracker.job_tracker.entity.JobApplication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobApplication,Long> {
    List<JobApplication> findByUserId(Long userId);
}
