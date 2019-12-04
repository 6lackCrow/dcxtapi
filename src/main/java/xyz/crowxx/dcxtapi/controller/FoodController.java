package xyz.crowxx.dcxtapi.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import xyz.crowxx.dcxtapi.model.Category;
import xyz.crowxx.dcxtapi.model.Food;
import xyz.crowxx.dcxtapi.service.CategoryService;
import xyz.crowxx.dcxtapi.service.FoodService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodController {
    @Resource
    FoodService foodService;
    @Resource
    CategoryService categoryService;
    @PostMapping
    Food addFood(Food food){
        return foodService.addFood(food);
    }

    @GetMapping("/food/list")
    public List<FoodVO> findAllFood(){
        List<Food> foodList = foodService.findAllFood();
        List<String> categoryNameList = new ArrayList<>();
        for (Food food : foodList) {
            categoryNameList.add(food.getCategoryName());
        }
        List<Category> categoryList = categoryService.findByCategoryNameIn(categoryNameList);
        List<FoodVO> foodVOList = new ArrayList<>();

        for (Category category : categoryList) {
            FoodVO foodVO = new FoodVO();
            foodVO.setCategoryName(category.getCategoryName());
            List<FoodInfoVO> foodInfoVOList = new ArrayList<>();
            for (Food food : foodList) {
                if(food.getCategoryName().equals(category.getCategoryName())){
                    FoodInfoVO foodInfoVO = new FoodInfoVO();
                    BeanUtils.copyProperties(food,foodInfoVO);
                    foodInfoVOList.add(foodInfoVO);
                }
            }
            foodVO.setProductInfoVOList(foodInfoVOList);
            foodVOList.add(foodVO);
        }
        return foodVOList;
    }



}

@Data
class FoodInfoVO {

    @JsonProperty("name")
    private String foodName;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("image_url")
    private String imageUrl;
}
@Data
class FoodVO {

    @JsonProperty("name")//返回给前端，这样子再把对象序列化的时候返回给前端它就是一个name了。
    private String categoryName;

    @JsonProperty("foods")
    private List<FoodInfoVO> productInfoVOList;
}