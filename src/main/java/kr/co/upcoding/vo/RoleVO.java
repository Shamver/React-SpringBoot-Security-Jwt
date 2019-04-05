package kr.co.upcoding.vo;

import lombok.Data;

@Data
public class RoleVO {
    Long id;
    RoleName name;

    public RoleVO() {

    }

    public RoleVO(RoleName name){
        this.name = name;
    }
}
