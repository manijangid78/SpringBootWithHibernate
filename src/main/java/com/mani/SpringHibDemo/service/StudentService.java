package com.mani.SpringHibDemo.service;

import com.mani.SpringHibDemo.entity.Product;
import com.mani.SpringHibDemo.entityImpl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {

    @Autowired
    private ProductImpl productImpl;

    public String saveStudentData(Product product){
        if(productImpl.save(product)){
            return "Student Data Added !!!";
        }
        return "Error Found";
    }

    public Product getStudentData(int id){
        Product product = productImpl.getProduct(id);
        return product;
    }

    public boolean loginCheck(Product product){
        try {
            Product student = productImpl.getProduct(product.getId());
            System.out.println(student);
            System.out.println(student.getPasswd());
            System.out.println(product.getPasswd());
            if((student.getPasswd()).equals(product.getPasswd())) {
                System.out.println("true");
                return true;
            }
        }catch (Exception e){}
        System.out.println("false impl");
        return false;
    }

    public String delete(int id){
        if(productImpl.delete(id)){
            return "Student "+id+" deleted";
        }
        return "Id not Found";
    }

    public int getId(){
        int id = productImpl.getId();
        return id;
    }
}
