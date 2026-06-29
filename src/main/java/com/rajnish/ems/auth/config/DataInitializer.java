package com.rajnish.ems.auth.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rajnish.ems.auth.entity.Role;
import com.rajnish.ems.auth.entity.UserAccount;
import com.rajnish.ems.auth.enums.RoleName;
import com.rajnish.ems.auth.enums.UserStatus;
import com.rajnish.ems.auth.repository.RoleRepository;
import com.rajnish.ems.auth.repository.UserAccountRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserAccountRepository userAccountRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
System.out.println("Command line runner....");
        createRoleIfNotExists(RoleName.ADMIN);
        createRoleIfNotExists(RoleName.ADMINASD);
        createRoleIfNotExists(RoleName.HR);
        createRoleIfNotExists(RoleName.MANAGER);
        createRoleIfNotExists(RoleName.EMPLOYEE);
        testAdminPassword();
        createAdminUserIfNotExists();
    }

    private void createRoleIfNotExists(RoleName roleName) {

        if (!roleRepository.existsByName(roleName)) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }

    private void createAdminUserIfNotExists() {

        if (userAccountRepository.existsByUsername("admin")||userAccountRepository.existsByUsername("admin2")) {
            return;
        }

        Role adminRole = roleRepository.findByName(RoleName.ADMINASD)
                .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

        Set<Role> roles = new HashSet<Role>();
        roles.add(adminRole);

        UserAccount adminUser = new UserAccount();
        adminUser.setUsername("admin");
        adminUser.setEmail("admin@ems.com");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.setStatus(UserStatus.ACTIVE);
        adminUser.setAccountLocked(false);
        adminUser.setRoles(roles);

        userAccountRepository.save(adminUser);
    }
    
    private void testAdminPassword() {

        UserAccount adminUser = userAccountRepository.findByUsername("admin")
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        System.out.println("==== Admin DB password: " + adminUser.getPassword());
        System.out.println("==== Password matches admin123: "
                + passwordEncoder.matches("admin123", adminUser.getPassword()));
    }
}