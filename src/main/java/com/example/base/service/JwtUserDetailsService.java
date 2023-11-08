package com.example.base.service;

import com.example.base.config.audit.DefaultUserPrincipal;
import com.example.base.enums.Status;
import com.example.base.models.User;
import com.example.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserNameAndStatus(username, Status.ACTIVE);
    if (user == null) {
      throw new UsernameNotFoundException("Tài khoản/Mật khẩu không chính xác");
    } else if (Status.INACTIVE == user.getStatus()) {
      throw new UsernameNotFoundException("Tài khoản đã bị khóa");
    }
    DefaultUserPrincipal userPrincipal = new DefaultUserPrincipal(user.getId(), user.getUserName(),
        user.getPassWord(), user.getName());
//    userPrincipal.setPrivileges(user.getListPermission());
    return userPrincipal;
  }

}