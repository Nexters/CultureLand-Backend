package com.nexters.cultureland;

import com.nexters.cultureland.model.Culture;
import com.nexters.cultureland.repo.CultureRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CulturelandApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(CulturelandApplication.class, args);
	}

	@Autowired
	private CultureCrawler crawlerService;
	@Autowired
	private CultureRepsitory cultureRepsitory;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Culture musical = createCulture("musical");
		Culture concert = createCulture("concert");
		Culture play = createCulture("play");
		Culture exhibition = createCulture("exhibition");
		cultureRepsitory.save(musical);
		cultureRepsitory.save(concert);
		cultureRepsitory.save(play);
		cultureRepsitory.save(exhibition);
		long start = System.currentTimeMillis(); //시작하는 시점 계산

		crawlerService.getCultureRawdatas("musical");
		crawlerService.getCultureRawdatas("concert");
		crawlerService.getCultureRawdatas("play");
		crawlerService.getCultureRawdatas("exhibition");

		long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
		System.out.println("실행 시간 : "+ ( end - start )/1000.0 +"");
	}

	public Culture createCulture(String cultureName){
		return Culture.builder().cultureName(cultureName).build();
	}
}
