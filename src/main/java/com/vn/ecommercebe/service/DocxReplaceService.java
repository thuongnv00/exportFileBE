package com.vn.ecommercebe.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Timer;

@Service
public class DocxReplaceService {
    public byte[] replacePlaceholders(Map<String, String> replacements) throws IOException {
        // Đọc file template từ resources
        try (InputStream is = getClass().getResourceAsStream("/template.docx");
             XWPFDocument doc = new XWPFDocument(is);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Duyệt từng paragraph
            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                replaceInParagraph(paragraph, replacements);
            }

            // Duyệt từng bảng (nếu trong bảng cũng có placeholder)
            doc.getTables().forEach(table ->
                    table.getRows().forEach(row ->
                            row.getTableCells().forEach(cell ->
                                    cell.getParagraphs().forEach(p ->
                                            replaceInParagraph(p, replacements)
                                    )
                            )
                    )
            );


            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
            String localTime = LocalDateTime.now().format(dateTimeFormatter);
            String path = "E:\\ExportFile\\report-" + localTime + ".docx";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            }

            try {
                FileOutputStream fos = new FileOutputStream(file);
                doc.write(fos);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void replaceInParagraph(XWPFParagraph paragraph, Map<String, String> replacements) {
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null) {
                for (Map.Entry<String, String> entry : replacements.entrySet()) {
                    text = text.replace("{{" + entry.getKey() + "}}", entry.getValue());
                }
                run.setText(text, 0); // Ghi lại text mới vào run
            }
        }
    }
}
