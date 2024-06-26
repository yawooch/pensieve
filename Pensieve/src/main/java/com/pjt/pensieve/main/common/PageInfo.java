package com.pjt.pensieve.main.common;

import lombok.Getter;

public class PageInfo {
   private int currentPage;

   private int pageLimit;

   @Getter
   private int listCount;

   @Getter
   private int listLimit;   

   /**
    * @param currentPage ??Ž ??īė§?
    * @param pageLimit ? ??īė§?? ëģīėī? ??īė§?? ? 
    * @param listCount ? ėē? ëĶŽėĪ?ļ? ?
    * @param listLimit ? ??īė§?? ???  ëĶŽėĪ?ļ? ?
    */
   public PageInfo(int currentPage, int pageLimit, int listCount, int listLimit) {
      this.currentPage = currentPage;
      this.pageLimit = pageLimit;
      this.listCount = listCount;
      this.listLimit = listLimit;
   }

   /**    
    * @return ? ėē? ??īė§? ėĪ? ę°??Ĩ ë§ė?ë§? ??īė§?
    */
   public int getMaxPage() {
      /*
          listCount = 100, listLimit = 10
          100 / 10 = 10.0      => 10??īė§?
          101 / 10 = 10.1      => 11??īė§?
          103 / 10 = 10.3      => 11??īė§?
          109 / 10 = 10.9      => 11??īė§?
          110 / 10 = 11.0      => 11??īė§?
          111 / 10 = 11.1      => 12??īė§?
       */
      return (int) Math.ceil((double) this.listCount / this.listLimit);
   }

   /**
    * 
    * @return ??īė§? ? ??īė§? ėĪ? ?? ??īė§?
    */
   public int getStartPage() {
      /*   
         < 1 2 3 4 5 6 7 8 9 10 >
         < 11 12 13 14 15 16 17 18 19 20 >
         < 21 22 23 24 25 26 27 28 29 30 >
         
         1, 11, 21, 31, .... => (10 * n) + 1 (n >= 0)
         
         1 ~ 10 : n = 0
         11 ~ 20 : n = 1
         21 ~ 30 : n = 2
         31 ~ 40 : n = 3 
         .... 
         n = (currentPage - 1) / pageLimit
         
         (10 * ((currentPage - 1) / pageLimit)) + 1 (n >= 0)
       */
      return (this.pageLimit * ((this.currentPage - 1) / this.pageLimit)) + 1;
   }

   /**
    * 
    * @return ??īė§? ? ??īė§? ėĪ? ë§ė?ë§? ??īė§?
    */ 
   public int getEndPage() {
      // 10, 20, 30, 40, .... 

      int endPage = this.getStartPage() + this.pageLimit - 1;

      return endPage > this.getMaxPage() ? this.getMaxPage() : endPage;
   }   

   /**
    * 
    * @return ??Ž ??īė§?
    */ 
   public int getCurrentPage() {
      return this.currentPage;
   }

   /**
    * 
    * @return ?ī?  ??īė§?
    */ 
   public int getPrevPage() {
      int prevPage = this.getCurrentPage() - 1;

      return prevPage < 1 ? 1 : prevPage;
   }

   /**
    * 
    * @return ?Ī? ??īė§?
    */ 
   public int getNextPage() {
      int nextPage = this.getCurrentPage() + 1;

      return nextPage > this.getMaxPage() ? this.getMaxPage() : nextPage;
   }

   /**
    * 
    * @return ??īė§?? ?? ëĶŽėĪ?ļ 
    */    
   public int getStartList() {
      return (this.getCurrentPage() - 1) * this.listLimit + 1;
   }

   /**
    * 
    * @return ??īė§?? ë§ė?ë§? ëĶŽėĪ?ļ
    */    
   public int getEndList() {
      int endList = this.getStartList() + this.listLimit - 1;

      return endList > this.listCount ? this.listCount : endList;
   }
}
