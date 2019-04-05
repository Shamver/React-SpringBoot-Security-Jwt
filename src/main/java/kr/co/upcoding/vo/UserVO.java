package kr.co.upcoding.vo;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserVO {
    Long id;
    String username;
    String email;
    String password;
    List<RoleVO> roles;
    String usernameOrEmail;
    boolean isAccountNonExpired;
    boolean isAccountNonLocked;
    boolean isCredentialsNonExpired;
    boolean isEnabled;

    public UserVO(){

    }

    public UserVO(String username, String email, String password, boolean isAccountNonExpired
        , boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled){
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }
}
