package fun.ychen.share.app.controller;

import fun.ychen.share.app.common.result.Result;
import fun.ychen.share.app.model.vo.TagVO;
import fun.ychen.share.app.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "标签接口")
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping("/list")
    @Operation(summary = "获取标签列表")
    public Result<List<TagVO>> queryByType(){
        return Result.ok(tagService.getTagList());
    }
}
