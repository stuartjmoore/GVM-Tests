package temp.gvm.api.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import temp.gvm.api.Message;
import junit.framework.TestCase;

public class TestMessage extends TestCase
{
    private Message jsonMessage = null;
    private Message htmlMessage = null;
    
    private String id = "29270b5450f1594c15d932a7ab75deec8318f6e8";
    private String text = "My coworker gave me a card to try in your PC";
    private String date = "1355328929577";
    
    protected void setUp() throws Exception
    {
        super.setUp();
        jsonMessage = new Message(new JSONObject(loadFile("/Files/Message_JSON.txt")));
        Document respDoc = Jsoup.parse(loadFile("/Files/Message_HTML.txt"));
        List<Element> nodes = respDoc.getAllElements();
        htmlMessage = new Message(nodes.get(0), null);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testMessageText()
    {
        assertEquals(text, jsonMessage.text());
        assertEquals(text, htmlMessage.text());
    }
    
    public void testId()
    {
        assertEquals(id, jsonMessage.id());
    }
    
    public void testDate()
    {
        assertEquals(new Date(Long.parseLong(date)), jsonMessage.date());
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
