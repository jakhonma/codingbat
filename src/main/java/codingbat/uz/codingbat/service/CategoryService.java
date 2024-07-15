package codingbat.uz.codingbat.service;

import codingbat.uz.codingbat.entity.Category;
import codingbat.uz.codingbat.entity.Language;
import codingbat.uz.codingbat.payload.ApiResponse;
import codingbat.uz.codingbat.payload.CategoryDto;
import codingbat.uz.codingbat.repository.CategoryRepository;
import codingbat.uz.codingbat.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    /**
     * CATEGORYLAR RUYXATINI QAYTARADI
     * @return List<Category>
     */
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    /**
     * IDGA TEGISHLI CATEGORYNI QAYTARADI
     * @param id
     * @return Category
     */
    public Category getCategory(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    /**
     * CATEGORY QO'SHISH
     * @param categoryDto
     * @return ApiResponse
     */
    public ApiResponse addCategory(CategoryDto categoryDto){
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday Til mavjud emas", false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setText(categoryDto.getText());
        category.setLanguage(optionalLanguage.get());
        return new ApiResponse("Category movaffaqyatli qo'shildi", true);
    }

    /**
     * CATEGORYNI TAHRIRLASH
     * @param categoryDto
     * @param categoryDto, id
     * @return ApiResponse
     */
    public ApiResponse editCategory(CategoryDto categoryDto, Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Bunday Category mavjud emas", false);
        }
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday Til mavjud emas", false);
        }

        Category category = optionalCategory.get();

        category.setName(categoryDto.getName());
        category.setText(categoryDto.getText());
        category.setLanguage(optionalLanguage.get());
        return new ApiResponse("Category movaffaqyatli o'zgartirildi", true);
    }

    /**
     * CATEGORYNI O'CHIRISH
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteCategory(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Bunday Category mavjud emas", false);
        }
        categoryRepository.delete(optionalCategory.get());
        return new ApiResponse("Category movaffaqyatli o'chirildi", true);
    }
}
