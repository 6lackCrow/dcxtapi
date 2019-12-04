package xyz.crowxx.dcxtapi.service;

import org.springframework.stereotype.Service;
import xyz.crowxx.dcxtapi.model.FoodModel;
import xyz.crowxx.dcxtapi.repository.FoodModelRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodModelService {
    @Resource
    FoodModelRepository foodModelRepository;

    public List<FoodModel> findFoodsByOrderId(Long orderid) {
        return foodModelRepository.findFoodModelsByOrderid(orderid);
    }
}
