package com.example.pagination_ex.controller.pagination;

import lombok.Getter;

import java.util.List;

@Getter
public class  Pagination<T> {
    private List<T> content;

    private int currentPage;
    private int nextPage;
    private int prevPage;
    private int pageSize;
    private int totalPage;

    public Pagination(int currentPage, int pageSize, int totalPage){
        this.currentPage=currentPage;
        this.nextPage=this.currentPage+1>totalPage-1?this.currentPage+1:totalPage-1;
        this.prevPage=this.currentPage-1<0?0:this.currentPage-1;
        this.pageSize=pageSize;
        this.totalPage=totalPage;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
