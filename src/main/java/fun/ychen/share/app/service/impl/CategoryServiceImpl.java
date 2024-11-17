package fun.ychen.share.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ychen.share.app.convert.CategoryConvert;
import fun.ychen.share.app.mapper.CategoryMapper;
import fun.ychen.share.app.model.entity.Category;
import fun.ychen.share.app.model.vo.CategoryVO;
import fun.ychen.share.app.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<CategoryVO> getCategoryList() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        return CategoryConvert.INSTANCE.convert(list(wrapper));
    }

    @Override
    public List<String> queryCategoryNameList(List<Integer> pkIdList) {
        return baseMapper.selectBatchIds(pkIdList)
                .stream()
                .map(Category::getTitle)
                .toList();
    }
}
