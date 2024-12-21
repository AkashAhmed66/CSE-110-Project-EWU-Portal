package main;


public abstract class Member implements Information{

    protected String name="";
    protected int id;
    protected String contact="";
    protected String dob="";
    protected String address="";
    protected String bloodGroup="";
    public Member() {
    }
    public Member(String name, int id, String contact, String dob, String address, String bloodGroup) {
        this.name=name;
        this.id=id;
        this.contact=contact;
        this.dob=dob;
        this.address=address;
        this.bloodGroup=bloodGroup;
    }
}
