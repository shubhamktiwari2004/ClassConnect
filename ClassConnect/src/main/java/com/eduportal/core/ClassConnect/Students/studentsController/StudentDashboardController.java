package com.eduportal.core.ClassConnect.Students.studentsController;

import com.eduportal.core.ClassConnect.Students.studentService.StudentDashboardService;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentDashboardModel;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
public class StudentDashboardController {

    private final StudentDashboardService studentDashboardService;

    public StudentDashboardController(StudentDashboardService studentDashboardService) {
        this.studentDashboardService = studentDashboardService;
    }

    //Function to get dashboard marks of specific students
    @GetMapping("/students/marks")
    @ResponseBody
    public ResponseEntity<?> studentMarksById(HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Student ID not found in session"));
        }
        List<StudentDashboardModel> studentsMarks = studentDashboardService.getStudentMarksById(studentId);
        return ResponseEntity.ok(studentsMarks);
    }
    // Function to return the performance page with student marks
    @GetMapping("/students/performance")
    public String studentPerformancePage(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            model.addAttribute("error", "Student ID not found in session");
            return "students/performance"; // Return the page with an error message
        }

        // Fetch the student's marks
        List<StudentDashboardModel> studentsMarks = studentDashboardService.getStudentMarksById(studentId);

        // Add the marks to the model to pass to the view
        model.addAttribute("studentsMarks", studentsMarks);

        return "students/performance"; // Return the view name
    }

    @GetMapping("/students/report/download")
    public void downloadStudentReport(HttpServletResponse response, HttpSession session) throws IOException {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Student ID not found in session");
            return;
        }

        // Fetch the student's marks
        List<StudentDashboardModel> studentsMarks = studentDashboardService.getStudentMarksById(studentId);

        // Set response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Student_Report.pdf");

        // Generate PDF
        try (OutputStream out = response.getOutputStream()) {
            com.itextpdf.kernel.pdf.PdfWriter writer = new com.itextpdf.kernel.pdf.PdfWriter(out);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            // Title
            document.add(new com.itextpdf.layout.element.Paragraph("Student Annual Report")
                    .setFontSize(18)
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER)
                    .setBold());

            // Student Info
            document.add(new com.itextpdf.layout.element.Paragraph("Student ID: " + studentId)
                    .setFontSize(12)
                    .setMarginBottom(10));

            // Table for marks
            com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(7);
            table.addHeaderCell("S.No.");
            table.addHeaderCell("Subject");
            table.addHeaderCell("Unit 1");
            table.addHeaderCell("Sem 1");
            table.addHeaderCell("Unit 2");
            table.addHeaderCell("Sem 2");
            table.addHeaderCell("Total");

            // Populate table rows with marks
            int index = 1;
            for (StudentDashboardModel mark : studentsMarks) {
                table.addCell(String.valueOf(index++));
                table.addCell(mark.getSubject());
                table.addCell(String.valueOf(mark.getUnit1()));
                table.addCell(String.valueOf(mark.getSem1()));
                table.addCell(String.valueOf(mark.getUnit2()));
                table.addCell(String.valueOf(mark.getSem2()));
                table.addCell(String.valueOf(mark.getTotal()));
            }


            // Add table to document
            document.add(table);

            // Close the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating PDF");
        }
    }


}
