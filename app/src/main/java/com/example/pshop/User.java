package com.example.pshop;

public class User {
        private String name;            //用户名
        private String password;        //密码
        private String mail;            //邮箱

        public User(String name, String mail,String password) {
            this.name = name;
            this.mail = mail;
            this.password = password;


        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getMail() {
        return mail;
       }
       public void setMail(String mail) {
        this.mail = mail;
       }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", mail='"+mail +'\''+
                    ", password='" + password + '\'' +
                    '}';
        }
}


