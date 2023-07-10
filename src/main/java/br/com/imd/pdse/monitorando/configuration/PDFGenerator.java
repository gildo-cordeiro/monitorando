package br.com.imd.pdse.monitorando.configuration;

import br.com.imd.pdse.monitorando.domain.Student;
import br.com.imd.pdse.monitorando.domain.Topic;
import br.com.imd.pdse.monitorando.domain.enums.ReportType;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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

        // Add the header
        Font fontHeader = FontFactory.getFont(FontFactory.COURIER_BOLD);
        fontHeader.setSize(18);
        fontHeader.setColor(CMYKColor.BLACK);

        Paragraph headerParagraph = new Paragraph(header, fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(headerParagraph);

        Font fontTitle = FontFactory.getFont(FontFactory.COURIER);
        fontTitle.setSize(fontSize);

        // Creating paragraph
        Paragraph paragraph = new Paragraph(title, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(numberOfColumns);
        table.setWidthPercentage(100f);
        table.setWidths(getWidths());
        table.setSpacingBefore(5);
        table.flushContent();
        // Create Table Cells for table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding
        cell.setBackgroundColor(CMYKColor.WHITE);
        cell.setBorderWidthBottom(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(CMYKColor.BLACK);

        // Adding headings in the created table cell/ header
        // Adding Cell to table
        headers.forEach(s -> {
            cell.setPhrase(new Phrase(s, font));
            table.addCell(cell);
        });

        rows.forEach(object -> {
            if (Objects.requireNonNull(reportType) == ReportType.QTT_CLOSED_TOPICS) {
                var topic = (Topic) object;
                table.addCell(String.valueOf(quantity));
                table.addCell(String.valueOf(topic.getLikes()));
                table.addCell(topic.isActive() ? "SIM" : "NÃO");
                table.addCell(getDateFormatted(topic.getClosedDate()));
                table.addCell(getDateFormatted(LocalDate.ofInstant(topic.getCreatedDate(), ZoneId.of("UTC"))));

            }else if (Objects.requireNonNull(reportType) == ReportType.QTT_ASSISTED_STUDENTS){
                var student = (Student) object;
                student.getStudents().forEach(classroom -> {
                    table.addCell(String.valueOf(student.getUuid()));
                    table.addCell(student.getUser().getName());
                    table.addCell(classroom.getClassroom().getClassName());
                    table.addCell(classroom.getClassroom().getMonitor().getUser().getName());
                });
                // Restante do código omitido por questões de espaço
            }else {
                table.addCell("");
            }
        });

        document.add(table);

        // Add the footer
        Font fontFooter = FontFactory.getFont(FontFactory.COURIER);
        fontFooter.setSize(10);
        fontFooter.setColor(CMYKColor.GRAY);

        Paragraph footerParagraph = new Paragraph(footer, fontFooter);
        footerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(footerParagraph);

        document.close();
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

    private int[] getWidths() {
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
