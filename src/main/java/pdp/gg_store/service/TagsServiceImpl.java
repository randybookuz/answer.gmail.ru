package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Block;
import pdp.gg_store.model.DAO.TagRepository;

import pdp.gg_store.model.Tags;

import pdp.gg_store.service.interfaces.TagsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class TagsServiceImpl implements TagsService {
    private  final TagRepository tagRepository;
    @Override
    public ApiResult<List<Tags>> getAllTags() {
        List<Tags> all = tagRepository.findAll();
        if(all.isEmpty()){
            ApiResult.error("not found");
        }
        return ApiResult.success(all);
    }




    @Override
    public ApiResult<List<UUID>> getQuestionsByTagName(String name) {
        List<UUID> questionsByTagName = tagRepository.getQuestionsByTagName(name);
        return  ApiResult.success(questionsByTagName);
    }


    @Override
    public ApiResult<Tags> createTag(Tags tags) {
      try {
          if (tagRepository.existsById(tags.getId())) {
              throw new RuntimeException();
          }
           tagRepository.save(tags);
          return  ApiResult.success(tags);
      }catch (RuntimeException e){
          return  ApiResult.error("id busy for create");
      }
    }

    @Override
    public ApiResult<Void> deleteTagWithQuestionsID(UUID id) {
        tagRepository.deleteTagWithQuestionsID(id);
return ApiResult.success(null);
    }


}



