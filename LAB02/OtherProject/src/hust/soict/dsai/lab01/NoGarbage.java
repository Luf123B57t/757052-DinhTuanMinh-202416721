package hust.soict.dsai.lab01;
import java.nio.file.Files;
import java.nio.file.Paths;
public class NoGarbage {
    public static void main(String[] args) throws Exception {
        String filename = "D:\\Java\\LAB_ALL\\757052-DinhTuanMinh-202416721\\LAB02\\OtherProject\\large.txt";
        byte[] inputBytes = { };
        long startTime, endTime;

        inputBytes = Files.readAllBytes(Paths.get(filename));
        startTime = System.currentTimeMillis();
        StringBuilder outputStringBuilder = new StringBuilder();
        for (byte b : inputBytes) {
            outputStringBuilder.append((char) b);
        }
        /*
        StringBuffer outputStringBuffer = new StringBuffer();
        for (byte b : inputBytes) {
            outputStringBuffer.append((char) b);
        } //slower than StringBuilder

         */
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

}