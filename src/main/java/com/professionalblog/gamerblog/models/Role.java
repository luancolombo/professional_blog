package com.professionalblog.gamerblog.models;

import com.professionalblog.gamerblog.enums.RoleName;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_ROLE")
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;
    public Role() {
    }
    public Role(RoleName roleAdmin) {
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
}
