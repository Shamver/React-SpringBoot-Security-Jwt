package kr.co.upcoding.mapper;

import kr.co.upcoding.vo.RoleVO;
import kr.co.upcoding.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface UserMapper {
    UserVO findByEmail(String email);
    UserVO findByUsernameOrEmail(String usernameOrEmail);
    UserVO findById(Long id);
    List<UserVO> findByIdIn(List<Long> userIds);
    UserVO findByUsername(String username);
    UserVO existsByUsername(String username);
    UserVO existsByEmail(String email);
    UserVO findByIdJoin(Long id);
    boolean saveUser(UserVO vo);
    boolean saveRole(List<RoleVO> roles);
}
