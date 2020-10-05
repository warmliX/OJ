package com.zzkk.bean;

import java.util.ArrayList;
//testsafs
public class PageBean {
    private int currentPage;//当前页
    private int pageCount;//每页数据数
    private int totalCount;//数据总数
    private int totalPage;//总页数
    private ArrayList<String[]> list;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if(currentPage > totalPage || currentPage < 0)
            this.currentPage = totalPage;
        else
            this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public ArrayList getList(){
        return list;
    }

    public void setList(ArrayList<String[]> list){
        this.list = list;
    }
}
