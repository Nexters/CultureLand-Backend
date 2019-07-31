package org.nexters.cultureland.model.service;

import org.nexters.cultureland.model.dao.CultureRawRepo;
import org.nexters.cultureland.model.dao.CultureRepo;
import org.nexters.cultureland.model.vo.Culture;
import org.nexters.cultureland.model.vo.CultureRawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("CultureService")
public class CultureServiceImpl implements CultureService{

    @Autowired
    private CultureRepo cultureRepo;

    @Autowired
    private CultureRawRepo cultureRawRepo;

    @Transactional
    public List<Culture> getAllCulture() {
        return cultureRepo.findAll();
    }

    @Transactional
    public List getList() {
        return cultureRepo.findList();
    }

    @Transactional
    public List getByCategory(String category) {
        return cultureRepo.findByCultureName(category);
    }

    @Transactional
    public Optional<CultureRawData> getByCultureId(Long id) {
        return cultureRawRepo.findById(id);
    }

    @Transactional
    public List getBySearch(String query) {
        return cultureRawRepo.findByTitleIgnoreCaseContaining(query);
    }

    @Transactional
    public List<CultureRawData> getAll(){
        List<CultureRawData> test  = cultureRawRepo.findAll();
        for(CultureRawData c : test) {
           c.setCulture(c.getCulture());
        }
        return test;
    }
}
