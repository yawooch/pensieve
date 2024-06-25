package com.pjt.pensieve.main.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo
{
    private String menuId;        //메뉴아이디
    private String menuUrl;       //메뉴주소
    private String menuName;      //메뉴이름
    private int    menuLv;        //메뉴레벨
    private String menuParentId;  //메뉴부모아이디
    private String menuCreateDate;//메뉴생성일자
}
