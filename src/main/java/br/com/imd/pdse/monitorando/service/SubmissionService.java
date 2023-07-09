package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Comment;
import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.CommentRepository;
import br.com.imd.pdse.monitorando.repository.SubmissionRepository;
import br.com.imd.pdse.monitorando.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final CommentRepository commentRepository;

    public SubmissionService(SubmissionRepository submissionRepository, CommentRepository commentRepository) {
        this.submissionRepository = submissionRepository;
        this.commentRepository = commentRepository;
    }

    public Submission save(Submission submission) {
        return submissionRepository.save(submission);
    }

//    public boolean submissionAccess(List<Submission> submission, HttpServletRequest request) {
//        var user = (User) request.getSession().getAttribute("foundUser");
//
//        submission.stream().map(sub -> {
//            if (!sub.isActive())
//                return true;
//
//            if (sub.getUser().getUserType() == UserType.STUDENT) {
//                if (sub.getUser().getUuid().equals(user.getUuid()))
//                    return true;
//
//                return sub.isPrivacy();
//            }
//
//            return sub.getUser().getUuid().equals(user.getUuid()) && (user.getUserType() == UserType.TEACHER || user.getUserType() == UserType.MONITOR);
//        });
//    }

    public Optional<Submission> findById(UUID id) {
        return submissionRepository.findById(id);
    }

//    public Submission findSubmissionByComment(List<Comment> comment){
//        return submissionRepository.findSubmissionByComment(comment);
//    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Submission> findByExerciseId(UUID uuid) {
        return submissionRepository.findByExerciseId(uuid);
    }
}
