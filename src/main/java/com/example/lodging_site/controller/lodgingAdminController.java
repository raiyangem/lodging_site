package com.example.lodging_site.controller;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.lodging_site.entity.BfCategory;
import com.example.lodging_site.entity.Lodging;
import com.example.lodging_site.repository.BfCategoryRepository;
import com.example.lodging_site.repository.LodgingRepository;


@Controller
public class lodgingAdminController {
    @Autowired
    LodgingRepository lodgingRepository;
    @Autowired
    BfCategoryRepository bfCategoryRepository;

    @GetMapping("/admin/lodging")
    public String adminLodging(
        Model model
    ) {
        List<Lodging> lodgingList = lodgingRepository.findAll();
        model.addAttribute("lodgings", lodgingList);
        return "adminLodging";
    }

    @GetMapping("/admin/lodging/edit/{id}")
    public String editLodging(
        @PathVariable("id") Long id,
        Model model
    ) {
        List<BfCategory> bfCategoryList = bfCategoryRepository.findAll();
        model.addAttribute("bfList", bfCategoryList);
        Optional<Lodging> l = lodgingRepository.findById(id);
        model.addAttribute("eL", l.get());

        return "adminLodgingDetail";
    }

    @PostMapping("/admin/lodging/edit")
    public String editLodging(
        @RequestParam("id") Long id,
        @RequestParam("lodgingName") String lodgingName,
        @RequestParam(value="bfCategoryId", defaultValue="") BfCategory bfCategory,
        @RequestParam("address") String address,
        @RequestParam("checkIn") LocalTime checkIn,
        @RequestParam("checkOut") LocalTime checkOut,
        @RequestParam("img") MultipartFile file,
        Model model
    )throws IOException{
        Optional<Lodging> is = null;
		Path dst = null;
		String fileName;
		if (!file.isEmpty()) {
			// 新しい画像がアップロードされた場合のみ保存処理を行う
			dst = Path.of("src\\main\\resources\\static\\img", file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), dst);
			} catch (FileAlreadyExistsException e) {
			} finally {
				fileName = file.getOriginalFilename();
			}
		} else {
			is = lodgingRepository.findById(id);
			fileName = is.get().getImg();
		}
		Lodging lodgingSet = new Lodging(id, lodgingName, bfCategory, address, checkIn, checkOut, fileName);
		lodgingRepository.save(lodgingSet);
        return "redirect:/admin/lodging";
    }

    @GetMapping("/admin/lodging/new")
    public String newLodging(Model model) {
        List<BfCategory> bfCategoryList = bfCategoryRepository.findAll();
        model.addAttribute("bfList", bfCategoryList);
        return "adminLodgingDetail";
    }

    @PostMapping("/admin/lodging/new")
    public String newLodging(
        @RequestParam("lodgingName") String lodgingName,
        @RequestParam(value="bfCategoryId", defaultValue="") BfCategory bfCategory,
        @RequestParam("address") String address,
        @RequestParam("checkIn") LocalTime checkIn,
        @RequestParam("checkOut") LocalTime checkOut,
        @RequestParam("img") MultipartFile file,
        Model model
    )throws IOException{
		Path dst = Path.of("src\\main\\resources\\static\\img", file.getOriginalFilename());
		try {
			Files.copy(file.getInputStream(), dst);
		} catch (FileAlreadyExistsException e) {
			String fileName = file.getOriginalFilename();

			Lodging lSet = new Lodging(lodgingName, bfCategory, address, checkIn, checkOut, fileName);

			lodgingRepository.save(lSet);

			return "redirect:/admin/lodging";
		}

		String fileName = file.getOriginalFilename();

		Lodging lSet = new Lodging(lodgingName, bfCategory, address, checkIn, checkOut, fileName);

        lodgingRepository.save(lSet);
        return "redirect:/admin/lodging";
    }

}
