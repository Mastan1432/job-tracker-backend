package com.jobtracker.job_tracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jobtracker.job_tracker.entity.JobApplication;
import com.jobtracker.job_tracker.entity.JobStatus;
import com.jobtracker.job_tracker.entity.User;
import com.jobtracker.job_tracker.repository.JobRepository;
import com.jobtracker.job_tracker.repository.UserRepository;

@Service
public class JobService {
    
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository,UserRepository userRepository){
        this.jobRepository=jobRepository;
        this.userRepository=userRepository;
    }

    // create job
    public JobApplication createJob(JobApplication job,Long userId){
        User user=userRepository.findById(userId)
                   .orElseThrow(()-> new RuntimeException("User not found"));

        job.setUser(user);
        job.setAppliedDate(LocalDate.now());
        job.setStatus(JobStatus.APPLIED);

        return jobRepository.save(job);   
    }

    // get all jobs for a user
    public List<JobApplication> getJobsByUser(Long userId){
        return jobRepository.findByUserId(userId);
    }

    // updateJob
    public JobApplication updateJob(Long jobId,JobApplication updatedJob,Long userId){
        JobApplication existingJob=jobRepository.findById(jobId)
                                    .orElseThrow(()->new RuntimeException("Job not found"));
        
        if(!existingJob.getUser().getId().equals(userId)){
            throw new RuntimeException("Unauthorized access");
        }

        existingJob.setCompanyName(updatedJob.getCompanyName());
        existingJob.setJobTitle(updatedJob.getJobTitle());
        existingJob.setStatus(updatedJob.getStatus());
        existingJob.setNotes(updatedJob.getNotes());

        return jobRepository.save(existingJob);
    }


    // delete job
    public void deleteJob(Long jobId,Long userId){
        JobApplication job=jobRepository.findById(jobId)
                            .orElseThrow(()-> new RuntimeException("Job not found"));

        if(!job.getUser().getId().equals(userId)){
            throw new RuntimeException("Unauthorized access");
        }

        jobRepository.delete(job);
    }


}
