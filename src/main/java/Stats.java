import java.io.*;
import java.util.Date;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Stats {
    public static void statAdd(int choice, String text) throws IOException {
        FileWriter file = new FileWriter("tmp/stats.txt", true);
        BufferedWriter out = new BufferedWriter(file);
        Date date = new Date();
        String dateStr = date.toString();
        String txt;
        if (choice == 1){
            txt = dateStr + "- Dodano nowy jezyk: " + text + "\n";
            out.write(txt);
        }
        if (choice == 2){
            txt = dateStr + "- Dodano nowe slowa do jezyka: " + text + "\n";
            out.write(txt);
        }
        if (choice == 3){
            txt = dateStr + "- Usunieto jezyk: " + text + "\n";
            out.write(txt);
        }
        if (choice == 4){
            txt = dateStr + "- Szukano slowa: " + text + "\n";
            out.write(txt);
        }
        out.close();
        file.close();
        pdfCreate();
    }

    public static void pdfCreate() throws IOException {
        File file = new File("tmp/stats.txt");
        Scanner input = new Scanner(file);
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        PDPageContentStream contents = new PDPageContentStream(doc, page);
        contents.beginText();
        contents.setFont(font, 14);
        contents.setLeading(14.5f);
        contents.newLineAtOffset(50, 700);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            contents.showText(line.toString());
            contents.newLine();
        }
        input.close();
        contents.endText();
        contents.close();
        doc.save("stats.pdf");
        doc.close();
    }
}
