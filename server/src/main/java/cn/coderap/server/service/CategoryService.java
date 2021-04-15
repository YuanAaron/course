package cn.coderap.server.service;

import cn.coderap.server.dto.CategoryDto;
import cn.coderap.server.dto.PageDto;
import cn.coderap.server.mapper.CategoryMapper;
import cn.coderap.server.pojo.Category;
import cn.coderap.server.pojo.CategoryExample;
import cn.coderap.server.util.CopyUtil;
import cn.coderap.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 列表查询：分类的总数不多，不需要分页，一次查询出所有数据，由前端来处理显示
     */
    public List<CategoryDto> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        return categoryDtoList;
    }

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        pageDto.setList(categoryDtoList);
    }

    /**
     * 保存，id没有值时新增，有值时更新
     * @param categoryDto
     */
    public void save(CategoryDto categoryDto) {
        Category category = CopyUtil.copy(categoryDto, Category.class);
        if (StringUtils.isEmpty(category.getId())) {
            insert(category);
        } else {
            update(category);
        }
    }

    /**
     * 新增
     * @param category
     */
    private void insert(Category category) {
        category.setId(UuidUtil.getShortUuid());
        categoryMapper.insert(category);
    }

    /**
     * 更新
     * @param category
     */
    private void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 删除
     * @param id
     */
    @Transactional //重要
    public void delete(String id) {
        deleteChildren(id);
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除子分类
     * @param id
     */
    private void deleteChildren(String id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if ("00000000".equals(category.getParent())) {
            //如果是一级分类，需要删除其下的二级分类
            CategoryExample categoryExample = new CategoryExample();
            CategoryExample.Criteria criteria = categoryExample.createCriteria();
            criteria.andParentEqualTo(category.getId());
            categoryMapper.deleteByExample(categoryExample);
        }


    }
}
