package com.example.sd40.controller;

import com.example.sd40.entity.Category;
import com.example.sd40.entity.Employee;
import com.example.sd40.service.EmployeeService;
import com.example.sd40.service.serviceimpl.CategoryServiceimpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceimpl categoryServiceimpl;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/index")
    public String hienThi(Model model, HttpSession session){
        if (session.getAttribute("Name") != null){
            //Nếu đã đăng nhập vào trang index
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            Employee checkLogin = employeeService.login(username,password);
            model.addAttribute("empLogin",checkLogin);
            //////
            model.addAttribute("listCategory",categoryServiceimpl.getAll());
            model.addAttribute("view", "/Category/index.jsp");
            return "index";
        }
        //Nếu chưa đăng nhập thì return về trang logina
        return "login";
    }
    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "status") Integer status
    ){
        categoryServiceimpl.save(new Category(name,status));
        return "redirect:/category/index";
    }
    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") String id
    ){
        categoryServiceimpl.delete(UUID.fromString(id));
        return "redirect:/category/index";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id){

        model.addAttribute("listCategory",categoryServiceimpl.getAll());
        model.addAttribute("loai",categoryServiceimpl.getOne(id));
        model.addAttribute("view", "/Category/index.jsp");
        return "index";

    }
    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") String id,
                         @RequestParam("name") String name,
                         @RequestParam("status") Integer status){
        Category cv = new Category(name,status);
        categoryServiceimpl.update(UUID.fromString(id),cv);
        return "redirect:/category/index";
    }
}
