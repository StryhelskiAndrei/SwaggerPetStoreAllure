package com.itspartner.petstore;

public class User extends PetStore {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {

        private User mUser = new User();

        public Builder setId(int id) {
            this.mUser.id = id;
            return this;
        }

        public Builder setUsername (String name) {
            this.mUser.username = name;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.mUser.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.mUser.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.mUser.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.mUser.password = password;
            return this;
        }

        public Builder setPhone(String phone) {
            this.mUser.phone = phone;
            return this;
        }

        public Builder setUserStatus(int status) {
            this.mUser.userStatus = status;
            return this;
        }

        public User build() {
            return mUser;
        }
    }

    public static User createPositiveUser() {
        User user = new User.Builder()
                .setId(getRandomId())
                .setUsername(getRandomName())
                .setFirstName(getRandomName())
                .setLastName(getRandomName())
                .setPassword(getRandomPassword())
                .setEmail("test@email.com")
                .setPhone("234123")
                .setUserStatus(1)
                .build();

        return user;
    }

    public static User createPositiveUser(String username) {
        User user = new User.Builder()
                .setId(getRandomId())
                .setUsername(username)
                .setFirstName(getRandomName())
                .setLastName(getRandomName())
                .setPassword(getRandomPassword())
                .setEmail("test@email.com")
                .setPhone("234123")
                .setUserStatus(1)
                .build();

        return user;
    }
}
