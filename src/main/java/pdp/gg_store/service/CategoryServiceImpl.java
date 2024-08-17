package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Answer;
import pdp.gg_store.model.Block;
import pdp.gg_store.model.Category;
import pdp.gg_store.model.DAO.CategoryRepository;
import pdp.gg_store.service.interfaces.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private  final CategoryRepository categoryRepository;

    @Override
    public ApiResult<List<Category> >getAllCategories() {
        List<Category> all = categoryRepository.findAll();
        if(all.isEmpty()){
            ApiResult.error("not found");
        }
        return ApiResult.success(all);
    }

    @Override
    public ApiResult<Category> getCategoryById(UUID id) {
        Optional<Category> category=categoryRepository.findById(id);
        if(category.isPresent()){
            Category category1=category.get();
            return ApiResult.success(category1);
        }else {
            return ApiResult.error("not found");
        }
    }

    @Override
    public ApiResult<Category> createCategory(Category category)  {
      try {
          if (categoryRepository.existsById(category.getId())) {
              throw new RuntimeException();
          }
          categoryRepository.save(category);
          return  ApiResult.success(category);
      }catch ( RuntimeException e){
          return  ApiResult.error("id busy for create");
      }
    }

    @Override
    public ApiResult<Category> updateCategory(UUID id,Category category){
       try {
           Optional<Category> existingCategory = categoryRepository.findById(id);

           if (existingCategory.isPresent()) {
               Category updatedCategory = existingCategory.get();
               updatedCategory.setName(category.getName());
               updatedCategory.setDescription(category.getDescription());
               updatedCategory.setParentCategory(category.getParentCategory());
                categoryRepository.save(updatedCategory);
                return  ApiResult.success(updatedCategory);

           } else {
               throw new RuntimeException();
           }
       }catch (RuntimeException e ){
           return  ApiResult.error("id is empty,use post(create)");
       }



    }

    @Override
    public ApiResult<Void>deleteCategory(UUID id) {
        try {
            if (categoryRepository.existsById(id)) {
                categoryRepository.deleteById(id);
                return ApiResult.success(null);
            }else {
                throw new RuntimeException();
            }
        }catch (RuntimeException e){
            return  ApiResult.error("id not found for delete");
        }
    }
}
