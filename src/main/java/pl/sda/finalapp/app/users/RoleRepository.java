package pl.sda.finalapp.app.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //Todo - napisać ręcznie zapytanie
    Role findByRoleName(String roleName);
}
