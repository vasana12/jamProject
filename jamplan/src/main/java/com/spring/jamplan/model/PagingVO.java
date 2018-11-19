package com.spring.jamplan.model;

public class PagingVO {
   private int page; //page:현재페이지
   private int maxPage; //maxPage:전체글수의 대해서 나타낼 페이지수
   private int startPage; //startPage:한페이지내에서(현제페이지) 처음시작하는 부분
   private int endPage; //endPage:한페이지내에서 마지막부분 부분
   private int listCount; //listCount:전체페이지 갯수
   
   public void setData(int page, int pageNum, int listCount) {
      this.page = page;
      //계산하기 전값 가져와서 계산하기
      this.maxPage = (int)((double)listCount/pageNum + 0.95);
      this.startPage = (((int)((double)page / 10 +0.9)) -1)*10+1;
      this.endPage = startPage+10-1;
      
      if(maxPage < endPage) {
         endPage = maxPage;
      }
   }
   
   public int getPage() {
      return page;
   }
   public void setPage(int page) {
      this.page = page;
   }
   public int getMaxPage() {
      return maxPage;
   }
   public void setMaxPage(int maxPage) {
      this.maxPage = maxPage;
   }
   public int getStartPage() {
      return startPage;
   }
   public void setStartPage(int startPage) {
      this.startPage = startPage;
   }
   public int getEndPage() {
      return endPage;
   }
   public void setEndPage(int endPage) {
      this.endPage = endPage;
   }
   public int getListCount() {
      return listCount;
   }
   public void setListCount(int listCount) {
      this.listCount = listCount;
   }
}