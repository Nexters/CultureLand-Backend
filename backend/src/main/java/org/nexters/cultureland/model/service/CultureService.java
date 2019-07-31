package org.nexters.cultureland.model.service;

import org.nexters.cultureland.model.dao.CultureRawRepo;
import org.nexters.cultureland.model.vo.Culture;
import org.nexters.cultureland.model.vo.CultureRawData;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CultureService {
    //최근순으로 문화생활 전체 목록 조회(id, img)
    public List getList();

    //카테고리에 맞는 문화생활 조회
    public List<Culture> getByCategory(String cultureName);

    //상세정보 조회
    public Optional<CultureRawData> getByCultureId(Long id);

    //검색어에 맞는 문화생활 조회
    public List<Object> getBySearch(String query);

    //test1
    public List<CultureRawData> getAll();


}
