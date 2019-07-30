package org.nexters.cultureland.model.service;

import org.nexters.cultureland.model.dao.CultureRepo;
import org.nexters.cultureland.model.vo.Culture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("CultureService")
public class CultureServiceImpl implements CultureService{

    @Autowired
    private CultureRepo cultureRepo;

    @Transactional
    public List<Culture> getAllCulture() {
        return cultureRepo.findAll();
    }

    @Override
    public List getList() {
        return cultureRepo.findList();
    }

    @Transactional
    public List getByCategory(String category) {
        return cultureRepo.findByCultureName(category);
    }
}
