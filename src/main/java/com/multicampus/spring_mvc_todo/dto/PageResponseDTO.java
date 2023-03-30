package com.multicampus.spring_mvc_todo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO<E> {

    private int page; // 현재페이지
    private int size; // 한 페이지당 갯수
    private int total; // 전체 글 갯수

    // 페이지그룹의 시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;
    //그룹기준 이전 페이지의 존재 여부
    private boolean prev;
    //그룹기준 다음 페이지의 존재 여부
    private boolean next;
    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        // 10, 9 대신 size
        this.end = (int)(Math.ceil(this.page / 10.0)) * 10; // ceil(현재페이지 17/ 10) * 10 = 20  나열된 페이지 중 끝 페이지
        this.start = this.end - 9; // 20 -9 = 11 나열된 페이지중 첫 페이지
        int last = (int)(Math.ceil((total/(double)size))); // ceil(전체페이지 갯수 435 / 한페이지당 글 갯수 10 ) = 44 마지막 페이지 수
        this.end = end > last ? last: end; // 20 > 44 false end = end 20 ; 11~20페에지만 표시, 44페이지까지는 필요없음
        this.prev = this.start > 1; // 11 > 1 boolean true
        this.next = total > this.end * this.size; // 437 > 20 * 10 = 200 boolean true
    }

    public int getSkip(){
        return (page -1) * 10;
    }

}
