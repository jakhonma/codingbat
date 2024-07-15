package codingbat.uz.codingbat.controller;

import codingbat.uz.codingbat.entity.Matter;
import codingbat.uz.codingbat.payload.ApiResponse;
import codingbat.uz.codingbat.payload.MatterDto;
import codingbat.uz.codingbat.service.MatterService;
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
@RequestMapping("/matter")
public class MatterController {
    @Autowired
    MatterService matterService;

    @GetMapping
    public ResponseEntity<List<Matter>> getAll(){
        List<Matter> allMatter = matterService.getAllMatter();
        return ResponseEntity.status(200).body(allMatter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matter> getOne(@PathVariable Long id){
        Matter matter = matterService.getMatter(id);
        return ResponseEntity.status(200).body(matter);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody MatterDto matterDto){
        ApiResponse apiResponse = matterService.addMatter(matterDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@RequestBody MatterDto matterDto, @PathVariable Long id){
        ApiResponse apiResponse = matterService.editMatter(matterDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        ApiResponse apiResponse = matterService.deleteMatter(id);
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
