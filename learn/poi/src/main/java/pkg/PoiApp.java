package pkg;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PoiApp {
    public static void main(String[] args) throws Exception {
        PoiApp p = new PoiApp();
        p.doc("minimal.doc");
        p.docX("minimal.docx");
        p.doc("tables.doc");
        p.docX("tables.docx");
    }

    private void doc(String fileName) throws Exception {
        // need an open InputStream; for a file-based system, this would be appropriate:
        // InputStream stream = new FileInputStream(fileName);
        DirectoryNode root = getRoot(fileName);
        String s = root.getName() + " with " + root.getEntryCount() + " entries...";
        out(s);
        Iterator<Entry> entries = root.getEntries();
        while (entries.hasNext()) {
            Entry next = entries.next();
            out(next.getName());
        }

        out("");
        out("Obtaining document: WordDocument");
        DocumentInputStream doc = root.createDocumentInputStream("WordDocument");
        byte[] bytes = new byte[doc.available()];
        out(bytes.length + " bytes allocated.");
        int readCout = doc.read(bytes);
        out("Read " + readCout + " bytes.");

        out("");
        WordExtractor extractor = new WordExtractor(getStream(fileName));
        String[] lines = extractor.getParagraphText();
//        TODO: Make doc vs docx transparent.
        System.out.println("<" + fileName + ">.getText()...");
        for (String line : lines) {
            out(cleanString(line));
        }
        out("---------------End of getText()");
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

    private void docX(String fileName) throws Exception {
        File myFile = file(fileName);
        ZipFile docxFile = new ZipFile(myFile);
        ZipEntry documentXML = docxFile.getEntry("word/document.xml");
        InputStream documentXMLIS = docxFile.getInputStream(documentXML);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = dbf.newDocumentBuilder().parse(documentXMLIS);

        Element tElement = doc.getDocumentElement();
        NodeList n = tElement.getElementsByTagName("w:p");

        out("<" + fileName + ">.lines...");
        for (int j = 0; j < n.getLength(); j++) {
            Node child = n.item(j);
            String line = child.getTextContent();

            out(cleanString(line));
//            if (isNotEmpty(line)) {
//                out(line);
//            }
        }
        out("---------------- end of lines.");
    }

    private File file(String fileName) {
        return new File("src/main/resources/" + fileName);
    }

    private DirectoryNode getRoot(String fileName) throws Exception {
        InputStream inputStream = getStream(fileName);
        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        return fs.getRoot();
    }

    private InputStream getStream(String fileName) throws Exception {
        return new FileInputStream(file(fileName));
    }

    private void out(Object s) {
        System.out.println(s);
    }


}
