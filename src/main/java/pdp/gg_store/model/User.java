package pdp.gg_store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pdp.gg_store.model.audit.AbsUUIDEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "user")
@SQLRestriction("deleted=false")
@SQLDelete(sql = "(update users set deleted=true where id=?)")
public class User extends  AbsUUIDEntity implements UserDetails {
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false, length = 1000)
    private String password;
    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;
    @ManyToOne
    private Role role;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "avatarUrl")
    private  String avatarUrl;
    @Column(name = "lastVisitDate")
    private Timestamp lastVisitDate;
    @Column(name = "active", nullable = false)
    private  boolean active;
    @Column(name = "banned", nullable = false)
    private boolean banned;

    @ManyToMany
    @JoinTable(name = "blockedusers",
    joinColumns =@JoinColumn(name="user_id") ,
    inverseJoinColumns = @JoinColumn(name="blockedUser_id"))
    private List<User> blockedUsers;
    @ManyToMany
    @JoinTable(name = "blockedByUsers",
            joinColumns =@JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="blockedByUser_id"))
    private List<User>blockedByUsers;
    private  Integer reputation;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "UserSettings_id")
    private UserSettings settings;



    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissions()
                .stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
