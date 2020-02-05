package com.growit.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.growit.api.dto.UserRegistrationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@Table(name = "usr")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "name", length = 50)
    protected String name;

    protected String userpic;

    protected String gender;

    protected String locale;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime lastVisit;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    protected String lastName;

    @Size(max = 50)
    @Column(name = "middle_name", length = 50)
    protected String middleName;

    @Column(name = "age", length = 3)
    protected Integer age;

    @ManyToOne
    @JoinColumn(name = "address_id")
    protected Address address;

    @OneToOne
    protected Passport passport;

    @Column(name = "married", nullable = true)
    protected boolean married;

    @Email
    @Size(max = 85)
    @Column(length = 85)
    protected String email;

    protected String password;

    protected String phone;

    protected int monthlyIncomeTotal;

    @Column(name = "active", nullable = true)
    protected boolean active;

    @Transient
    protected Collection<? extends GrantedAuthority> authorities;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    protected LocalDateTime birthday;

    public User(User user) {
        //    this(new UserRegistrationDto(user));
    }

    public User (UserRegistrationDto dto) {
        this.name = dto.getName();
        this.middleName = dto.getMiddleName();
        this.lastName = dto.getLastName();
        this.gender = dto.getGender();
        this.birthday = dto.getBirthday();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.created = dto.getCreated();
        this.updated = this.created;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
