package com.mani.SpringHibDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String branch;
    private String passwd;

}
