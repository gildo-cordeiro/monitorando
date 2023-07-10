package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.configuration.PDFGenerator;
import br.com.imd.pdse.monitorando.domain.Topic;
import br.com.imd.pdse.monitorando.domain.enums.ReportType;
import br.com.imd.pdse.monitorando.service.ExerciseService;
import br.com.imd.pdse.monitorando.service.TopicService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReportController {

    public static final String MONITORANDO = "MONITORANDO";
    private final TopicService topicService;
    private final ExerciseService exerciseService;

    private final PDFGenerator pdfGenerator;

    public ReportController(TopicService topicService, ExerciseService exerciseService, PDFGenerator pdfGenerator) {
        this.topicService = topicService;
        this.exerciseService = exerciseService;
        this.pdfGenerator = pdfGenerator;
    }


    @GetMapping("pdf/topics-closed")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        var topicList = topicService.findTopicsByOpen();
        var headers = List.of("Etudante","Submissão", "Exercício", "Submissão Privada", "Data de Criação");
        var title = "Relatorio de Topicos Fechados";

        reportsValues(headers, title);
        pdfGenerator.setQuantity(topicList.stream().count());
        pdfGenerator.setRows(topicList.stream().collect(Collectors.toList()));

        // Add the header
        pdfGenerator.setHeader(MONITORANDO);

        List<Topic> closedTopics = topicList.stream()
                .filter(Topic::isActive)
                .toList();

        double percentageClosed = (double) closedTopics.size() / topicList.getTotalElements() * 100;
        pdfGenerator.setFooter("Total de Tópicos: " + topicList.getTotalElements() + " - Porcentagem de Tópicos Fechados: " + String.format("%.2f", percentageClosed) + "%");

        pdfGenerator.generate(getHeaders(response, title), ReportType.QTT_CLOSED_TOPICS);
    }

    @GetMapping("pdf/students-helped")
    public void generatePdfStudentsHelped(HttpServletResponse response) throws DocumentException, IOException {
        var studentsHelped = topicService.findStudentsHelped();
        var headers = List.of("Etudante","Submissão", "Exercício", "Submissão Privada", "Data de Criação");
        var title = "Relatorio de Estudantes Assistidos";

        reportsValues(headers, title);
        pdfGenerator.setQuantity(studentsHelped.stream().count());
        pdfGenerator.setRows(studentsHelped.stream().collect(Collectors.toList()));

        // Add the header
        pdfGenerator.setHeader(MONITORANDO);

        pdfGenerator.generate(getHeaders(response, title), ReportType.QTT_ASSISTED_STUDENTS);
    }

    @GetMapping("pdf/exercises-closed")
    public void generatePdfExercisesClosed(HttpServletResponse response) throws DocumentException, IOException {
        var exercises = exerciseService.findExerciseByActive();
        var headers = List.of("Exercício", "Descrição", "Ativo", "Data de Criação");
        var title = "Relatorio de Exercicios";

        reportsValues(headers, title);
        pdfGenerator.setQuantity(exercises.stream().count());
        pdfGenerator.setRows(exercises.stream().collect(Collectors.toList()));

        // Add the header
        pdfGenerator.setHeader(MONITORANDO);

        pdfGenerator.generate(getHeaders(response, title), ReportType.REPORT_EXERCISE);
    }

    private void reportsValues(List<String> headers, String title) {
        pdfGenerator.setFontSize(12);
        pdfGenerator.setTitle(title);
        pdfGenerator.setNumberOfColumns(headers.size());
        pdfGenerator.setHeaders(headers);
    }


    private HttpServletResponse getHeaders(HttpServletResponse response, String title){
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + title.replaceAll(" ", "_").toLowerCase() + ".pdf";
        response.setHeader(headerKey, headerValue);

        return response;
    }
}
