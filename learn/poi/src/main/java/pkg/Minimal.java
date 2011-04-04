package pkg;

import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Minimal {
    public static void main(String[] args) throws Exception {
        new Minimal().run();
    }

    private void run() throws Exception {
        // need an open InputStream; for a file-based system, this would be appropriate:
        // InputStream stream = new FileInputStream(fileName);
        DirectoryNode root = getRoot("minimal.doc");
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
    }

    private DirectoryNode getRoot(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        return fs.getRoot();
    }

    private void out(Object s) {
        System.out.println(s);
    }


}
