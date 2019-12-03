package xyz.crowxx.dcxtapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crowxx.dcxtapi.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByCategoryNameIn(List<String> categoryNameList);
}
