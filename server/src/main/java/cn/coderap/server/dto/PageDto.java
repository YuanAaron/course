package cn.coderap.server.dto;

import java.util.List;

/**
 * Created by yw
 * 2021/3/23
 */
public class PageDto<T> {

    /**
     * 当前页码
     */
    private int page;

    /**
     * 每页条数
     */
    private int size;

    /**
     * 总条数
     */
    private long total;

    /**
     * 查询到的当前页记录
     */
    private List<T> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
