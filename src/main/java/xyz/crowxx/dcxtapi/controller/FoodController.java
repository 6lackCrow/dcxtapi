package xyz.crowxx.dcxtapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.crowxx.dcxtapi.model.Food;
import xyz.crowxx.dcxtapi.service.FoodService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodController {
    @Resource
    FoodService foodService;
    @PostMapping
    Food addFood(@RequestBody Food food){
        return foodService.addFood(food);
    }
    List<Food> findAllFood(){
        return foodService.findAllFood();
    }
}
