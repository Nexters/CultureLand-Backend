package org.nexters.cultureland.controller;

import org.nexters.cultureland.model.service.CultureService;
import org.nexters.cultureland.model.service.CultureServiceImpl;
import org.nexters.cultureland.model.vo.Culture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path= "/cultureInfos")
public class CultureController {

    @Autowired
    private CultureServiceImpl cultureService;

    @GetMapping
    public ResponseEntity<List> readAllCultures() {
        return ResponseEntity.ok(cultureService.getAllCulture());
    }
    @GetMapping("/{category}")
    public ResponseEntity<List> readCultureByKind(String cultureName) {
        return ResponseEntity.ok(cultureService.getCultureByKind(cultureName));
    }
}
