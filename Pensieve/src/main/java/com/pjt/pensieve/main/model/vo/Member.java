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
    
    private int    memberNo;  //사용자가입일
    private String memberId;  //사용자번호
    private String memberPw;  //사용자아이디
    private String role;      //사용자아이디
    private Date   createDate;//사용자비밀번호

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
    //계정이 만료되었는지를 반환하는 메소드
    @Override
    public boolean isAccountNonExpired()
    {
        return true;//계정의 만료여부
    }

    //계정 잠김 여부를 반환하는 메소드
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    //패스워드 만료여부를 반환하는 메소드
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    //계정 활성 여부
    @Override
    public boolean isEnabled()
    {
//        return this.status.equals("Y");
        return true;
    }
}
