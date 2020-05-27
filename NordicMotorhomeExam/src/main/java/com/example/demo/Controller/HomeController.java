package com.example.demo.Controller;

import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Reservation;
import com.example.demo.Service.MotorhomeService;
import com.example.demo.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/")
    public String index(){
        return "home/index";
    }


    //RESEVATIONS
    @GetMapping("/reservations")
    public String indexReservation(Model model){
        List<Reservation> reservationsList = reservationService.fetchAll();
        model.addAttribute("reservations", reservationsList);
        return "home/reservations";
    }

    @GetMapping("/viewOneReservation/{res_id}")
    public String viewOne(@PathVariable("res_id") int res_id, Model model){
        model.addAttribute("reservation", reservationService.findReservationById(res_id));
        return "home/viewOneReservation";
    }

    @GetMapping("/createReservation")
    public  String createReservation(){
        return "home/createReservation";
    }

    @PostMapping("/createReservation")
    public String createReservation(@ModelAttribute Reservation reservation){
        reservationService.addReservation(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/updateReservation/{res_id}")
    public String updateReservation(@PathVariable("res_id") int res_id, Model model) {
        model.addAttribute("reservation", reservationService.findReservationById(res_id));
        return "home/updateReservation";
    }

    @PostMapping("/updateReservation")
    public String updateReservation(@ModelAttribute Reservation reservation){
        reservationService.updateReservation(Integer.parseInt(reservation.getRes_id()), reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/deleteReservation/{res_id}")
    public String delete(@PathVariable("res_id") int res_id){
        reservationService.deleteReservation(res_id);
        return "redirect:/reservations";
    }

    //MOTORHOME MODELS

    //MOTORHOMES
    @Autowired
    MotorhomeService motorhomeService;
    @GetMapping("/motorhomes")
    public String motorhomes(Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        model.addAttribute("motorhomes", motorhomeList);
        return "home/motorhomes";
    }

    @GetMapping("/add_motorhome")
    public String add_motorhome(){
        return "home/add_motorhome";
    }

    @PostMapping ("/add_motorhome")
    public String add_motorhome(@ModelAttribute Motorhome motorhome){
        motorhomeService.addMotorhome(motorhome);
        return "redirect:motorhomes";
    }

    @GetMapping("/delete_motorhome/{moto_id}")
    public String delete_motorhome(@PathVariable("moto_id") int moto_id, Model model){
        motorhomeService.deleteMotorhome(moto_id);
        return "redirect:/";
    }

    @GetMapping("/update_motorhome/{moto_id}")
    public String update(@PathVariable("moto_id") int moto_id, Model model) {
        model.addAttribute("apartment", motorhomeService.findMotorhomeById(moto_id));
        return "home/update_motorhome";
    }

    @PostMapping("/update_motorhome")
    public String update_motorhome(@ModelAttribute Motorhome motorhome){
        motorhomeService.updateMotorhome(motorhome.getMotor_id(), motorhome);
        return "redirect:/";
    }



}
