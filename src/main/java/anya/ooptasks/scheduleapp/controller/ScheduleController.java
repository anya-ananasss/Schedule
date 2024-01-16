package anya.ooptasks.scheduleapp.controller;

import anya.ooptasks.scheduleapp.ScheduleAppApplication;
import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ScheduleController {

    ScheduleService service;

    @GetMapping
    String getPeople (Model model){
        model.addAttribute("peoples", Arrays.asList(
                "aboba",
                "huipizda",
                "popa",
                "pipiska",
                "kakashka"
        ));
        return "index";
    }

}

