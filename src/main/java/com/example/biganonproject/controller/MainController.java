package com.example.biganonproject.controller;

import com.example.biganonproject.repository.MyFilesRep;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    MyFilesRep rep;

    public MainController(MyFilesRep rep){
        this.rep = rep;
    }

    @GetMapping("/myNav")
    public String sendTypes(ModelMap map){
        map.addAttribute("allRoles", rep.findAll().stream().map(i -> i.getType()).toList());
        return "navigator";
    }

    @PostMapping("/ListSameTypes")
    public String getOnlyTheseTypes(@RequestParam("types") String type, ModelMap map){
        map.addAttribute("allRoles", rep.findMyFilesByType(".docx"));
        return "ListSameTypes";
    }

}
