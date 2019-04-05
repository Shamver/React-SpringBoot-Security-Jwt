package kr.co.upcoding.repository;

import kr.co.upcoding.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<UserVO> findByEmail(String email);
    Optional<UserVO> findByUsernameOrEmail(String username, String email);
    Optional<UserVO> findById(Long id);
    List<UserVO> findByIdIn(List<Long> userIds);
    Optional<UserVO> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
