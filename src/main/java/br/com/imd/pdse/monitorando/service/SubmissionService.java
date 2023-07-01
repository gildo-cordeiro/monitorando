package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.SubmissionRepository;
import br.com.imd.pdse.monitorando.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final UserRepository userRepository;

    public SubmissionService(SubmissionRepository submissionRepository, UserRepository userRepository) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
    }

    public Submission save(Submission submission) {
        return submissionRepository.save(submission);
    }

    public boolean submissionAccess(Submission submission, HttpServletRequest request){
        var user = (User) request.getSession().getAttribute("foundUser");

        if (!submission.isActive())
            return true;

        if (submission.getUser().getUserType().equals(UserType.STUDENT) && submission.getUser().equals(user))
            return true;

        return !submission.getUser().equals(user) && (user.getUserType().equals(UserType.TEACHER) || user.getUserType().equals(UserType.MONITOR));

    }
}
