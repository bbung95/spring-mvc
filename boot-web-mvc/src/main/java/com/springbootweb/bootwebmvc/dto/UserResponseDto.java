package com.springbootweb.bootwebmvc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.springbootweb.bootwebmvc.view.UserJsonView;

import java.time.LocalDateTime;

public class UserResponseDto {

    @JsonView(
            {UserJsonView.UserView.class
            ,UserJsonView.UserAdminView.class
            ,UserJsonView.UserListView.class}
    )
    private String userId;
    @JsonView(UserJsonView.UserModifiedView.class)
    private String password;
    @JsonView(
            {UserJsonView.UserView.class
            ,UserJsonView.UserAdminView.class
            ,UserJsonView.UserListView.class
            ,UserJsonView.UserModifiedView.class}
    )
    private String nickname;
    @JsonView(UserJsonView.UserView.class)
    private String username;
    @JsonView(
            {UserJsonView.UserView.class
            ,UserJsonView.UserAdminView.class
            ,UserJsonView.UserModifiedView.class}
    )
    private String email;
    @JsonView(
            {UserJsonView.UserView.class
            ,UserJsonView.UserAdminView.class
            ,UserJsonView.UserListView.class
            ,UserJsonView.UserModifiedView.class}
    )
    private String phone;
    @JsonView(UserJsonView.UserListView.class)
    private LocalDateTime createDate;
    @JsonView(UserJsonView.UserListView.class)
    private LocalDateTime modifiedDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
