package com.mani.SpringHibDemo.entityImpl;

import com.mani.SpringHibDemo.entity.Product;
import com.mani.SpringHibDemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImpl {

    @Autowired
    public ProductRepository repository;

    public boolean save(Product product){
        try{
            repository.save(product);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Product getProduct(int id){
        return repository.findById(id).orElse(null);
    }

    public boolean delete(int id){
        try {
            Product product = repository.findById(id).orElse(null);
            repository.delete(product);
            if (product != null) {
                return true;
            }
        }catch (Exception e){}
        return false;
    }

    public int getId(){
        int id = new Product().getId();
        return id;
    }
}
