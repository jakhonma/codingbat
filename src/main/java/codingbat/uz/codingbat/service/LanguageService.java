package codingbat.uz.codingbat.service;

import codingbat.uz.codingbat.entity.Language;
import codingbat.uz.codingbat.payload.ApiResponse;
import codingbat.uz.codingbat.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    /**
     * TILLARNI RUYXATINI CHIQARADI
     * @return List<Language>
     */
    public List<Language> getAllLanguage(){
        return languageRepository.findAll();
    }

    /**
     * ID ORQALI TILNI QAYTARADI
     * @param id
     * @return Language
     */
    public Language getLanguage(Long id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    /**
     * TILNI QO'SHISH
     * @param language
     * @return ApiResponse
     */
    public ApiResponse addLanguage(Language language){
        boolean existsByName = languageRepository.existsByName(language.getName());
        if (existsByName){
            return new ApiResponse("Bunday Til mavjud", false);
        }
        Language language1 = new Language();
        language1.setName(language.getName());
        languageRepository.save(language1);
        return new ApiResponse("Til movaffaqyatli qo'shildi", true);
    }

    /**
     * TILNI O'ZGARTIRISH
     * @param language, id
     * @return ApiResponse
     */
    public ApiResponse editLanguage(Language language, Long id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday til mavjud emas", false);
        }

        Language language1 = optionalLanguage.get();
        language1.setName(language.getName());
        languageRepository.save(language1);
        return new ApiResponse("Til movaffaqyatli o'zgartirildi", true);
    }

    /**
     * TILNI O'CHIRISH
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteLanguage(Long id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday til mavjud emas", false);
        }
        languageRepository.delete(optionalLanguage.get());
        return new ApiResponse("Til movaffaqyatli o'chirildi", true);
    }
}
