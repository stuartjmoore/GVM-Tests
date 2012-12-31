package temp.gvm.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import temp.gvm.Utilities;
import temp.gvm.api.Voice;
import junit.framework.TestCase;

public class TestUtilities extends TestCase
{    
    //These are just to avoid having to type out the long names all the time
    private String htmlStart = null;
    private String htmlEnd = null;
    private String jsonStart = null;
    private String jsonEnd = null;
    
    protected void setUp() throws Exception
    {
        super.setUp();
        htmlStart = Voice.HTMLResponse_Start;
        htmlEnd = Voice.HTMLResponse_End;
        jsonStart = Voice.JSONResponse_Start;
        jsonEnd = Voice.JSONResponse_End;
        
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * Test the Utilities.getSubstring() method on JSON portion of the response
     */
    public void testGetSubstring_JSON()
    {
        String fileData = loadFile("/Files/SampleResponse1.xml");
        String expected = loadFile("/Files/SampleResponse1_JSON.txt");
        
        assertEquals(expected,
                Utilities.getSubstring(fileData, jsonStart, jsonEnd, true, true, false));
    }
    
    /**
     * Test the Utilities.getSubstring() method on HTML portion of the response
     */
    public void testGetSubstring_HTML()
    {
        String fileData = loadFile("/Files/SampleResponse1.xml");
        String expected = loadFile("/Files/SampleResponse1_HTML.txt");
        
        assertEquals(expected,
                Utilities.getSubstring(fileData, htmlStart, htmlEnd, true, true, false));
    }
    
    /**
     * Loads a file into a string
     * 
     * @param file File to load
     * @return The string that is the file... duh
     */
    private String loadFile(String file)
    {
        try {
            StringBuffer fileData = new StringBuffer();
            BufferedReader brIn = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
            char[] buf = new char[1024];
            int numRead = 0;
            while((numRead=brIn.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0 , numRead);
                fileData.append(readData);
            }
            brIn.close();
            return fileData.toString();
        }
        catch(IOException ex) {
            return "";
        }       
    }

}
