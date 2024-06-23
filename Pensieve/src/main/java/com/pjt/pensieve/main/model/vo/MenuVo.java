package com.pjt.pensieve.main.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo
{
    private String menuId;        //�޴����̵�
    private String menuUrl;       //�޴��ּ�
    private String menuName;      //�޴��̸�
    private int    menuLv;        //�޴�����
    private String menuParentId;  //�޴��θ���̵�
    private String menuCreateDate;//�޴���������
}
