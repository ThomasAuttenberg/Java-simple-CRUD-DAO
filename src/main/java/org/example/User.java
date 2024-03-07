package org.example;

@DataBaseTable(name = "users")
public class User implements DBSerializable{

    @DataBaseField(isPrimaryKey = true, isSequence = true)
    private long user_id;
    @DataBaseField
    private String username;
    @DataBaseField
    private String password;

    public long getId(){return user_id;}
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
    public String getUserName(){return username;}
    public String getPassword(){return password;}

    @Override
    public String toString() {
        return "username: "+username+" | password: "+password;
    }
}
