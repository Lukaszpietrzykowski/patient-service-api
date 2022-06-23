package com.sop.seciurity;

import com.sop.entity.UserEntity;
import com.sop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Klasa UserDetailsServiceImpl implementuje interfejs {@link UserDetailsService}
 * Jest on wymagany do szukania użytkownika w bazie danych przez jego login
 *
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Przechowuje obiekt typu {@link UserRepository}
     */
    private final UserRepository userRepository;

    /**
     * Metoda pozwala na znalezienie użytkownika w bazie danych używając jego loginu
     *
     * @param username jest to nazwa użytkownika po którym go szukamy w bazie danych
     * @return zwraca obiekt z zaimplementowanym interfejsem {@link UserDetails}
     * @throws UsernameNotFoundException w razie gdy nie istnieje użytkownik o podanym loginie
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username);
        return new UserDetailsImpl(user);
    }
}