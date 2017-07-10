import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper {

    private String corpus;

    public Scraper(String url) {

        Document document = null;
        try {
            document = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //  corpus += document.select("div").first().text();

        Elements links = document.select("p");
        for (Element link : links) {
            corpus += link.text() + " ";
        }
    }

    public String getCorpus() {return corpus;}

    public static void main(String[] args) throws Exception {
        //   Scrape sc = new Scrape("https://en.wikipedia.org/wiki/The_Beatles");
        //   System.out.println(sc.getCorpus());
    }

}

