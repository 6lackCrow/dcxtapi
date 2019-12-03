package xyz.crowxx.dcxtapi.service;

import org.springframework.stereotype.Service;
import xyz.crowxx.dcxtapi.model.Category;
import xyz.crowxx.dcxtapi.repository.CategoryRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    @Resource
    CategoryRepository categoryRepository;
    public List<Category> findByCategoryNameIn(List<String> categoryNameList){
        return categoryRepository.findByCategoryNameIn(categoryNameList);
    }
}
