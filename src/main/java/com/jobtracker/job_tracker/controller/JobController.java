package com.jobtracker.job_tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.job_tracker.entity.JobApplication;
import com.jobtracker.job_tracker.service.JobService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000")

public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService=jobService;
    }

    // create job
    @PostMapping
    public JobApplication createJob(@RequestBody JobApplication job,@RequestParam Long userId){
        return jobService.createJob(job, userId);
    }

    // get jobs for logged in user
    @GetMapping
    public List<JobApplication> getJobs(@RequestParam Long userId){
        return jobService.getJobsByUser(userId);
    }

    // update job
    @PutMapping("/{jobId}")
    public JobApplication updateJob(@PathVariable Long jobId,
                                    @RequestBody JobApplication job,
                                    @RequestParam Long userId)
    {
        return jobService.updateJob(jobId,job, userId);
    }

    // delete job
    @DeleteMapping("/{jobId}")
    public void deleteJob(@PathVariable Long jobId,
                          @RequestParam Long userId)
    {
        jobService.deleteJob(jobId, userId);
    }

}
