package cn.tedu.csmall.commons.restful;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JsonPage<T> implements Serializable {
    @ApiModelProperty(value = "总页数",name="totalPages")
    private Integer totalPages;
    @ApiModelProperty(value = "总条数",name="totalCount")
    private Long totalCount;
    @ApiModelProperty(value = "当前页码",name="page")
    private Integer page;
    @ApiModelProperty(value = "每页条数",name="pageSize")
    private Integer pageSize;

    @ApiModelProperty(value = "分页数据",name = "list")
    private List<T> list;

    public static<T> JsonPage<T> restPage(PageInfo<T> pageInfo){
        JsonPage<T> result=new JsonPage<>();
        result.setTotalPages(pageInfo.getPages());
        result.setTotalCount(pageInfo.getTotal());
        result.setPage(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setList(pageInfo.getList());
        return result;
    }

}
