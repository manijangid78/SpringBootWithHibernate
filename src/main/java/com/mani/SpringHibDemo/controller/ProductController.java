package com.mani.SpringHibDemo.controller;

import com.mani.SpringHibDemo.entity.Product;
import com.mani.SpringHibDemo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private StudentService studentService;
    
    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("student_operation")
    public String student_operation(){
        return "student_operation";
    }

    @GetMapping("student_add")
    public String student_add(Model model){
        int std_id = studentService.getId();
        Product product = new Product(std_id, "", "", "");
        model.addAttribute("new_student", product);
        return "student_add";
    }

    @GetMapping("student_login")
    public String student_login(){
        return "student_login";
    }

    @GetMapping("student_delete")
    public String student_delete(){
        return "student_delete";
    }


    @GetMapping("student_get_data")
    public String student_home(){
        return "student_get_data";
    }

    @GetMapping("student_edit")
    public String student_edit(){
        return "student_edit";
    }

    @PostMapping("student_login")
    public String student_login(@RequestParam("id")int id,@RequestParam("passwd")String student_passwd, Model model) {

        Product product = new Product();
        product.setId(id);
        product.setPasswd(student_passwd);
        System.out.println(id);
        System.out.println(student_passwd);

        boolean bool = studentService.loginCheck(product);
        System.out.println(bool+"contoller");
        if (bool) {
            return "student_operation";
        }
        model.addAttribute("login_message","Login Filed");
        return "student_login";
    }

    @PostMapping("student_done")
    public String getData(@RequestParam(value = "id",required =false,defaultValue ="-1") int id,@RequestParam("name")String name,
                          @RequestParam("branch")String branch,@RequestParam("passwd")String passwd, Model model){
        Product student_data = studentService.getStudentData(id);
        Product product = new Product(id, name, branch, passwd);
        String message = studentService.saveStudentData(product);
        model.addAttribute("message", message);
        System.out.println(message);
        if(student_data==null) {
            int student_id = studentService.getId();
            Product student = new Product(id, "", "", "");
            model.addAttribute("new_student", student);
            return "student_add";
        }
        else{
            return "student_edit";
        }
    }

    @PostMapping("student_data")
    public String student_data(@RequestParam("id")int id, Model model) {
        Product product = studentService.getStudentData(id);
        if(product==null){
            model.addAttribute("get_data_message","Id Not Found");
        }
        model.addAttribute("student_data", product);
        return "student_get_data";
    }

    @PostMapping("student_edit")
    public String student_edit(@RequestParam("id")int id,Model model){
        Product product = studentService.getStudentData(id);
        if(product==null){
            model.addAttribute("edit_message","Id Not Found");
        }
        model.addAttribute("student", product);
        return "student_edit";
    }

    @PostMapping("student_delete")
    public String student_delete(@RequestParam("id")int id,Model model){
        String delete_message = studentService.delete(id);
        model.addAttribute("delete_message", delete_message);
        return "student_delete";
    }
}