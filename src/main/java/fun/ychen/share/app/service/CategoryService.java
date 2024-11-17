package fun.ychen.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ychen.share.app.model.entity.Category;
import fun.ychen.share.app.model.vo.CategoryVO;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<CategoryVO> getCategoryList();

    List<String> queryCategoryNameList(List<Integer> pkIdList);
}
