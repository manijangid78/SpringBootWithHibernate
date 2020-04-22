package com.mani.SpringHibDemo.config;

import com.mani.SpringHibDemo.entityImpl.ProductImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class ProductConfig implements WebMvcConfigurer {

    @Autowired
    ProductImpl productImpl;

    // public methods

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    /*@PostConstruct
    public void data(){
        Product product = new Product();
        product.setName("Pinky");
        product.setBranch("CSE");
        productImpl.save(product);

        Product product2 = new Product();
        product2.setName("Kashi");
        product2.setBranch("ME");
        productImpl.save(product2);
    }*/
}
