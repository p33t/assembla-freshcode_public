package pkg;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PoiApp {
    public static void main(String[] args) throws Exception {
        PoiApp p = new PoiApp();
        p.outDoc("minimal.doc");
        p.outDocX("minimal.docx");
        p.outDoc("tables.doc");
        p.outDocX("tables.docx");
    }

    private void outDoc(String fileName) throws Exception {
        File file = file(fileName);
        List<String> list = doc(file);
        outParagraphs(fileName, list);
    }

    private void outDocX(String fileName) throws Exception {
        File myFile = file(fileName);
        List<String> list = docX(myFile);
        outParagraphs(fileName, list);
    }

    private List<String> doc(File file) throws IOException {
        WordExtractor extractor = new WordExtractor(stream(file));
        String[] lines = extractor.getParagraphText();
        List<String> list = new ArrayList<String>();
        for (String line : lines) list.add(cleanString(line));
        return list;
    }

    private void outParagraphs(String fileName, List<String> lines) {
        System.out.println("<" + fileName + "> paragraphs...");
        for (String line : lines) {
            out(line);
        }
        out("---------------End of paragraphs");
    }

    private String cleanString(String line) {
        if (line == null) return "";
        if (line.isEmpty()) return "";
        if (line.charAt(0) == '\f') return cleanString(line.substring(1));
        int len = line.length();
        if (line.charAt(len - 1) == '\u0007') return cleanString(line.substring(0, len - 1));
        String trimmed = line.trim();
        if (trimmed.length() != len) return cleanString(trimmed);
        return line;
    }

    private List<String> docX(File myFile) throws Exception {
        ZipFile zip = new ZipFile(myFile);
        ZipEntry documentXML = zip.getEntry("word/document.xml");
        InputStream stream = zip.getInputStream(documentXML);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = dbf.newDocumentBuilder().parse(stream);

        Element elem = doc.getDocumentElement();
        NodeList n = elem.getElementsByTagName("w:p");

        List<String> list = new ArrayList<String>();
        for (int j = 0; j < n.getLength(); j++) {
            Node child = n.item(j);
            String line = child.getTextContent();
            list.add(cleanString(line));
        }
        return list;
    }

    private File file(String fileName) {
        return new File("src/main/resources/" + fileName);
    }

    private FileInputStream stream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    private void out(Object s) {
        System.out.println(s);
    }


}
