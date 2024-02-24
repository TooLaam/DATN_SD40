package com.example.sd40.controller;


import com.example.sd40.entity.Employee;
import com.example.sd40.entity.Role;
import com.example.sd40.service.EmployeeService;
import com.example.sd40.service.RoleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller

public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RoleService roleService;

    @GetMapping("/Login")
    public String login(){
        return "login";
    }

    @GetMapping("/indexxx")
    public String indexxx(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username,password);
        session.setAttribute("checkRole",employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        return "index";


    }


    @PostMapping("/loginOK")
    public String loginOK(@RequestParam("username")String username,
                          @RequestParam("password")String password,
                          HttpSession session,
                          Model model){
        if (username == ""||password == ""){
            model.addAttribute("erTrong", "Please enter complete information !!!");
            return "login";
        }
        else {
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            Employee checkLogin = employeeService.login(username,password);
            session.setAttribute("checkRole",employeeService.checkRole(username));
            if (!(checkLogin == null)){
                session.setAttribute("Name", checkLogin);
                model.addAttribute("empLogin",checkLogin);
                return "index";
            }
            else {
                model.addAttribute("erCheck","Username and password are incorrect");
                model.addAttribute("reUsername",username);
                model.addAttribute("rePassword",password);
                return "login";
            }
        }

    }
    @GetMapping("/logout")
    public String loguot(HttpSession session){
        session.removeAttribute("Name");
        return "redirect:/Login";
    }

    @GetMapping("/employee/index")
    public String HienThi(Model model,HttpSession session,@RequestParam(value = "pageNo",defaultValue = "0")Integer page) {


        if (session.getAttribute("Name") != null){
            if (session.getAttribute("checkRole") == null){
                if (employeeService.findAll().isEmpty()){
                    model.addAttribute("erList","Empty list");
                }
                Page<Employee> customerList = employeeService.listDesc(page) ;
                model.addAttribute("empList", customerList);
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("password");
                Employee checkLogin = employeeService.login(username,password);
                session.setAttribute("checkRole",employeeService.checkRole(username));

                session.setAttribute("Name", checkLogin);
                model.addAttribute("empLogin",checkLogin);

                model.addAttribute("view", "/Employee/index.jsp");
                return "index";
            }
            else {
                model.addAttribute("erCheckRole","Employees cannot use this function");
                return "login";
            }

        }
        return "login";


    }
    public void valiUpdate(Model model,HttpSession session,UUID id){
        model.addAttribute("emp", employeeService.detail(id));
        model.addAttribute("role", roleService.getAll());
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username,password);
        session.setAttribute("checkRole",employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        model.addAttribute("view", "/Employee/detail.jsp");
    }

    @PostMapping("/employee/update")
    public String updte(Model model,
                        @RequestParam("id") UUID id,
                        @RequestParam("fullName") String fullName,
                        @RequestParam("username1") String username,
                        @RequestParam("password1") String password,
                        @RequestParam("dateOfBirth") String dateOfBirth,
                        @RequestParam("gender") int gender,
                        @RequestParam("phoneNumber") String phoneNumber,
                        @RequestParam("email") String email,
                        @RequestParam("address") String address,
                        @RequestParam("status") int status,
                        @RequestParam("idRole")String idRole,
                        HttpSession session
    ) {
        if (session.getAttribute("Name") != null){
            if (session.getAttribute("checkRole") == null){
                if (fullName.isBlank()){
                    model.addAttribute("errName","Invalid Receiver FullName");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (username.isBlank()){
                    model.addAttribute("errUser","Invalid Receiver UserName");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (password.isBlank()){
                    model.addAttribute("errPass","Invalid Receiver Password");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (dateOfBirth.isBlank()){
                    model.addAttribute("errDate","Invalid Receiver Date Of Birth");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (phoneNumber.isBlank()){
                    model.addAttribute("errPhone","Invalid Receiver Phone Number");
                    valiUpdate(model,session,id);
                    return "index";
                }
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
                else {
                    List<Employee>  checkUser = employeeService.checkUserNameUpdate(employeeService.detail(id).getUsername(),username);
                    if (!(checkUser.isEmpty())){
                        model.addAttribute("errUserTrung","Duplicate Username !!! Please enter another username");
                        valiUpdate(model,session,id);
                        return "index";            }

                    String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";


                    Date currentDate = new Date(System.currentTimeMillis());
                    Date birth = Date.valueOf(dateOfBirth);
                    if (birth.after(currentDate)){
                        model.addAttribute("errDateAfter","Date of birth must be less than current date !!!");
                        valiUpdate(model,session,id);
                        return "index";            }
                    if (!phoneNumber.matches(Regex)){
                        model.addAttribute("errPhoneErrr","Phone number syntax is incorrect !!!");
                        valiUpdate(model,session,id);
                        return "index";
                    }
                    Employee employee = new Employee();


                    Role rl = roleService.detail(UUID.fromString(idRole));
                    employee.setId(id);
                    employee.setFullName(fullName);
                    employee.setDateOfBirth(dateOfBirth);
                    employee.setAddress(address);
                    employee.setPhoneNumber(phoneNumber);
                    employee.setEmail(email);
                    employee.setDatecreated(employeeService.detail(id).getDatecreated());
                    employee.setGender(gender);
                    employee.setStatus(status);
                    employee.setUsername(username);
                    employee.setPassword(password);
                    employee.setRole(rl);
                    employeeService.add(employee);
                    return "redirect:/employee/index";
                }
            }
            else {
                model.addAttribute("erCheckRole","Employees cannot use this function");
                return "login";
            }

        }else{
            return "login";
        }


    }
     public void vali(Model model,HttpSession session,String fullName,String password1,String username1,String dateOfBirth,String email,String address,String phoneNumber){
         model.addAttribute("role", roleService.getAll());
         String username = (String) session.getAttribute("username");
         String password = (String) session.getAttribute("password");
         Employee checkLogin = employeeService.login(username,password);
         session.setAttribute("checkRole",employeeService.checkRole(username));
         session.setAttribute("Name", checkLogin);
         model.addAttribute("empLogin",checkLogin);
         model.addAttribute("fullnameAdd",fullName);
         model.addAttribute("usernameAdd",username1);
         model.addAttribute("passwordAdd",password1);
         model.addAttribute("dateOfBirthAdd",dateOfBirth);
         model.addAttribute("emailAdd",email);
         model.addAttribute("addressAdd",address);
         model.addAttribute("phoneNumberAdd",phoneNumber);

    }
    @PostMapping("/employee/add")
    public String add(
            @RequestParam("fullName") String fullName,
            @RequestParam("username1") String username1,
            @RequestParam("password1") String password1,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("gender") int gender,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("idRole")String idRole,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {


        if (fullName.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errName","Invalid Receiver FullName");
            model.addAttribute("view", "/Employee/add.jsp");
            return "index";

        }
        if (dateOfBirth.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errDate","Invalid Receiver Date Of Birth");
            model.addAttribute("view", "/Employee/add.jsp");
            return "index";        }
        String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

        if (phoneNumber.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errPhone","Invalid Receiver Phone Number");
            model.addAttribute("view", "/Employee/add.jsp");
            return "index";        }
        if (email.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errEmail","Invalid Receiver Email");
            model.addAttribute("view", "/Employee/add.jsp");
            return "index";        }
        if (address.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errAdd","Invalid Receiver Address");
            model.addAttribute("view", "/Employee/add.jsp");
            return "index";        }
        if (username1.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errUser","Invalid Receiver UserName");
            model.addAttribute("view", "/Employee/add.jsp");
            return "index";        }
        if (password1.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errPass","Invalid Receiver Password");
            model.addAttribute("view", "/Employee/add.jsp");
            return "index";        }
        else {
            List<Employee>  checkUser = employeeService.getByUserName(username1);
            if (!(checkUser.isEmpty())){
                vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
                model.addAttribute("errUserTrung","Duplicate Username !!! Please enter another username");
                model.addAttribute("view", "/Employee/add.jsp");
                return "index";            }


            Employee employee = new Employee();
            Date currentDate = new Date(System.currentTimeMillis());
            Date birth = Date.valueOf(dateOfBirth);
            if (birth.after(currentDate)){
                vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
                model.addAttribute("errDateAfter","Date of birth must be less than current date !!!");
                model.addAttribute("view", "/Employee/add.jsp");
                return "index";            }
            if (!phoneNumber.matches(Regex)){
                vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
                model.addAttribute("errPhoneErrr","Phone number syntax is incorrect !!!");
                model.addAttribute("view", "/Employee/add.jsp");
                return "index";
            }


            employee.setFullName(fullName);
            employee.setDateOfBirth(String.valueOf(dateOfBirth) );
            employee.setAddress(address);
            employee.setPhoneNumber(phoneNumber);
            employee.setDatecreated((currentDate));
            employee.setEmail(email);
            employee.setGender(gender);
            employee.setStatus(1);
            employee.setUsername(username1);
            employee.setPassword(password1);
            employee.setRole(roleService.detail(UUID.fromString(idRole)));
            employeeService.add(employee);
            return "redirect:/employee/index";
        }
    }


    @GetMapping("/employee/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id,HttpSession session) {
        model.addAttribute("emp", employeeService.detail(id));
        model.addAttribute("role", roleService.getAll());
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username,password);
        session.setAttribute("checkRole",employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        model.addAttribute("view", "/Employee/detail.jsp");
        return "index";


    }

    @GetMapping("/employee/viewAdd")
    public String viewAdd(Model model,HttpSession session) {
        model.addAttribute("role", roleService.getAll());
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Employee checkLogin = employeeService.login(username,password);
        session.setAttribute("checkRole",employeeService.checkRole(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        model.addAttribute("view", "/Employee/add.jsp");
        return "index";


    }

    @GetMapping("/employee/role")
    public String role(Model model) {
        model.addAttribute("role",roleService.getAll());
        return "/Employee/role";

    }

    @GetMapping("/employee/timKiem")
    public String timKiem(Model model,
                          @RequestParam("name1")String name,
                          @RequestParam("phone1")String phone,HttpSession session,
                          @RequestParam(value = "pageNo",defaultValue = "0")Integer page) {
        if (name.isBlank()&&phone.isBlank()) {
            model.addAttribute("empList", employeeService.listDesc(page));
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            Employee checkLogin = employeeService.login(username, password);
            session.setAttribute("checkRole", employeeService.checkRole(username));

            session.setAttribute("Name", checkLogin);
            model.addAttribute("empLogin", checkLogin);
            model.addAttribute("view", "/Employee/index.jsp");
            return "index";

        }

        else {
            if (name.isBlank()||phone.isBlank()){
                model.addAttribute("tim",employeeService.timKiem2(name,phone));
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("password");
                Employee checkLogin = employeeService.login(username,password);
                session.setAttribute("checkRole",employeeService.checkRole(username));

                session.setAttribute("Name", checkLogin);
                model.addAttribute("empLogin",checkLogin);

                model.addAttribute("view", "/Employee/index.jsp");
                return "index";
            }
            else {
                model.addAttribute("tim", employeeService.timKiem(name, phone));
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("password");
                Employee checkLogin = employeeService.login(username, password);
                session.setAttribute("checkRole", employeeService.checkRole(username));

                session.setAttribute("Name", checkLogin);
                model.addAttribute("empLogin", checkLogin);

                model.addAttribute("view", "/Employee/index.jsp");
                return "index";
            }

        }
    }
}
