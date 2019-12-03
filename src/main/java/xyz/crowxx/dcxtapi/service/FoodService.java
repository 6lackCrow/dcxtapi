package xyz.crowxx.dcxtapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.crowxx.dcxtapi.model.Food;
import xyz.crowxx.dcxtapi.repository.FoodRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodService {
    @Resource
    FoodRepository foodRepository;
    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> findAllFood() {
        return foodRepository.findAll();
    }
    public Page<Food> findPageFood(Pageable pageable){
        return foodRepository.findAll(pageable);
    }
}
