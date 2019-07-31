package org.nexters.cultureland.controller;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.nexters.cultureland.model.service.CultureService;
import org.nexters.cultureland.model.service.CultureServiceImpl;
import org.nexters.cultureland.model.vo.Culture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/cultureInfos")
public class CultureController {

    /////
    @Autowired
    private CultureServiceImpl cultureService;

    @GetMapping
    public ResponseEntity<List<Object>> readCultures(@RequestParam(value = "category", required = false, defaultValue = "") String category, @RequestParam(value = "find", required = false, defaultValue = "") String find) {
        if(category.equals("") && find.equals(""))
            return ResponseEntity.ok(cultureService.getList());
        else if(category.length() != 0 && find.equals(""))
                return ResponseEntity.ok(cultureService.getByCategory(category));
        return ResponseEntity.ok(cultureService.getBySearch(find));
    }

    @GetMapping("/{cultureInfoId}")
    public ResponseEntity readDetailById(@PathVariable("cultureInfoId") Long id) {
        System.out.println("asdfasfasfasdid"+id);
        return ResponseEntity.ok(cultureService.getByCultureId(id));
    }

    @GetMapping("/test/test")
    public ResponseEntity testall() {
        return ResponseEntity.ok(cultureService.getAll());
    }
}
