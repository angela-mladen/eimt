package emt.backend.web;

import emt.backend.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    @GetMapping("/list")
    public List<Category> listCategories(){
        return Arrays.asList(Category.values());

    }
}
