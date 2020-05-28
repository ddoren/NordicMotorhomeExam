package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Carmodel;
import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Reservation;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.MotorhomeService;
import com.example.demo.Service.CarmodelService;
import com.example.demo.Service.MotorhomeService;
import com.example.demo.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {



    @Autowired
    MotorhomeService motorhomeService;

    @Autowired
    CustomerService customerService;



    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    //RESERVATIONS
    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservations")
    public String indexReservation(Model model) {
        List<Reservation> reservationsList = reservationService.fetchAll();
        model.addAttribute("reservations", reservationsList);
        return "home/reservations/reservations";
    }

    @GetMapping("/viewOneReservation/{res_id}")
    public String viewOne(@PathVariable("res_id") int res_id, Model model) {
        model.addAttribute("reservation", reservationService.findReservationById(res_id));
        return "home/reservations/viewOneReservation";
    }

    /*@GetMapping("/createReservation")
    public  String createReservation(){
        return "startReservation";
    }

    @PostMapping("/createReservation")
    public String createReservation(@ModelAttribute Reservation reservation){
        reservationService.addReservation(reservation);
        return "redirect:/reservations";
    }*/

    @GetMapping("/updateReservation/{res_id}")
    public String updateReservation(@PathVariable("res_id") int res_id, Model model) {
        model.addAttribute("reservation", reservationService.findReservationById(res_id));
        return "home/reservations/updateReservation";
    }

    @PostMapping("/updateReservation")
    public String updateReservation(@ModelAttribute Reservation reservation) {
        reservationService.updateReservation(reservation.getRes_id(), reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/deleteReservation/{res_id}")
    public String delete(@PathVariable("res_id") int res_id) {
        reservationService.deleteReservation(res_id);
        return "redirect:/reservations";
    }

    @GetMapping("/startReservation")
    public String startReservation() {
        return "/home/reservations/startReservation";
    }

    @RequestMapping(value = "/availableMotorhome", method = RequestMethod.GET)
    public String availableMotorhome(Model model, @RequestParam(value = "cus_id") int cus_id, @RequestParam(value = "date_reservation_start") String date_reservation_start, @RequestParam(value = "date_reservation_end") String date_reservation_end) {
        List<Motorhome> availableMotorhomes = reservationService.availableMotorhomes(date_reservation_start, date_reservation_end);

        model.addAttribute("motorhomes", availableMotorhomes);
        model.addAttribute("cus_id", cus_id);
        model.addAttribute("date_reservation_start", date_reservation_start);
        model.addAttribute("date_reservation_end", date_reservation_end);
        return "/home/reservations/chooseMotorhomeToReserve";
    }

    @GetMapping("/createCustomer")
    public String createCustomer() {
        return "/home/reservations/createCustomer";
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.addCustomer(customer);
        model.addAttribute("customer", customerService.findCustomerByLicense(customer.getDriver_license()));
        return "/home/reservations/startReservation";
    }

    @GetMapping("/finishReservation")
    public String finishReservation(Model model, @RequestParam(value = "cus_id") String cus_id, @RequestParam(value = "date_reservation_start") String date_reservation_start, @RequestParam(value = "date_reservation_end") String date_reservation_end,
                                    @RequestParam(value = "date_made") String date_made, @RequestParam(value = "season") String season, @RequestParam(value = "motor_id") String motor_id) {
        Motorhome motorhome = motorhomeService.findMotorhomeById(Integer.parseInt(motor_id));
//        model.addAttribute("cus_id", cus_id);
//        model.addAttribute("date_reservation_start", date_reservation_start);
//        model.addAttribute("date_reservation_end", date_reservation_end);
//        model.addAttribute("date_made", "2020-02-05");
//        model.addAttribute("season", season);
//        model.addAttribute("motor_id", motor_id);
//      model.addAttribute("motorhome", motorhome);

        //       return "home/finishReservation";

        Reservation reservation = new Reservation();
        reservation.setRes_customer(cus_id);
        reservation.setRes_motorhome(motor_id);
        reservation.setDate_made(date_made);
        reservation.setDate_reservation_start(date_reservation_start);
        reservation.setDate_reservation_end(date_reservation_end);
        reservation.setSeason(season);
        int total_price = 1;
        total_price = motorhome.getPrice_per_day() * customerService.countReservationDays(date_reservation_start, date_reservation_end);
        if (season.equals("low_season")) {
            total_price *= 0.5;
        } else if (season.equals("high_season")) {
            total_price *= 2;
        }
        reservation.setPrice(total_price);
        reservationService.addReservation(reservation);
        model.addAttribute("reservation", reservation);

        return "home/reservations/viewOneReservation";
    }

    //MOTORHOMES


    @GetMapping("/motorhomes")
    public String motorhomes(Model model){
        List <com.example.demo.Model.Motorhome> motorhomeList = motorhomeService.fetchAll();
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
    @GetMapping("/delete_motorhome/{motor_id}")
    public String delete_motorhome(@PathVariable("motor_id") int motor_id, Model model){
        motorhomeService.deleteMotorhome(motor_id);
        return "redirect:/";
    }
    @GetMapping("/update_motorhome/{motor_id}")
    public String update(@PathVariable("motor_id") int motor_id, Model model) {
        model.addAttribute("motorhome", motorhomeService.findMotorhomeById(motor_id));
        return "home/update_motorhome";
    }
    @PostMapping("/update_motorhome")
    public String update_motorhome(@ModelAttribute Motorhome motorhome){
        motorhomeService.updateMotorhome(motorhome.getMotor_id(), motorhome);
        return "redirect:/";
    }

    //MOTORHOME MODEL
    @Autowired
    CarmodelService carmodelService;
    @GetMapping("/carmodels")
    public String carmodels(Model model) {
        List<Carmodel> carmodelList = carmodelService.fetchAll();
        model.addAttribute("carmodels", carmodelList );
        return "home/carmodels";
    }

    @GetMapping("/add_carmodel")
    public String add_carmodel(){
        return "home/add_carmodel";
    }

    @PostMapping ("add_carmodel")
    public String add_carmodel(@ModelAttribute Carmodel carmodel){
        carmodelService.addModel(carmodel);
        return "redirect:carmodels";
    }

    @GetMapping ("/delete_carmodel/{model_id}")
    public String delete_carmodel(@PathVariable("model_id") int model_id, Model model) {
        carmodelService.deleteModel(model_id);
        return "redirect:/";
    }

    @GetMapping ("/update_carmodel/{model_id}")
    public String updateCarmodel(@PathVariable("model_id") int model_id, Model model) {
        model.addAttribute("carmodel", carmodelService.findModelById(model_id));
        return "home/update_carmodel";
    }

    @PostMapping("/update_carmodel")
    public String update_carmodel(@ModelAttribute Carmodel carmodel) {
        carmodelService.updateModel(carmodel.getModel_id(), carmodel);
        return "redirect:/";
    }
}

