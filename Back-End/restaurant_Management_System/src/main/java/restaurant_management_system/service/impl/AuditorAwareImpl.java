package restaurant_management_system.service.impl;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import restaurant_management_system.dto.UsersDto;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
 /*   public Optional<String> getCurrentAuditor()
    {
       *//* //===>   GetUser
        UsersDto currentUser = (UsersDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUser.getUsername();*//*

        return Optional.of("username");
    }*/
}
