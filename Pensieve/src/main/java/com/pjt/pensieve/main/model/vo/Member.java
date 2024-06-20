package com.pjt.pensieve.main.model.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails
{
    private static final long serialVersionUID = 3047469130914582982L;
    
    private int    memberNo;  //����ڰ�����
    private String memberId;  //����ڹ�ȣ
    private String memberPw;  //����ھ��̵�
    private String role;      //����ھ��̵�
    private Date   createDate;//����ں�й�ȣ

    @Override
    public String getUsername()
    {
        return this.memberId;
    }
    
    @Override
    public String getPassword() {
        return this.memberPw;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        authorities.add(new SimpleGrantedAuthority(this.role));
        
        return authorities;
    }
    //������ ����Ǿ������� ��ȯ�ϴ� �޼ҵ�
    @Override
    public boolean isAccountNonExpired()
    {
        return true;//������ ���Ῡ��
    }

    //���� ��� ���θ� ��ȯ�ϴ� �޼ҵ�
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    //�н����� ���Ῡ�θ� ��ȯ�ϴ� �޼ҵ�
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    //���� Ȱ�� ����
    @Override
    public boolean isEnabled()
    {
//        return this.status.equals("Y");
        return true;
    }
}
