package codingbat.uz.codingbat.service;

import codingbat.uz.codingbat.entity.Category;
import codingbat.uz.codingbat.entity.Matter;
import codingbat.uz.codingbat.payload.ApiResponse;
import codingbat.uz.codingbat.payload.MatterDto;
import codingbat.uz.codingbat.repository.CategoryRepository;
import codingbat.uz.codingbat.repository.MatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatterService {

    @Autowired
    MatterRepository matterRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Matter> getAllMatter(){
        return matterRepository.findAll();
    }

    public Matter getMatter(Long id){
        Optional<Matter> optionalMatter = matterRepository.findById(id);
        return optionalMatter.orElse(null);
    }

    public ApiResponse addMatter(MatterDto matterDto){
        Optional<Category> optionalCategory = categoryRepository.findById(matterDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Bunday category mavjud emas", false);
        }
        Matter matter = new Matter();
        matter.setQuestion(matterDto.getQuestion());
        matter.setCode(matterDto.getCode());
        matter.setHint(matterDto.getHint());
        matter.setTrue(false);
        matter.setCategory(optionalCategory.get());
        matterRepository.save(matter);
        return new ApiResponse("Matter created", true);
    }

    public ApiResponse editMatter(MatterDto matterDto, Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(matterDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Bunday category mavjud emas", false);
        }
        Optional<Matter> optionalMatter = matterRepository.findById(id);
        if (!optionalMatter.isPresent()){
            return new ApiResponse("Bunday masala mavjud emas", false);
        }
        Matter matter = optionalMatter.get();
        matter.setQuestion(matterDto.getQuestion());
        matter.setCode(matterDto.getCode());
        matter.setHint(matterDto.getHint());
        matter.setTrue(false);
        matter.setCategory(optionalCategory.get());
        matterRepository.save(matter);
        return new ApiResponse("Matter updated", true);
    }

    public ApiResponse deleteMatter(Long id){
        Optional<Matter> optionalMatter = matterRepository.findById(id);
        if (!optionalMatter.isPresent()){
            return new ApiResponse("Bunday masala mavjud emas", false);
        }
        matterRepository.delete(optionalMatter.get());
        return new ApiResponse("Matter deleted", true);
    }
}
