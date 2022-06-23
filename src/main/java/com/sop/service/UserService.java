package com.sop.service;

import com.sop.creators.UserCreator;
import com.sop.dto.UserDto;
import com.sop.entity.UserEntity;
import com.sop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa używająca interfejsu {@link UserRepository} który pozwala zarządać operacjami CRUD
 * na bazie danych
 */
@Service
@AllArgsConstructor
public class UserService {

    /**
     * Przechowuje obiekt typu {@link UserRepository}
     */
    private final UserRepository repository;

    /**
     * Metoda pobierająca wszystkie rekordy {@link UserEntity} z bazy danych i mapuje je na liste obiektów typu {@link UserDto}
     *
     * @return zwraca listę obiektów typu {@link UserDto}
     */
    public List<UserDto> getUsers() {
        return repository.findAll()
                .stream()
                .map(UserDto::of)
                .toList();
    }

    /**
     * Metoda tworząca rekord w bazie danych typu {@link UserEntity}
     *
     * @param userCreator obiekt typu {@link UserCreator} przechowujący dane użytkownika wykorzytsane do stworzenia rekordu
     * @return zwraca stworzony rekord z bazy danych przekonwertowany na obiekt typu {@link UserDto}
     */
    public UserDto createUser(UserCreator userCreator) {
        return UserDto.of(repository.save(UserEntity.of(userCreator)));
    }

    /**
     * Metoda pobierająca rekord {@link UserEntity} z bazy danych i mapująca go na obiekt typu {@link UserDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd informujący o braku ów rekordu
     *
     * @param id jest to ID rekordu w bazie danych
     * @return zwraca obiekt typu {@link UserDto}
     */
    public UserDto getUser(Long id) {
        return UserDto.of(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error user doesn't exist")));
    }

    /**
     * Metoda pobierająca rekord {@link UserEntity} z bazy danych i mapująca go na obiekt typu {@link UserDto}
     * W przypadku braku rekordu o podanym loginie wyrzuca błąd informujący o braku ów rekordu
     *
     * @param login jest to login użytkownika po którym go szukamy w bazie danych
     * @return zwraca obiekt typu {@link UserDto}
     */
    public UserDto getUserByUsername(String login) {
        return UserDto.of(repository.findByLogin(login));
    }

    /**
     * Metoda aktualizująca rekord {@link UserEntity} w bazie danych i mapująca go na obiekt typu {@link UserDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd o braku ów rekordu
     *
     * @param id          jest to ID  rekordu w bazie danych
     * @param userCreator jest to obiekt typu {@link UserCreator} który ma być zapisany w miejscu rekordu o podanym ID
     * @return zwraca zaktualizowany obiekt typu {@link UserDto}
     */
    public UserDto updateUser(Long id, UserCreator userCreator) {
        return UserDto.of(repository.findById(id)
                .map(oldUser -> {
                    UserEntity updatedUser = oldUser.updateWith(UserEntity.of(userCreator), oldUser.getPassword());
                    return repository.save(updatedUser);
                })
                .orElseThrow(() -> new RuntimeException("Error user doesn't exist")));
    }

    /**
     * Metoda usuwająca rekord {@link UserEntity} z bazy danych
     *
     * @param id jest to ID rekordu w bazie danych
     */
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}