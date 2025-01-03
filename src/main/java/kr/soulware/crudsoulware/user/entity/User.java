package kr.soulware.crudsoulware.user.entity;

import jakarta.persistence.*;
import kr.soulware.crudsoulware.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @ElementCollection(targetClass = AuthorityType.class)
    @CollectionTable(name = "USER_AUTHORITY", joinColumns = @JoinColumn(name = "USER_ID"))
    @Enumerated(EnumType.STRING)
    private List<AuthorityType> authorities = new ArrayList<>();

    @Builder
    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities.add(AuthorityType.USER);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
        this.username = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .collect(Collectors.toList());
    }
}
