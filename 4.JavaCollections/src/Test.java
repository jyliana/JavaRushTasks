import java.io.File;
import java.io.IOException;

/**
 * Created by Inna on 11/20/17.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        File folder = new File("d:/1/");
        for (File file : folder.listFiles()) {

            System.out.println(file.getParent());
        }

    }
}
