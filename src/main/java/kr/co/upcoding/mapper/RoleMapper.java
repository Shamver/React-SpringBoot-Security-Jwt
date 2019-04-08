package kr.co.upcoding.mapper;

import kr.co.upcoding.vo.RoleName;
import kr.co.upcoding.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RoleMapper {
    List<RoleVO> findById(Long id);
    Optional<RoleVO> findByName(String roleName);
}
