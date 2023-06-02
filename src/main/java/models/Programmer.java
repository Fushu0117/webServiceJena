package models;

public class Programmer {
    private String name;
    private String email;
    public Programmer(String name, String email){
        this.name = name;
        this.email = email;
    }
    public Programmer(){
        this.name = "";
        this.email = "";
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
}
