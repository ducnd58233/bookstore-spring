package com.example.demo.common;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class Paging {
    private Integer pageNum;
    private Integer pageSize; // limit
    private String sortBy;
    private String orderBy;

    public Paging(Integer pageNum, Integer pageSize, String sortBy, String orderBy) {
        this.pageNum = setPageNum(pageNum);
        this.pageSize = setPageSize(pageSize);
        this.sortBy = setSortBy(sortBy);
        this.orderBy = setOrderBy(orderBy);
    }

    private Integer setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum <= 0) pageNum = 1;
        return pageNum;
    }

    private Integer setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize <= 0) pageSize = 50;
        return pageSize;
    }

    private String setSortBy(String sortBy) {
        if (sortBy == null || sortBy.equals("createdAt")) sortBy = "createdAt";
        return sortBy;
    }

    private String setOrderBy(String orderBy) {
        if (orderBy == null || orderBy.equals("DESC")) orderBy = "DESC";
        else orderBy = "ASC";
        return orderBy;
    }

    public PageRequest getPaging() {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);

        if (orderBy.isEmpty() || orderBy.equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }

        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        return pageRequest;
    }
}
