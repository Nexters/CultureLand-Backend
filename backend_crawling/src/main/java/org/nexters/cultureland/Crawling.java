package org.nexters.cultureland;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.nexters.cultureland.model.Repo.CultureRepo;
import org.nexters.cultureland.model.vo.Culture;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;

@Component
@AllArgsConstructor
public class Crawling {


    private CultureRepo repo;

    final String url = "http://www.playdb.co.kr/playdb/playdblist.asp?Page=";

    @Transactional
    public void getCrawling() throws IOException{
        Document doc = null;
        int size = GetSize()+1;
        for (int i = 1; i<size; i++) {
            String url2 = "";
            String url3 = "&sReqMainCategory=000002";
            url2 = url + String.valueOf(i) + url3;
            try {
                doc = Jsoup.connect(url2).get();
            } catch (Exception e) {
                return;
            }
            // System.out.println(doc.data());

            Elements container = doc.select("table[width=480]");

            for (int k = 0; k < container.size(); k++) {
                Elements test = container.get(k).getElementsContainingText("세부장르");

                String key = container.get(k).text();

                String title = key.substring(0, key.indexOf("세부장르")).trim();
                if(title.contains("-")) {
                    title = key.split("-")[0].trim();
                }
                String genre = key.substring(key.indexOf("세부장르")+7,key.indexOf("일시")).trim();
                String start_date="";
                for(String s : key.substring(key.indexOf("일시")+5,key.indexOf("~")).trim().split("\\.") ) {
                    start_date += s;
                }
                String end_date = "";
                for(String s : key.substring(key.indexOf("~")+2,key.indexOf("장소")).trim().split("\\.") ) {
                    end_date += s;
                }
                String place="";
                String img = container.get(k).getElementsByTag("img").attr("src").trim();
                if(key.indexOf("출연")==-1) {
                    place = key.substring(key.indexOf("장소")+5,key.length()).trim();
                }
                else {
                    place= key.substring(key.indexOf("장소")+5,key.indexOf("출연")).trim();
                }

                if(end_date.equals("오픈런")) {
                    end_date="";
                }
/*                System.out.print("제목: "+ title);
                System.out.println("그림: "+ img);
                System.out.println("장르: " + genre);
                System.out.println("시작시간: "+start_date);
                System.out.println("종료시간: "+end_date);
                System.out.println("장소: "+place);*/
                repo.save(new Culture(title,img,genre,start_date,end_date,place));

            }
        }
    }


    private static int GetSize() throws IOException {
        String url = "http://www.playdb.co.kr/playdb/playdblist.asp?Page=1&sReqMainCategory=000002";

        String size = "0";
        Document doc = null;

        doc = Jsoup.connect(url).get();
        Elements container = doc.select("td[height=25][align=center]");

        for (int k = 0; k < container.size(); k++)
            size = container.get(k).getElementsContainingText("[total").text().split("/")[1].split("]")[0];

        return Integer.parseInt(size);
    }
}
