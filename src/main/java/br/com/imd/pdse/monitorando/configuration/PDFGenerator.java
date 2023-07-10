package br.com.imd.pdse.monitorando.configuration;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.domain.Topic;
import br.com.imd.pdse.monitorando.domain.enums.ReportType;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class PDFGenerator {

    // List to hold all Students
    private List<Object> rows;
    private List<String> headers;
    private String title;
    private int fontSize;
    private int numberOfColumns;
    private long quantity;
    private String header;
    private String footer;

    public void generate(HttpServletResponse response, ReportType reportType) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontHeader = FontFactory.getFont(FontFactory.COURIER_BOLD);
        fontHeader.setSize(18);
        fontHeader.setColor(CMYKColor.BLACK);

        Paragraph headerParagraph = new Paragraph(header, fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(headerParagraph);

        Font fontTitle = FontFactory.getFont(FontFactory.COURIER);
        fontTitle.setSize(fontSize);

        AtomicReference<Paragraph> paragraph = new AtomicReference<>(new Paragraph(title, fontTitle));
        paragraph.get().setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph.get());

        PdfPTable table = buildTable(numberOfColumns);
        AtomicReference<PdfPTable> table1 = new AtomicReference<>();

        PdfPCell cell = buildCell(CMYKColor.WHITE, Element.ALIGN_CENTER, Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(CMYKColor.BLACK);

        setHeaders(cell, table, font, headers);

        rows.forEach(object -> {
            if (Objects.requireNonNull(reportType) == ReportType.QTT_CLOSED_TOPICS) {
                var topic = (Topic) object;
                setCellValue(cell, table, font, String.valueOf(quantity));
                setCellValue(cell, table, font, String.valueOf(topic.getLikes()));
                setCellValue(cell, table, font, topic.isActive() ? "SIM" : "NÃO");
                setCellValue(cell, table, font, getDateFormatted(topic.getClosedDate()));
                setCellValue(cell, table, font, getDateFormatted(LocalDate.ofInstant(topic.getCreatedDate(), ZoneId.of("UTC"))));

            }else if (Objects.requireNonNull(reportType) == ReportType.QTT_ASSISTED_STUDENTS){
                var submission = (Submission) object;
                setCellValue(cell, table, font, submission.getUser().getName());
                setCellValue(cell, table, font, submission.getAnswer());
                setCellValue(cell, table, font, submission.getExercise().getTitle());
                setCellValue(cell, table, font, submission.isPrivacy() ? "SIM" : "NÃO");
                setCellValue(cell, table, font, getDateFormatted(LocalDate.ofInstant(submission.getCreatedDate(), ZoneId.of("UTC"))));
            }else if (Objects.requireNonNull(reportType) == ReportType.REPORT_EXERCISE){
                var exercise = (Exercise) object;
                setCellValue(cell, table, font, exercise.getTitle());
                setCellValue(cell, table, font, exercise.getDescription());
                setCellValue(cell, table, font, exercise.isActive() ? "SIM" : "NÃO");
                setCellValue(cell, table, font, getDateFormatted(LocalDate.ofInstant(exercise.getCreatedDate(), ZoneId.of("UTC"))));

                paragraph.set(new Paragraph("Alunos que participaram do exericio", fontTitle));
                paragraph.get().setAlignment(Paragraph.ALIGN_CENTER);

                var studentsHeaders = List.of("Nome", "Turma", "Monitor", "Professor");
                table1.set(buildTable(studentsHeaders.size()));
                setHeaders(cell, table1.get(), font, studentsHeaders);

                exercise.getClassroom().getClassrooms().forEach(studentClassroom -> {
                    setCellValue(cell, table1.get(), font, studentClassroom.getStudent().getUser().getName());
                    setCellValue(cell, table1.get(), font, studentClassroom.getClassroom().getClassName());
                    setCellValue(cell, table1.get(), font, studentClassroom.getClassroom().getMonitor().getUser().getName());
                    setCellValue(cell, table1.get(), font, studentClassroom.getClassroom().getTeacher().getUser().getName());
                });
            }
        });

        document.add(table);

        Font fontFooter = FontFactory.getFont(FontFactory.COURIER);
        fontFooter.setSize(10);
        fontFooter.setColor(CMYKColor.GRAY);

        Paragraph footerParagraph = new Paragraph(footer, fontFooter);
        footerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(footerParagraph);
        document.add(paragraph.get());

        if (table1.get() != null)
            document.add(table1.get());

        document.close();
    }

    public PdfPTable buildTable(int numberOfColumns){
        PdfPTable table = new PdfPTable(numberOfColumns);
        table.setWidthPercentage(100f);
        table.setWidths(getWidths(numberOfColumns));
        table.setSpacingBefore(5);
        table.flushContent();
        return table;
    }

    public PdfPCell buildCell(Color color, int alignHorizontal, int alignVertical){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(color);
        cell.setHorizontalAlignment(alignHorizontal);
        cell.setVerticalAlignment(alignVertical);
        return cell;
    }

    public void setHeaders(PdfPCell cell, PdfPTable table, Font font, List<String> headers){
        headers.forEach(s -> {
            cell.setPhrase(new Phrase(s, font));
            table.addCell(cell);
        });
    }
    private void setCellValue(PdfPCell cell, PdfPTable table, Font font, String text){
        cell.setPhrase(new Phrase(text, font));
        table.addCell(cell);
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    private int[] getWidths(int numberOfColumns) {
        int[] widths = new int[numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            widths[i] = 4;
        }
        return widths;
    }

    private String getDateFormatted(LocalDate localDate){
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return localDate.format(formatters);
    }
}
