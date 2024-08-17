package pdp.gg_store.api.mapper;

import org.mapstruct.*;
import pdp.gg_store.api.payload.CategoryDTO;
import pdp.gg_store.model.Category;

import java.util.List;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    @Mapping(source = "parentCategoryId", target = "parentCategory.id")
    Category toEntity(CategoryDTO categoryDto);


    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    CategoryDTO toDTO(Category category);

    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    List<CategoryDTO> toDTOs(List<Category> categories);


}