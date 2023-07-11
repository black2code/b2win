package com.example.b2win.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;


@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private String birthday;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "account", nullable = false, unique = true)
    private String account;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = true)
    private String email;

    private LocalDate passwordExpirationDate;

    private int passwordErrorCount;

    @Builder
    public User(String name, String birthday, String sex, String account, String password, String email){
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.account = account;
        this.password = password;
        this.email = email;
        this.passwordExpirationDate = passwordExpirationDate();
        this.passwordErrorCount = passwordErrorCount();
    }

    public LocalDate passwordExpirationDate() {
        return passwordExpirationDate = LocalDate.now().plusMonths(6); // 비밀번호 유효기간은 6개월로 설정
    }

    public int passwordErrorCount() {
        return passwordErrorCount = 0; // 비밀번호 에러 횟수 초기화
    }

    public boolean isPasswordExpired() {
        return LocalDate.now().isAfter(passwordExpirationDate);
    }

    public void incrementPasswordErrorCount() {
        this.passwordErrorCount++;
    }

    public void resetPasswordErrorCount() {
        this.passwordErrorCount = 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
        return true;
    }
}
