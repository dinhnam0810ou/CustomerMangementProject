package com.ndn.model;

import com.ndn.enums.Gender;
import com.ndn.enums.MembershipLevel;
import com.ndn.utils.ValidationUtils;

public class Customer {
    private int id;
    private String name;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private String email;
    private int point;
    private MembershipLevel membershipLevel;
    
    public Customer() {}
    public Customer(String name, Gender gender, String phoneNumber,
            String address, String email) {
        if( ValidationUtils.isEmpty(name) || ValidationUtils.isEmpty(gender.toString()) || ValidationUtils.isEmpty(phoneNumber) ) {
            System.out.println("Customer data can not empty");
        }
        else {
                this.name = name;
                this.gender = gender;
                this.phoneNumber = phoneNumber;
                this.address = address;
                this.email = email;
                this.point = 0;
        }  
    }
    
    
    public MembershipLevel getMembershipLevel() {
          if(this.point < membershipLevel.Gold.getPoint()) return membershipLevel.Silver;
          if(this.point < membershipLevel.Platinum.getPoint()) return membershipLevel.Gold;
          return membershipLevel.Platinum;
    }
    
    
    public int getTicketFree() {
        MembershipLevel level = getMembershipLevel();
        return level.getTicketFree();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
  
}
