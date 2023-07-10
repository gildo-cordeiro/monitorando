package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.configuration.PDFGenerator;
import br.com.imd.pdse.monitorando.domain.Topic;
import br.com.imd.pdse.monitorando.domain.enums.ReportType;
import br.com.imd.pdse.monitorando.service.TopicService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReportController {

    private final TopicService topicService;
    private final PDFGenerator pdfGenerator;

    public ReportController(TopicService topicService, PDFGenerator pdfGenerator) {
        this.topicService = topicService;
        this.pdfGenerator = pdfGenerator;
    }

    @GetMapping("/pdf/topics-closed")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        var responseWithHeaders = setHeaders(response);
        var topicList = topicService.findTopicsByOpen();
        var headers = List.of("Tópicos Fechados", "Likes", "Ativo", "Data de Fechamento", "Data de Criação", "Contribuições");

        pdfGenerator.setFontSize(12);
        pdfGenerator.setTitle("Relatório de Tópicos Fechados");
        pdfGenerator.setNumberOfColumns(headers.size());
        pdfGenerator.setHeaders(headers);
        pdfGenerator.setFooter("Total de Tópicos: " + topicList.getTotalElements());

        pdfGenerator.setHeader("MONITORANDO");

        List<Topic> closedTopics = topicList.stream()
                .filter(Topic::isActive)
                .collect(Collectors.toList());

        double percentageClosed = (double) closedTopics.size() / topicList.getTotalElements() * 100;

        pdfGenerator.generate(responseWithHeaders, ReportType.QTT_CLOSED_TOPICS);

        pdfGenerator.setFooter("Total de Tópicos: " + topicList.getTotalElements() + " - Porcentagem de Tópicos Fechados: " + String.format("%.2f", percentageClosed) + "%");
    }

    private HttpServletResponse setHeaders(HttpServletResponse response) {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return response;
    }
}
