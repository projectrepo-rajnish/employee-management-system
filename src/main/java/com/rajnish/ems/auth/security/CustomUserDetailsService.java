package com.rajnish.ems.auth.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rajnish.ems.auth.entity.Role;
import com.rajnish.ems.auth.entity.UserAccount;
import com.rajnish.ems.auth.enums.UserStatus;
import com.rajnish.ems.auth.repository.UserAccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	System.out.println("loaduserbyname...."+username);
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Role role : userAccount.getRoles()) {
            String authorityName = "ROLE_" + role.getName().name();
            authorities.add(new SimpleGrantedAuthority(authorityName));
        }

        boolean enabled = UserStatus.ACTIVE.equals(userAccount.getStatus());
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = !userAccount.isAccountLocked();

        return new org.springframework.security.core.userdetails.User(
                userAccount.getUsername(),
                userAccount.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities
        );
    }
}