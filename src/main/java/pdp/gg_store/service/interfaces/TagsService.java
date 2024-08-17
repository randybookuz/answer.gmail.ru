package pdp.gg_store.service.interfaces;


import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Tags;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagsService {
    ApiResult<List<Tags>>getAllTags();
    ApiResult<List<UUID>>getQuestionsByTagName(String name);
    ApiResult<Tags> createTag(Tags tags) ;
    ApiResult<Void> deleteTagWithQuestionsID(UUID id);
}
