package xyz.crowxx.dcxtapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crowxx.dcxtapi.model.FoodModel;

import java.util.List;

public interface FoodModelRepository extends JpaRepository<FoodModel,Long> {
    List<FoodModel> findFoodModelsByOrderid(Long id);
}
