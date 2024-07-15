package codingbat.uz.codingbat.controller;

import codingbat.uz.codingbat.entity.Language;
import codingbat.uz.codingbat.payload.ApiResponse;
import codingbat.uz.codingbat.service.LanguageService;
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
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    /**
     * TILLARNI RUYXATINI CHIQARADI
     * @return List<Language>
     */
    @GetMapping
    public ResponseEntity<List<Language>> getAll(){
        List<Language> allLanguage = languageService.getAllLanguage();
        return ResponseEntity.status(200).body(allLanguage);
    }

    /**
     * ID ORQALI TILNI QAYTARADI
     * @param id
     * @return Language
     */
    @GetMapping("/{id}")
    public ResponseEntity<Language> get(@PathVariable Long id){
        Language language = languageService.getLanguage(id);
        return ResponseEntity.status(200).body(language);
    }

    /**
     * TILNI QO'SHISH
     * @param language
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody Language language){
        ApiResponse apiResponse = languageService.addLanguage(language);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    /**
     * TILNI O'ZGARTIRISH
     * @param language, id
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@RequestBody Language language, @PathVariable Long id){
        ApiResponse apiResponse = languageService.editLanguage(language, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    /**
     * TILNI O'CHIRISH
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
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
