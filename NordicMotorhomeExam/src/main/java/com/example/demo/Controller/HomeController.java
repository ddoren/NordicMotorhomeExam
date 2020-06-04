package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Model.Motorhome;
import com.example.demo.Service.*;
import com.example.demo.Service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

/**
 * @author
 *
 */
@Controller
public class HomeController {

    static Employee trackEmployee=new Employee();
    @Autowired
    ReservationService reservationService;
    @Autowired
    LoginService loginService;
    @Autowired
    ExtrasService extrasService;
    @Autowired
    InvoiceService invoiceService;

    @Autowired
    MotorhomeService motorhomeService;
    @Autowired
    CustomerService customerService;





    @GetMapping("/")
    public String index() {
        return "home/index";
    }
    //SPECIFIC users mapping
    @GetMapping("/salesassistant/salesassistant")
    public String salesAssistant()
    {
        return "home/salesassistant/salesassistant";
    }
    @GetMapping("/bookkeeper/bookkeeper")
    public String booKeeper()
    {return  "home/bookkeeper/bookkeeper";}
    @GetMapping("/returnToMenu")
            public String returnToMenu()
    {
        if(trackEmployee.getType_employee().equalsIgnoreCase("owner"))
        {
            return "redirect:/owner";
        }
        else if(trackEmployee.getType_employee().equalsIgnoreCase("sales assistant"))
        {
            return "redirect:/salesassistant/salesassistant";
        }
        else return "redirect:/motorhomemaintanence/repairMenu";
    }
    @GetMapping("/motorhomemaintanence/repairMenu")
            public String repairMenu(Model model)
    {
        List<Motorhome> motorhomeList =motorhomeService.fetchAll();
        model.addAttribute("motorhomes", motorhomeList);
        return "home/motorhomemaintanence/repairMenu";
    }
    //RESERVATIONS
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
        int nr_days = reservationService.countReservationDays(reservation.getDate_reservation_start(), reservation.getDate_reservation_end());
        Motorhome motorhome = motorhomeService.findMotorhomeById(Integer.parseInt(reservation.getRes_motorhome()));
        int price_per_day = motorhome.getPrice_per_day();
        int new_price = nr_days * price_per_day;
        reservation.setPrice(new_price);
        reservationService.updateReservation(reservation.getRes_id(), reservation);
        return "redirect:/viewOneReservation/" + reservation.getRes_id();
    }

    @GetMapping("/deleteReservation/{res_id}")
    public String delete(@PathVariable("res_id") int res_id)
    {
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
        String  current_date = reservationService.getCurrentDate();
        model.addAttribute("current_date", current_date);
        return "/home/reservations/chooseMotorhomeToReserve";
    }

    @GetMapping("reservations/createCustomer")
    public String createCustomer() {
        return "home/createCustomer";
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.addCustomer(customer);
        model.addAttribute("customer", customerService.findCustomerByLicense(customer.getDriver_license()));
        String  current_date = reservationService.getCurrentDate();
        model.addAttribute("current_date", current_date);
        return "/home/reservations/startReservation";
    }

    @PostMapping("/createReservation")
    public String createReservation(@ModelAttribute Reservation reservation) {
        String res_motorhome = reservation.getRes_motorhome();
        Motorhome motorhome = motorhomeService.findMotorhomeById(Integer.parseInt(res_motorhome));
        int total_price = motorhome.getPrice_per_day() * reservationService.countReservationDays(reservation.getDate_reservation_start(),reservation.getDate_reservation_end());

        if (reservation.getSeason().equals("Normal Season")) {
            total_price *=  1.3;
        } else if (reservation.getSeason().equals("High Season")) {
            total_price *= 1.6;
        }
        reservation.setPrice(total_price);
        reservationService.addReservation(reservation);
        int reservation_id = reservationService.getLastInsertedId();
        System.out.println(reservation.getPrice());
        return "redirect:/finishReservation/" + reservation_id;
    }

    @GetMapping("/finishReservation/{res_id}")
    public String finishReservation(Model model, @PathVariable("res_id") int res_id){
        List<Extras> extras = extrasService.fetchAll();
        model.addAttribute("res_id", res_id);
        model.addAttribute("extras", extras);
        return "home/reservations/finishReservation";
    }
    @RequestMapping(value = "/finishReservation", method = RequestMethod.GET)
    public String finishReservation(@RequestParam(value = "pick_in_store") String pick_in_store, @RequestParam(value = "distance") int distance, @RequestParam(value = "extra_id") int extra_id, @RequestParam(value = "res_id") int res_id) {
        Reservation reservation = reservationService.findReservationById(res_id);
        System.out.println(pick_in_store);
        System.out.println(distance);
        System.out.println(extra_id);
        System.out.println(res_id);
        int reservation_price = (int) reservation.addDropOff(pick_in_store, distance);
        Extras extra = extrasService.findExtraByID(extra_id);
        int extra_price = extra.getPrice();
        int total_price = reservation_price + extra_price;
        System.out.println(total_price);
        reservation.setPrice(total_price);
        reservationService.updatePrice(total_price, res_id);
        reservationService.addExtraIntoReservation(res_id, extra_id);
        return "redirect:/viewOneReservation/" + res_id;

    }
/*
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
        int total_price = motorhome.getPrice_per_day() * reservationService.countReservationDays(date_reservation_start, date_reservation_end);
        if (season.equals("Normal Season")) {
            total_price *=  1.3;
        } else if (season.equals("High Season")) {
            total_price *= 1.6;
        }
        reservation.setPrice(total_price);
        reservationService.addReservation(reservation);
        int reservation_id = reservationService.getLastInsertedId();
        Reservation reservationFromSQL = reservationService.findReservationById(reservation_id);
        System.out.println(reservationFromSQL.toString());
        model.addAttribute("reservation", reservationFromSQL);

        return "home/reservations/viewOneReservation";
    }*/

    //LOGIN
    /**
     * @author Alexander
     * The idea of this method is to redirect the user to his appropriate main menu
     * @param validation is an object used to store the inputed email and password from the fields
     * @return it returns a string view which redirects the user to the correct menu depending on their role in the motorhome
     *
     */
    @PostMapping("/login")
    public String loginCheck(@ModelAttribute Validation validation) {
        //with validation we find the correct employee and save it in an object of the employee type
        //this employee is a global parameter so it can keep
        trackEmployee = loginService.findEmployee(validation.getEmail(), validation.getEmploy_pass());
        if (trackEmployee.getType_employee().equalsIgnoreCase("owner")) {
            return "redirect:/owner";
        } else if (trackEmployee.getType_employee().equalsIgnoreCase("sales assistant")) {
            return "redirect:/salesassistant/salesassistant";
        } else if (trackEmployee.getType_employee().equalsIgnoreCase("bookkeeper"))
        {
            return "redirect:/bookkeeper/bookkeeper";
        }
       else
           {
               return "redirect:/motorhomemaintanence/repairMenu";
           }
   }
   //Log out
    @GetMapping("/logout")
    public String logout()
    {
        trackEmployee=null;
        return "redirect:/";
    }
       @GetMapping("/owner")
       public String owner() {
           return "home/owner";
       }

   //Exceptions
    /*@ControllerAdvice
    public class controllerAdvice
    {
    @ExceptionHandler({Exception.class, SQLException.class, DataAccessException.class})
        public ModelAndView handleUserException(Exception ex)
    {
        ModelAndView modelAndView= new ModelAndView("home/error2",ex.getMessage(),ex.getCause());
        return modelAndView ;
    }
    }
    @GetMapping("/error2")
    public String errorHandling()
    {
        return "home/error2";
    }*/
       //EXTRAS
    @GetMapping("/extras")
    public String extras(Model model)
    {
        List<Extras> extrasList = extrasService.fetchAll();
        model.addAttribute("extras", extrasList);
        return "home/extras";
    }
    @GetMapping("/viewOneExtra/{extra_id}")
    public String viewOneExtra(@PathVariable("extra_id") int extra_id, Model model)
    {
    model.addAttribute("extra", extrasService.findExtraByID(extra_id));
    return "home/viewOneExtra";
    }
    @GetMapping("/updateExtra/{extra_id}")
    public String updateExtra(@PathVariable("extra_id") int extra_id,Model model )
    {
        model.addAttribute("extra",extrasService.findExtraByID(extra_id));
       return "home/updateExtra";
    }
    @PostMapping("/updateExtra")
    public String updateExtra(@ModelAttribute Extras extras)
    {
        extrasService.updateExtra( extras ,extras.getExtra_id());
        return  "redirect:/extras";
    }
    @GetMapping("/deleteExtra/{extra_id}")
    public String deleteExtra(@PathVariable("extra_id") int extra_id){
        extrasService.deleteExtra(extra_id);

        return "redirect:/extras";
    }

    @GetMapping("/createExtra")
    public  String createExtra(){
        return "home/createExtra";
    }

    @PostMapping("/createExtra")
    public String createExtra(@ModelAttribute Extras extra){
        extrasService.addExtras(extra);
        return "redirect:/extras";
    }


    //MOTORHOMES


    @GetMapping("/motorhomes")
    public String motorhomes(Model model){
        List <Motorhome> motorhomeList = motorhomeService.fetchAll();
        model.addAttribute("motorhomes", motorhomeList);
        if(trackEmployee.getType_employee().equalsIgnoreCase("owner")) {
            return "home/motorhomes";
        }
        else if(trackEmployee.getType_employee().equalsIgnoreCase("mechanic"))
        {
            return "home/motorhomemaintanence/repairMenu";
        }
        else return "home/error2";
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
    //Employee Just to view
    @GetMapping("/employee")
    public String viewEmployee(Model model)
    {
        List<Employee> employeeList = loginService.findAllEmployee();
        model.addAttribute("employees",employeeList);
        return "home/employee";
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


    // Invoices

    @GetMapping("/displayInvoices")
    public String displayInvoices(Model model) {
        List<Invoice> invoiceList = invoiceService.fetchAll();
        model.addAttribute("invoices", invoiceList);
        return "home/displayInvoices";
    }

    @GetMapping("/startInvoice")
    public String startInvoice(Model model) {
        List<Reservation> reservationList = reservationService.fetchAll();
        model.addAttribute("reservations", reservationList);
        return "home/startInvoice";
    }


    @GetMapping("/continueInvoice/{res_id}")
    public String availableMotorhome(@PathVariable("res_id") int res_id, Model model) {
        Reservation reservation = reservationService.findReservationById(res_id);
        model.addAttribute("reservation", reservation);
        Customer customer = customerService.findCustomerById(Integer.parseInt(reservation.getRes_customer()));
        model.addAttribute("customer", customer);
        return "home/continueInvoice";
    }

    @PostMapping("/createFinalInvoice")
    public String createInvoice(@ModelAttribute Invoice invoice){
        int total_price = 0;
        if (invoice.getCanceled().equals("No")){
            invoice.setService("Motorhome Rent");
            total_price = invoice.getPrice_per_day() * invoice.getNr_days() + invoice.getPrice_for_extras() + invoice.getAddit_expenses();
        }else {
            invoice.setService("Reservation");

        }
        invoice.setTotal_price(total_price);
        invoiceService.addInvoice(invoice);
        int created_invoice = reservationService.getLastInsertedId();
        reservationService.stopDisplayReservation(Integer.parseInt(invoice.getRes_id()), created_invoice);
        return "redirect:/displayInvoices";

    }
    @GetMapping("/cancelReservation/{res_id}")
    public String cancelReservation(@PathVariable("res_id") int res_id, Model model) {
        Invoice invoice = new Invoice();
        invoice.setCanceled("Yes");
        model.addAttribute("invoice", invoice);
        Reservation reservation = reservationService.findReservationById(res_id);
        String  current_date = reservationService.getCurrentDate();
        model.addAttribute("current_date", current_date);
        String reservation_start_date = reservation.getDate_reservation_start();
        int number_days = reservationService.countReservationDays(current_date,  reservation_start_date);
        model.addAttribute("days_before_reservation", number_days);
        int total_price = reservation.calculateCancelPrice(reservation.getPrice(), number_days);
        reservation.setPrice(total_price);
        model.addAttribute("reservation", reservation);
        Customer customer = customerService.findCustomerById(Integer.parseInt(reservation.getRes_customer()));
        model.addAttribute("customer", customer);
        return "home/reservations/cancelReservation";
    }



    

}

