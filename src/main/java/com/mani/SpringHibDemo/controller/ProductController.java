package com.mani.SpringHibDemo.controller;

import com.mani.SpringHibDemo.entity.Product;
import com.mani.SpringHibDemo.service.StudentService;
import com.mani.SpringHibDemo.util.MappingAttributes;
import com.mani.SpringHibDemo.util.ViewName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@SessionAttributes("id")
public class ProductController {

    @Autowired
    private StudentService studentService;
    
    @GetMapping(MappingAttributes.HOME)
    public String home(HttpSession session){
        return ViewName.HOME;
    }

    @GetMapping(MappingAttributes.STUDENT_OPERATION)
    public String student_operation(HttpSession session){
        if(session.getAttribute("id")==null) {
            return ViewName.STUDENT_LOGIN;
        }
        return ViewName.STUDENT_OPERATION;
    }

    @GetMapping(MappingAttributes.STUDENT_ADD)
    public String student_add(HttpSession session,Model model){
        if(session.getAttribute("id")==null) {
            return ViewName.STUDENT_LOGIN;
        }
        int std_id = studentService.getId();
        Product product = new Product(std_id, "", "", "");
        model.addAttribute("new_student", product);
        return ViewName.STUDENT_ADD;
    }

    @GetMapping(MappingAttributes.STUDENT_LOGIN)
    public String student_login(){
        return ViewName.STUDENT_LOGIN;
    }

    @GetMapping(MappingAttributes.STUDENT_DELETE)
    public String student_delete(HttpSession session){
        if(session.getAttribute("id")==null) {
            return ViewName.STUDENT_LOGIN;
        }
        return ViewName.STUDENT_DELETE;
    }

    @GetMapping(MappingAttributes.STUDENT_LOGOUT)
    public String student_logout(HttpSession session){
        session.removeAttribute("id");
        return ViewName.STUDENT_LOGIN;
    }



    @GetMapping(MappingAttributes.STUDENT_GET_DATA)
    public String student_home(HttpSession session){
        if(session.getAttribute("id")==null) {
            return ViewName.STUDENT_LOGIN;
        }
        return ViewName.STUDENT_GET_DATA;
    }

    @GetMapping(MappingAttributes.STUDENT_EDIT)
    public String student_edit(HttpSession session){
        if(session.getAttribute("id")==null) {
            return ViewName.STUDENT_LOGIN;
        }
        return ViewName.STUDENT_EDIT;
    }

    @PostMapping(MappingAttributes.STUDENT_LOGIN)
    public String student_login(@RequestParam(value = "id")int id,@RequestParam(value = "passwd",defaultValue = "")String student_passwd, ModelMap model) {

        Product product = new Product();
        product.setId(id);
        product.setPasswd(student_passwd);
        boolean bool = studentService.loginCheck(product);
        if (bool) {
            model.put("id",id);
            return ViewName.STUDENT_OPERATION;
        }
        model.addAttribute("login_message","Login Filed");
        return ViewName.STUDENT_LOGIN;
    }

    @PostMapping(MappingAttributes.STUDENT_DONE)
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
            return ViewName.STUDENT_ADD;
        }
        else{
            return ViewName.STUDENT_EDIT;
        }
    }

    @PostMapping(MappingAttributes.STUDENT_DATA)
    public String student_data(@RequestParam("id")int id, Model model) {
        Product product = studentService.getStudentData(id);
        if(product==null){
            model.addAttribute("get_data_message","Id Not Found");
        }
        model.addAttribute("student_data", product);
        return ViewName.STUDENT_GET_DATA;
    }

    @PostMapping(MappingAttributes.STUDENT_EDIT)
    public String student_edit(@RequestParam("id")int id,Model model){
        Product product = studentService.getStudentData(id);
        if(product==null){
            model.addAttribute("edit_message","Id Not Found");
        }
        model.addAttribute("student", product);
        return ViewName.STUDENT_EDIT;
    }

    @PostMapping(MappingAttributes.STUDENT_DELETE)
    public String student_delete(@RequestParam("id")int id,Model model){
        String delete_message = studentService.delete(id);
        model.addAttribute("delete_message", delete_message);
        return ViewName.STUDENT_DELETE;
    }
}