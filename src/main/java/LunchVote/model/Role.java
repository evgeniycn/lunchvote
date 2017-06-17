package LunchVote.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;


    @Override
    public String getAuthority() {
        return name();
    }
}
