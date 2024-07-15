package codingbat.uz.codingbat.controller;

import codingbat.uz.codingbat.entity.Category;
import codingbat.uz.codingbat.payload.ApiResponse;
import codingbat.uz.codingbat.payload.CategoryDto;
import codingbat.uz.codingbat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * CATEGORYLAR RUYXATINI QAYTARADI
     * @return List<Category>
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    /**
     * IDGA TEGISHLI CATEGORYNI QAYTARADI
     * @param id
     * @return Category
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Long id){
        Category category = categoryService.getCategory(id);
        return ResponseEntity.status(200).body(category);
    }

    /**
     * CATEGORY QO'SHISH
     * @param categoryDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    /**
     * CATEGORYNI TAHRIRLASH
     * @param categoryDto
     * @param categoryDto, id
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@RequestBody CategoryDto categoryDto,@PathVariable Long id){
        ApiResponse apiResponse = categoryService.editCategory(categoryDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    /**
     * CATEGORYNI O'CHIRISH
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isStatus()?204:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerValidationExaptions(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
