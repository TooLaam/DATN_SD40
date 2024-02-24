package com.example.sd40.controller;

import com.example.sd40.entity.*;
import com.example.sd40.service.CustomerService;
import com.example.sd40.service.EmployeeService;
import com.example.sd40.service.serviceimpl.CartServiceImpl;
import com.example.sd40.service.serviceimpl.CartdetailServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/customer")

public class CustomerController {


    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    CartdetailServiceImpl cartdetailService;

//    @Autowired
//    private ModelMapper modelMapper;

    @GetMapping("/index")
    public String HienThi(Model model, HttpSession session, @RequestParam(value = "pageNo",defaultValue = "0")Integer page) {
        if (session.getAttribute("Name") != null) {
            //Nếu đã đăng nhập vào trang index

            model.addAttribute("cusList", customerService.findAll(page));


            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            Employee checkLogin = employeeService.login(username, password);
            session.setAttribute("checkRole", employeeService.checkRole(username));

            session.setAttribute("Name", checkLogin);
            model.addAttribute("empLogin", checkLogin);
            model.addAttribute("view", "/Customer/index.jsp");
            return "index";



        }
        //Nếu chưa đăng nhập thì return về trang logina
        return "login";

    }

    @GetMapping("/indexcus")
    public String show_data_customer_cus(Model model, HttpSession session) {
        String username = (String) session.getAttribute("CustomerName");
        String password = (String) session.getAttribute("CustomerPass");
        model.addAttribute("cus", customerService.login(username, password));
        model.addAttribute("cusList", customerService.findAll());
        model.addAttribute("view", "/account/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/indexcus/changeAccountInfo")
    public String changeAccountInfo(Model model, HttpSession session) {
        String username = (String) session.getAttribute("CustomerName");
        String password = (String) session.getAttribute("CustomerPass");
        model.addAttribute("cus", customerService.login(username, password));
        model.addAttribute("cusList", customerService.findAll());
        model.addAttribute("view", "/changeAccountInfo/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("view", "/signup/index.jsp");
        return "/customerFE/signup/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "/login/index.jsp");
        return "/customerFE/login/index";
    }

    @PostMapping("/loginOK")
    public String loginOK(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpSession session,
                          Model model) {
        if (username == "" || password == "") {
            model.addAttribute("erTrongCustomer", "Please enter complete information !!!");
            return "/customerFE/login/index";
        } else {
            Customer checkLogin = customerService.login(username, password);
            if (!(checkLogin == null)) {
                session.setAttribute("CustomerName", username);
                session.setAttribute("CustomerPass", password);
                return "redirect:/home";
            } else {
                model.addAttribute("erCheckCustomer", "Username and password are incorrect");
                return "/customerFE/login/index";
            }
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("CustomerName");
        return "redirect:/customer/login";
    }

    @GetMapping("/viewAdd")
    public String viewAdd(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username, password);
        session.setAttribute("checkRole", employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/Customer/add.jsp");
        return "index";

    }

    public void valiAdd(HttpSession session,Model model,String fullname,String dateofbirth,String address,String phone,String email, String username1,String password1){
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username, password);
        session.setAttribute("checkRole", employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("fullnameAdd",fullname);
        model.addAttribute("usernameAdd",username1);
        model.addAttribute("passwordAdd",password1);
        model.addAttribute("dateOfBirthAdd",dateofbirth);
        model.addAttribute("emailAdd",email);
        model.addAttribute("addressAdd",address);
        model.addAttribute("phoneNumberAdd",phone);
    }

    @PostMapping("/add")
    public String themMoi(Model model,
                          HttpSession session,
                          @RequestParam("fullname") String fullname,
                          @RequestParam("dateofbirth") String dateofbirth,
                          @RequestParam("address") String address,
                          @RequestParam("phone") String phone,
                          @RequestParam("email") String email,
                          @RequestParam("gender") int gender,
                          @RequestParam("username") String username1,
                          @RequestParam("password") String password1
    ) {
        if (session.getAttribute("Name") != null){
            if (fullname.isBlank()){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errName","Invalid Receiver FullName");
                model.addAttribute("view", "/Customer/add.jsp");
                return "index";

            }
            if (dateofbirth.isBlank()){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errDate","Invalid Receiver Date Of Birth");
                model.addAttribute("view", "/Customer/add.jsp");
                return "index";        }
            String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

            if (phone.isBlank()){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errPhone","Invalid Receiver Phone Number");
                model.addAttribute("view", "/Customer/add.jsp");
                return "index";        }
            if (email.isBlank()){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errEmail","Invalid Receiver Email");
                model.addAttribute("view", "/Customer/add.jsp");
                return "index";        }
            if (address.isBlank()){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errAdd","Invalid Receiver Address");
                model.addAttribute("view", "/Customer/add.jsp");
                return "index";        }
            if (username1.isBlank()){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errUser","Invalid Receiver UserName");
                model.addAttribute("view", "/Customer/add.jsp");
                return "index";        }
            if (password1.isBlank()){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errPass","Invalid Receiver Password");
                model.addAttribute("view", "/Customer/add.jsp");
                return "index";        }
            else {
                List<Customer>  checkUser = customerService.getByUserName(username1);
                if (!(checkUser.isEmpty())){
                    valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                    model.addAttribute("errUserTrung","Duplicate Username !!! Please enter another username");
                    model.addAttribute("view", "/Customer/add.jsp");
                    return "index";            }
                Date currentDate = new Date(System.currentTimeMillis());
                Date birth = Date.valueOf(dateofbirth);
                if (birth.after(currentDate)){
                    valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                    model.addAttribute("errDateAfter","Date of birth must be less than current date !!!");
                    model.addAttribute("view", "/Customer/add.jsp");
                    return "index";            }
                if (!phone.matches(Regex)){
                    valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                    model.addAttribute("errPhoneErrr","Phone number syntax is incorrect !!!");
                    model.addAttribute("view", "/Customer/add.jsp");
                    return "index";
                }
                Customer customer = new Customer();
                customer.setFullname(fullname);
                customer.setDateofbirth(Date.valueOf(dateofbirth));
                customer.setAddress(address);
                customer.setPhone(phone);
                customer.setDatecreated(String.valueOf(currentDate));
                customer.setEmail(email);
                customer.setGender(gender);
                customer.setStatus(1);
                customer.setUsername(username1);
                customer.setPassword(password1);
                customerService.add(customer);
                Cart cart = new Cart();
                cart.setQuantity(0);
                cart.setTotalMoney(BigDecimal.valueOf(0));
                cart.setTotalMoney(BigDecimal.valueOf(0));
                cart.setCustomer(customer);
                cartService.save(cart);
                CartDetail cartDetail = new CartDetail();
                cartDetail.setPrice(BigDecimal.valueOf(0));
                cartDetail.setQuantity(0);
                cartDetail.setProductDetail(null);
                cartDetail.setCart(cart);
                cartdetailService.save(cartDetail);
                return "redirect:/customer/index";
            }

        }else{
            return "login";
        }
    }


    @PostMapping("/addLogin")
    public String themMoiLogin(Model model,
                                HttpSession session,
                               @RequestParam("fullname") String fullname,
                               @RequestParam("dateofbirth") String dateofbirth,
                               @RequestParam("address") String address,
                               @RequestParam("phone") String phone,
                               @RequestParam("email") String email,
                               @RequestParam("gender") int gender,
                               @RequestParam("username") String username1,
                               @RequestParam("password") String password1
    ) {
        if (fullname.isBlank()){
            valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
            model.addAttribute("errName","Invalid Receiver FullName");
            return "/customerFE/signup/index";

        }
        if (dateofbirth.isBlank()){
            valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
            model.addAttribute("errDate","Invalid Receiver Date Of Birth");
            return "/customerFE/signup/index";
        }
        String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

        if (phone.isBlank()){
            valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
            model.addAttribute("errPhone","Invalid Receiver Phone Number");
            return "/customerFE/signup/index";
        }
        if (email.isBlank()){
            valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
            model.addAttribute("errEmail","Invalid Receiver Email");
            return "/customerFE/signup/index";
        }
        if (address.isBlank()){
            valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
            model.addAttribute("errAdd","Invalid Receiver Address");
            return "/customerFE/signup/index";
        }
        if (username1.isBlank()){
            valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
            model.addAttribute("errUser","Invalid Receiver UserName");
            return "/customerFE/signup/index";
        }
        if (password1.isBlank()){
            valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
            model.addAttribute("errPass","Invalid Receiver Password");
            return "/customerFE/signup/index";
        }else {
            List<Customer>  checkUser = customerService.getByUserName(username1);
            if (!(checkUser.isEmpty())){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errUserTrung","Duplicate Username !!! Please enter another username");
                return "/customerFE/signup/index";     }
            Date currentDate = new Date(System.currentTimeMillis());
            Date birth = Date.valueOf(dateofbirth);
            if (birth.after(currentDate)){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errDateAfter","Date of birth must be less than current date !!!");
                return "/customerFE/signup/index";    }
            if (!phone.matches(Regex)){
                valiAdd(session,model,fullname,dateofbirth,address,phone,email,username1,password1);
                model.addAttribute("errPhoneErrr","Phone number syntax is incorrect !!!");
                return "/customerFE/signup/index";
            }
            Customer customer = new Customer();
            customer.setFullname(fullname);
            customer.setDateofbirth(Date.valueOf(dateofbirth));
            customer.setAddress(address);
            customer.setPhone(phone);
            customer.setDatecreated(String.valueOf(currentDate));
            customer.setEmail(email);
            customer.setGender(gender);
            customer.setStatus(1);
            customer.setUsername(username1);
            customer.setPassword(password1);
            customerService.add(customer);
            Cart cart = new Cart();
            cart.setId(customer.getId());
            cart.setQuantity(0);
            cart.setTotalMoney(BigDecimal.valueOf(0));
            cart.setTotalMoney(BigDecimal.valueOf(0));
            cart.setCustomer(customer);
            cart.setStatus(0);
            cartService.save(cart);
            CartDetail cartDetail = new CartDetail();
            cartDetail.setPrice(BigDecimal.valueOf(0));
            cartDetail.setQuantity(0);
            cartDetail.setProductDetail(null);
            cartDetail.setCart(cart);
            cartdetailService.save(cartDetail);
            model.addAttribute("sigsUp", "Sign Up Success !!! please log in");
            return "/customerFE/login/index";
        }
    }

    public void valiUpdate(Model model,HttpSession session,UUID id){
        model.addAttribute("cus", customerService.detail(id));
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username, password);
        session.setAttribute("checkRole", employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/Customer/detail.jsp");
    }

    public void valiUpdateCus(Model model,HttpSession session,UUID id){
        model.addAttribute("cus", customerService.detail(id));
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username, password);
        session.setAttribute("checkRole", employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/changeAccountInfo/index.jsp");
    }

    @PostMapping("/update")
    public String update(Model model,
                         @RequestParam("id") UUID id,
                         @RequestParam("fullname") String fullname,
                         @RequestParam("dateofbirth") String dateofbirth,
                         @RequestParam("address") String address,
                         @RequestParam("phone") String phone,
                         @RequestParam("email") String email,
                         @RequestParam("gender") int gender,
                         @RequestParam("status") Integer status,
                         @RequestParam("username") String username1,
                         @RequestParam("password") String password1,
                         HttpSession session
    ) {
        if (fullname.isBlank()){
            model.addAttribute("errName","Invalid Receiver FullName");
            valiUpdate(model,session,id);
            return "index";

        }
        if (dateofbirth.isBlank()){
            model.addAttribute("errDate","Invalid Receiver Date Of Birth");
            valiUpdate(model,session,id);
            return "index";        }
        String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

        if (phone.isBlank()){
            model.addAttribute("errPhone","Invalid Receiver Phone Number");
            valiUpdate(model,session,id);
            return "index";        }
        if (email.isBlank()){
            model.addAttribute("errEmail","Invalid Receiver Email");
            valiUpdate(model,session,id);
            return "index";
        }
        if (address.isBlank()){
            model.addAttribute("errAdd","Invalid Receiver Address");
            valiUpdate(model,session,id);
            return "index";
        }
        if (username1.isBlank()){
            model.addAttribute("errUser","Invalid Receiver UserName");
            valiUpdate(model,session,id);
            return "index";        }
        if (password1.isBlank()){
            model.addAttribute("errPass","Invalid Receiver Password");
            valiUpdate(model,session,id);
            return "index";
        }else {
            List<Customer> checkUser = customerService.checkUserNameUpdate(customerService.detail(id).getUsername(),username1);
            if (!(checkUser.isEmpty())) {
                model.addAttribute("errUserTrung", "Duplicate Username !!! Please enter another username");
                valiUpdate(model,session,id);
                return "index";            }
            Date currentDate = new Date(System.currentTimeMillis());
            Date birth = Date.valueOf(dateofbirth);
            if (birth.after(currentDate)) {
                model.addAttribute("errDateAfter", "Date of birth must be less than current date !!!");
                valiUpdate(model,session,id);
                return "index";            }
            if (!phone.matches(Regex)) {
                model.addAttribute("errPhoneErrr", "Phone number syntax is incorrect !!!");
                valiUpdate(model,session,id);
                return "index";            }
            Customer customer = new Customer();

            customer.setFullname(fullname);
            customer.setDateofbirth(Date.valueOf(dateofbirth));
            customer.setAddress(address);
            customer.setPhone(phone);
            customer.setId(id);
            customer.setEmail(email);
            customer.setGender(gender);
            customer.setStatus(status);
            customer.setUsername(username1);
            customer.setPassword(password1);
            customer.setDatecreated(String.valueOf(currentDate));


            customerService.update(customer);
            return "redirect:/customer/index";
        }
    }


    @PostMapping("/updateCus/{id}")
    public String updateCus(Model model,
                         @PathVariable("id") UUID id,
                         @RequestParam("fullname") String fullname,
                         @RequestParam("dateofbirth") String dateofbirth,
                         @RequestParam("address") String address,
                         @RequestParam("phone") String phone,
                         @RequestParam("email") String email,
                         @RequestParam("gender") int gender,
//                         @RequestParam("status") Integer status,
                         @RequestParam("username") String username1,
                         @RequestParam("password") String password1,
                         HttpSession session
    ) {
        if (fullname.isBlank()){
            model.addAttribute("errName","Invalid Receiver FullName");
            valiUpdateCus(model,session,id);
            return "/customerFE/index";

        }
        if (dateofbirth.isBlank()){
            model.addAttribute("errDate","Invalid Receiver Date Of Birth");
            valiUpdateCus(model,session,id);
            return "/customerFE/index";        }
        String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

        if (phone.isBlank()){
            model.addAttribute("errPhone","Invalid Receiver Phone Number");
            valiUpdateCus(model,session,id);
            return "/customerFE/index";        }
        if (email.isBlank()){
            model.addAttribute("errEmail","Invalid Receiver Email");
            valiUpdateCus(model,session,id);
            return "/customerFE/index";
        }
        if (address.isBlank()){
            model.addAttribute("errAdd","Invalid Receiver Address");
            valiUpdateCus(model,session,id);
            return "/customerFE/index";
        }
        if (username1.isBlank()){
            model.addAttribute("errUser","Invalid Receiver UserName");
            valiUpdateCus(model,session,id);
            return "/customerFE/index";        }
        if (password1.isBlank()){
            model.addAttribute("errPass","Invalid Receiver Password");
            valiUpdateCus(model,session,id);
            return "/customerFE/index";
        }else {
            List<Customer> checkUser = customerService.checkUserNameUpdate(customerService.detail(id).getUsername(),username1);
            if (!(checkUser.isEmpty())) {
                model.addAttribute("errUserTrung", "Duplicate Username !!! Please enter another username");
                valiUpdateCus(model,session,id);
                return "/customerFE/index";            }
            Date currentDate = new Date(System.currentTimeMillis());
            Date birth = Date.valueOf(dateofbirth);
            if (birth.after(currentDate)) {
                model.addAttribute("errDateAfter", "Date of birth must be less than current date !!!");
                valiUpdateCus(model,session,id);
                return "/customerFE/index";            }
            if (!phone.matches(Regex)) {
                model.addAttribute("errPhoneErrr", "Phone number syntax is incorrect !!!");
                valiUpdateCus(model,session,id);
                return "/customerFE/index";            }
            Customer customer = new Customer();

            customer.setFullname(fullname);
            customer.setDateofbirth(Date.valueOf(dateofbirth));
            customer.setAddress(address);
            customer.setPhone(phone);
            customer.setId(id);
            customer.setEmail(email);
            customer.setGender(gender);
            customer.setStatus(0);
            customer.setUsername(username1);
            customer.setPassword(password1);
            customer.setDatecreated(String.valueOf(currentDate));


            customerService.update(customer);
            return "redirect:/customer/indexcus";
        }
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, HttpSession session) {
        model.addAttribute("cus", customerService.detail(id));
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username, password);
        session.setAttribute("checkRole", employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/Customer/detail.jsp");
        return "index";

    }

    @GetMapping("/timKiem")
    public String timKiem(Model model,
                          @RequestParam("phone1") String phone,
                          HttpSession session,
                          @RequestParam(value = "pageNo",defaultValue = "0")Integer page) {


        if (phone == "") {
            model.addAttribute("cusList", customerService.findAll(page));
        } else {
            model.addAttribute("tim", customerService.timKiem(phone));
        }
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username, password);
        session.setAttribute("checkRole", employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/Customer/index.jsp");
        return "index";

    }
}
