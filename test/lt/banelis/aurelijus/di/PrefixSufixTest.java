package lt.banelis.aurelijus.di;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aurelijus Banelis
 */
public class PrefixSufixTest {
    
    public PrefixSufixTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void print() {
        PrefixSufix ps = new PrefixSufix();
        ps.read(getResource("abc"));
        
        System.out.println("\n========= PREFIX ==========");
        System.out.println(ps.getPrefixRoute());
        
        System.out.println("\n========= SUFIX ===========");
        System.out.println(ps.getSufixRoute());
        
        
        PrefixSufix ps2 = new PrefixSufix();
        ps2.read(getResource("daugyba"));
        
        System.out.println("\n========= PREFIX ==========");
        System.out.println(ps2.getPrefixRoute());
        
        System.out.println("\n========= SUFIX ===========");
        System.out.println(ps2.getSufixRoute());
    }
    
    @Test
    public void testAbc() {
        PrefixSufix ps = new PrefixSufix();
        ps.read(getResource("abc"));
        assertEquals("abcdefghijkl", ps.getPrefixRoute());
        assertEquals("cedbfijklhga", ps.getSufixRoute());
    }
    
    private static String getResource(String name) {
        String result = "";
        try {
            result = URLDecoder.decode(PrefixSufixTest.class.getResource(name).getPath(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Canot URL decode resource: " + name);
        }
        return result;
    }
}
