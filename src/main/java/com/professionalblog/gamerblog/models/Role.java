package com.professionalblog.gamerblog.models;

import com.professionalblog.gamerblog.enums.RoleName;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TB_ROLE")
public class Role implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;
    @ManyToMany(mappedBy = "roles")
    private List<Users> users;
    public Role() {
    }

    public Role(Long id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public RoleName getRoleName() {
        return roleName;
    }
    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
