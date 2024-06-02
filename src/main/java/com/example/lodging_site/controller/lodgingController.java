package com.example.lodging_site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lodging_site.entity.BfCategory;
import com.example.lodging_site.entity.Lodging;
import com.example.lodging_site.repository.BfCategoryRepository;
import com.example.lodging_site.repository.LodgingRepository;

@Controller
public class lodgingController {
    @Autowired
    LodgingRepository lodgingRepository;
    @Autowired
    BfCategoryRepository bfCategoryRepository;

    @GetMapping("/")
    public String index(
        @RequestParam(value="bfCategoryId", defaultValue="") BfCategory bfCategory,
        Model model
    ) {
        List<BfCategory> bfCategoryList;
        List<Lodging> lodgingList;
        if(bfCategory != null) {
            lodgingList = lodgingRepository.findAllByBfCategory(bfCategory);
        }else{
            lodgingList = lodgingRepository.findAll();
        }
        bfCategoryList = bfCategoryRepository.findAll();
        model.addAttribute("bfList", bfCategoryList);
        model.addAttribute("lodgings", lodgingList);
        return "lodging";
    }
}
