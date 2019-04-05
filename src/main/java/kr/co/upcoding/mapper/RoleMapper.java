package kr.co.upcoding.mapper;

import kr.co.upcoding.vo.RoleName;
import kr.co.upcoding.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface RoleMapper {
    Optional<RoleVO> findByName(String roleName);
}
