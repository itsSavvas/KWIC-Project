/**
 * Created by timday on 7/9/17.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JSouper {


    public String getFromWiki(String url) {


        try {
//            Document doc = Jsoup.connect(url).get();
//            Element contentDiv = doc.select("div[id=content]").first();
//            return contentDiv.toString(); // The result


            // need http protocol
            Document doc = Jsoup.connect(url).get();

            // get page title
            String title = doc.title();
            System.out.println("title : " + title);

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {

                // get the value from href attribute
                System.out.println("\nlink : " + link.attr("href"));
                System.out.println("text : " + link.text());

            }
            return links.toString();

        } catch (Exception e) {
            return "error";
        }
    }

    public static void main(String[] args) {

        JSouper test = new JSouper();
        String result = test.getFromWiki("https://en.wikipedia.org/wiki/The_Beatles");
//        System.out.println(result);

    }
}
