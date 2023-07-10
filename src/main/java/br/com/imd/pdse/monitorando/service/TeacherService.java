package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Calendar;
import br.com.imd.pdse.monitorando.domain.Teacher;
import br.com.imd.pdse.monitorando.repository.CalendarRepository;
import br.com.imd.pdse.monitorando.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CalendarRepository calendarRepository;

    public TeacherService(TeacherRepository teacherRepository, CalendarRepository calendarRepository) {
        this.teacherRepository = teacherRepository;
        this.calendarRepository = calendarRepository;
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher findById(UUID id) {
        return teacherRepository.findById(id).get();
    }

    public Teacher findTeacherByUserId(UUID id) {
        return teacherRepository.findTeacherByUserId(id);
    }

    public Calendar saveCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

}
