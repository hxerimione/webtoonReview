package com.review.webtoon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int page;
    private int totalPages;
    private int startPage;
    private int endPage;
    public Pagination(int totalPages, int page) {
        setPage(page);
        setTotalPages(totalPages);
        if (totalPages==0){
            setStartPage(1);
            setEndPage(1);
        }
        else if(totalPages<7){
            setStartPage(1);
            setEndPage(totalPages);
        }
        else {
            if (page < 4) {
                setStartPage(1);
                setEndPage(7);
            } else if (page > totalPages - 4) {
                setStartPage(page - 3);
                setEndPage(totalPages);
            } else {
                setStartPage(page - 3);
                setEndPage(page + 3);
            }
        }
    }
}
