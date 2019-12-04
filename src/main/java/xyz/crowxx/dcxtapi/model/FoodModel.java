package xyz.crowxx.dcxtapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class FoodModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodsId;
    private String name;
    private Integer price;
    private Integer number;
    private Integer sum;
    private Long orderid;
}