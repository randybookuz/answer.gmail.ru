package pdp.gg_store.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.CategoryDTO;
import pdp.gg_store.api.utils.AppConstants;
import pdp.gg_store.api.mapper.CategoryMapper;
import pdp.gg_store.model.Category;
import pdp.gg_store.service.interfaces.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/category")
@Tag(name="Category")
public class CategoryController {


    private final CategoryService categoryService;
    private final CategoryMapper mapper;

    @Operation(summary="getAllCategories(")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        ApiResult<List<Category>> allCategories = categoryService.getAllCategories();
        if(allCategories.isSuccess()){
            List<CategoryDTO> categoryDTOS = mapper.toDTOs(allCategories.getData());
            return ResponseEntity.ok(categoryDTOS);
        }else{ return  ResponseEntity.notFound().build();
        }

    }

    @Operation(summary="getCategoryById")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable UUID id) {

        ApiResult<Category> categoryById = categoryService.getCategoryById(id);
        if (categoryById.isSuccess()) {
            return ResponseEntity.ok(mapper.toDTO(categoryById.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="createCategory")
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto) {
        ApiResult<Category> category = categoryService.createCategory(mapper.toEntity(categoryDto));
        if (category.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(category.getData()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary="updateCategory(")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID id,
                                                      @Valid @RequestBody CategoryDTO updatedCategoryDTO) {
        ApiResult<Category> categoryApiResult = categoryService.updateCategory(id, mapper.toEntity(updatedCategoryDTO));
        if (categoryApiResult.isSuccess()) {
            return ResponseEntity.ok(mapper.toDTO(categoryApiResult.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="deleteCategory")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id) {

        ApiResult<Void> voidApiResult = categoryService.deleteCategory(id);
        if (voidApiResult.isSuccess()) {
            return ResponseEntity.ok("caregory deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}





