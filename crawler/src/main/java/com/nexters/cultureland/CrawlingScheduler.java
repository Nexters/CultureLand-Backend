package com.nexters.cultureland;

import com.nexters.cultureland.repo.RawDataRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CrawlingScheduler {
    private CultureCrawler cultureCrawler;
    private RawDataRepository rawDataRepository;

    /*
        각 자리는 초, 분, 시, 일, 월, 요일 이다.
        매분 마다 table drop한 후 crawling 시작.
        나중에는 시간단위로 변경 -> * 0 * * * *
    */
    @PostConstruct
    public void runner(){
        this.startScheduler();
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void startScheduler(){
        long start = System.currentTimeMillis(); //시작하는 시점 계산
        //rawDataRepository.deleteAll();
        cultureCrawler.getCultureRawdatas("musical");
        cultureCrawler.getCultureRawdatas("concert");
        cultureCrawler.getCultureRawdatas("play");
        cultureCrawler.getCultureRawdatas("exhibition");

        long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
        System.out.println("실행 시간 : "+ ( end - start )/1000.0 +"");
    }

    public CrawlingScheduler(CultureCrawler cultureCrawler, RawDataRepository rawDataRepository) {
        this.cultureCrawler = cultureCrawler;
        this.rawDataRepository = rawDataRepository;
    }
}
