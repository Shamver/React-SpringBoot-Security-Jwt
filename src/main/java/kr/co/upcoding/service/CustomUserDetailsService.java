package kr.co.upcoding.service;

import kr.co.upcoding.config.UserPrincipal;
import kr.co.upcoding.mapper.UserMapper;
import kr.co.upcoding.repository.UserRepository;
import kr.co.upcoding.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserVO user=userRepository.findByUsernameOrEmail(s);

                if(user ==null){
                    new UsernameNotFoundException("User not found with username or email : "+s);
                }

            user = userRepository.findByIdJoin(user.getId());
        return UserPrincipal.create(user);
    }

    // for JWT
    @Transactional
    public UserDetails loadUserById(Long id){
        UserVO user = userRepository.findById(id);

        if(user==null){
            new UsernameNotFoundException("User not found with id : " + id);
        }

        return UserPrincipal.create(user);
    }
}
