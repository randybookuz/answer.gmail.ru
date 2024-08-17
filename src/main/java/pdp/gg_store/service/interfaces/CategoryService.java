package pdp.gg_store.service.interfaces;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    ApiResult<List<Category>>getAllCategories();
    ApiResult<Category> getCategoryById(UUID id);
    ApiResult<Category> createCategory(Category category);
    ApiResult<Category> updateCategory(UUID id,Category category);
    ApiResult<Void> deleteCategory(UUID id) ;

}
