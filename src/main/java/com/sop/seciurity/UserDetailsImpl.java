package com.sop.seciurity;

import com.sop.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Klasa UserDetailsImpl implementuje interfejs {@link UserDetails}
 * Jest on wymagany do zarządzania danych użytkownika przez spring-security
 *
 * @see org.springframework.security.core.userdetails.UserDetails
 */
public class UserDetailsImpl implements UserDetails {

    /**
     * Przechwouje obiekt typu {@link UserEntity}
     */
    private final UserEntity user;

    /**
     * Konsruktor
     *
     * @param user użytkownik przekazany do zapisania w obiekcie
     */
    public UserDetailsImpl(UserEntity user) {
        this.user = user;
    }

    /**
     * Sprawdza role użytkownika i zwraca je w postaci kolekcji obiektów implementujących {@link GrantedAuthority}
     *
     * @return zwraca Liste obiektów implementujących {@link GrantedAuthority} które określaja role użytkownika
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        return List.of(authority);
    }

    /**
     * @return zwraca hasło użytkownika w postaci Striga
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * @return zwraca nazwe użytkownika w postaci Striga
     */
    @Override
    public String getUsername() {
        return user.getLogin();
    }

    /**
     * @return zwraca czy konto użytkownika wygasło
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return zwraca czy konto użytkownika jest zablokowane
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return zwraca czy poświdaczenie dla konta użytkownika wygasło
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return zwraca czy konto użytkownika jest aktywne
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}