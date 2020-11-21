package pl.sda.finalapp.app.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String roleName;

    public Role(String roleName) {
        this.roleName=roleName;
    }

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
