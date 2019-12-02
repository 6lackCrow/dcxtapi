package xyz.crowxx.dcxtapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crowxx.dcxtapi.model.Food;

public interface FoodRepository extends JpaRepository<Food,Long> {

}
