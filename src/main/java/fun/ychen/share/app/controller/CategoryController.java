package fun.ychen.share.app.controller;

import fun.ychen.share.app.common.result.Result;
import fun.ychen.share.app.model.vo.CategoryVO;
import fun.ychen.share.app.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "分类接口")
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    @Operation(summary = "获取分类列表")
    public Result<List<CategoryVO>> queryByType(){
        return Result.ok(categoryService.getCategoryList());
    }
}
