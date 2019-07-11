package com.nexters.cultureland;

import com.nexters.cultureland.model.Culture;
import com.nexters.cultureland.model.CultureRawData;
import com.nexters.cultureland.repo.CultureRepsitory;
import com.nexters.cultureland.repo.RawDataRepository;
import lombok.var;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CultureCrawler {

    private CultureRepsitory cultureRepsitory;
    private RawDataRepository rawDataRepository;

    final private String baseUrl = "http://ticket.11st.co.kr/";

    @Transactional
    public void getCultureRawdatas(String cultureName){
        Document doc = null;
        Culture culture = cultureRepsitory.findByCultureName(cultureName);
        String queryUrl = getQueryUrl(cultureName);
        List<CultureRawData> cultureRawDataList = new ArrayList<>();
        while(true) {
            try {
                var response = Jsoup.connect(baseUrl + queryUrl).timeout(2000).execute();
                if (response.statusCode() == HttpStatus.OK.value()) {
                    doc = response.parse();
                    Elements musicalElementList = doc.select("div.tk_section.play ul.tk_list li a");
                    Element nextUrl = doc.select("a.tk_next").first();

                    for (Element e : musicalElementList) {
                        String imgSrc = e.select("img").attr("src");
                        String title = e.select("span.tk_title").text();
                        String place = e.select("span.tk_place").text();
                        String date = e.select("span.tk_date").text();
                        cultureRawDataList.add(new CultureRawData(imgSrc, title, place, date, culture));
                    }
                    if (nextUrl == null) break;
                    queryUrl = nextUrl.attr("href");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        culture.setCultureRawDatas(cultureRawDataList);
        rawDataRepository.saveAll(cultureRawDataList);
        cultureRepsitory.save(culture);
    }
    private String getQueryUrl(String cultureName) {
        switch(cultureName.toUpperCase()) {
            case "MUSICAL":
                return "/Product/List?genreId=14123";
            case "CONCERT":
                return "/Product/List?genreId=14124";
            case "PLAY":
                return "/Product/List?genreId=14121";
            case "EXHIBITION":
                return "/Product/List?genreId=14126";
                default: return "";
        }
    }

    public CultureCrawler(CultureRepsitory cultureRepsitory, RawDataRepository rawDataRepository) {
        this.cultureRepsitory = cultureRepsitory;
        this.rawDataRepository = rawDataRepository;
    }
}
