import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Test {
    public static void main(String[] args) throws IOException {

        FileOutputStream zipFile = new FileOutputStream("d:/archive.zip");
        ZipOutputStream zip = new ZipOutputStream(zipFile);

        zip.putNextEntry(new ZipEntry("document.txt"));
        File file = new File("d:/document-for-archive.txt");
        Files.copy(file.toPath(), zip);
        zip.closeEntry();

        zip.putNextEntry(new ZipEntry("document2.txt"));
        File file2 = new File("d:/document-for-archive2.txt");
        Files.copy(file2.toPath(), zip);

        zip.close();

    }
}
