package com.dictionaryapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{
        @Column(nullable = false, unique = true)
        private String username;
        @Column(nullable = false)
        private String password;
        @Column(nullable = false, unique = true)
        private String email;
        @OneToMany(mappedBy = "addedBy")
        List<WordEntity> addedWords;

        public UserEntity() {
        }

        public String getUsername() {
                return username;
        }

        public UserEntity setUsername(String username) {
                this.username = username;
                return this;
        }

        public String getPassword() {
                return password;
        }

        public UserEntity setPassword(String password) {
                this.password = password;
                return this;
        }

        public String getEmail() {
                return email;
        }

        public UserEntity setEmail(String email) {
                this.email = email;
                return this;
        }

        public List<WordEntity> getAddedWords() {
                return addedWords;
        }

        public UserEntity setAddedWords(List<WordEntity> addedWords) {
                this.addedWords = addedWords;
                return this;
        }
}
