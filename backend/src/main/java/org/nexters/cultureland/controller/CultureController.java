package org.nexters.cultureland.controller;

import org.nexters.cultureland.model.service.CultureService;
import org.nexters.cultureland.model.service.CultureServiceImpl;
import org.nexters.cultureland.model.vo.Culture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/cultureInfos")
public class CultureController {

    /////
    @Autowired
    private CultureServiceImpl cultureService;

    @GetMapping
    public ResponseEntity<List<Culture>> readAllCultures() {
        return ResponseEntity.ok(cultureService.getList());
    }

    @GetMapping("/{category}")
    public ResponseEntity<List> readCultureByKind(@PathVariable String category) {
        return ResponseEntity.ok(cultureService.getByCategory(category));
    }
}
