package com.cyj.utils.contentChange;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * @program: volunteer
 * @author: xlk
 * @create: 2017-12-28 15:29
 */

public class Html2Text extends HTMLEditorKit.ParserCallback {

    public static void main(String[] args) {
        System.out.println(getContent("<h1>hshsh</h1>"));
    }

    private static Html2Text html2Text = new Html2Text();
    StringBuffer s;
    public Html2Text() {
    }

    //获取富文本内容
    public static String getContent(String str) {
        try {
            html2Text.parse(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html2Text.getText();
    }


    public void parse(String str) throws IOException {
        InputStream iin = new ByteArrayInputStream(str.getBytes());
        Reader in = new InputStreamReader(iin);
        s = new StringBuffer();
        ParserDelegator delegator = new ParserDelegator();
        delegator.parse(in, this, Boolean.TRUE);
        iin.close();
        in.close();
    }

    public void handleText(char[] text, int pos) {
        s.append(text);
    }

    public String getText() {
        return s.toString();
    }

}
