package cn.coderap.server.service;

import cn.coderap.server.dto.${Domain}Dto;
import cn.coderap.server.dto.PageDto;
import cn.coderap.server.mapper.${Domain}Mapper;
import cn.coderap.server.pojo.${Domain};
import cn.coderap.server.pojo.${Domain}Example;
import cn.coderap.server.util.CopyUtil;
import cn.coderap.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
<#list typeSet as type>
<#if type=='Date'>
import java.util.Date;
</#if>
</#list>

@Service
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        <#list fieldList as field>
            <#if field.nameHump == 'sort'>
        ${domain}Example.setOrderByClause("sort asc");
            </#if>
        </#list>
        ${Domain}Example.Criteria criteria = ${domain}Example.createCriteria();
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);

        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());
        List<${Domain}Dto> ${domain}DtoList = CopyUtil.copyList(${domain}List, ${Domain}Dto.class);
        pageDto.setList(${domain}DtoList);
    }

    /**
     * 保存，id没有值时新增，有值时更新
     * @param ${domain}Dto
     */
    public void save(${Domain}Dto ${domain}Dto) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        if (StringUtils.isEmpty(${domain}.getId())) {
            insert(${domain});
        } else {
            update(${domain});
        }
    }

    /**
     * 新增
     * @param ${domain}
     */
    private void insert(${Domain} ${domain}) {
        Date now = new Date();
        <#list fieldList as field>
            <#if field.nameHump == 'createdAt'>
        ${domain}.setCreatedAt(now);
            </#if>
            <#if field.nameHump == 'updatedAt'>
        ${domain}.setUpdatedAt(now);
            </#if>
        </#list>
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }

    /**
     * 更新
     * @param ${domain}
     */
    private void update(${Domain} ${domain}) {
        <#list fieldList as field>
            <#if field.nameHump == 'updatedAt'>
        ${domain}.setUpdatedAt(new Date());
            </#if>
        </#list>
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
