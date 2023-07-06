package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.configuration.PDFGenerator;
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

    public ReportController(TopicService topicService) {
        this.topicService = topicService;
    }


    @GetMapping("pdf/topics-closed")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        PDFGenerator generator = new PDFGenerator();

        var responseWithHeaders = setHeaders(response);
        var studentList = topicService.findTopicsByOpen();
        var headers = List.of("Topicos fechados","Likes", "Ativo", "Data de Fechamento", "Data de Criação");

        generator.setFontSize(12);
        generator.setQuantity(studentList.stream().count());
        generator.setTitle("Relatorio de Topicos Fechados");
        generator.setNumberOfColumns(headers.size());
        generator.setHeaders(headers);
        generator.setRows(studentList.stream().collect(Collectors.toList()));

        // Add the header
        generator.setHeader("MONITORANDO");

        generator.generate(responseWithHeaders, ReportType.QTT_CLOSED_TOPICS);
    }

    private HttpServletResponse setHeaders(HttpServletResponse response){
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return response;
    }
}
