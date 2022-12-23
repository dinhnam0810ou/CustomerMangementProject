package com.ndn.model;

import com.ndn.enums.Gender;
import com.ndn.enums.MembershipLevel;


public class Customer {
    private int id;
    private String name;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private String email;
    private int point;
    private int freeTicketAmount;
    private int usedFreeTicketAmount;
    public Customer() {}
    public Customer(String name, Gender gender, String phoneNumber,String address, String email) {
                this.name = name;
                this.gender = gender;
                this.phoneNumber = phoneNumber;
                this.address = address;
                this.email = email;
                this.point = 0;
    }
    
    
    public MembershipLevel getMembershipLevel() {
          if(this.point < MembershipLevel.Gold.getPoint()) return MembershipLevel.Silver;
          if(this.point < MembershipLevel.Platinum.getPoint()) return MembershipLevel.Gold;
          return MembershipLevel.Platinum;
    }
    
    
    public int getTicketFree() {
        MembershipLevel level = getMembershipLevel();
        freeTicketAmount = level.getTicketFree() - usedFreeTicketAmount >= 0 ? (level.getTicketFree() - usedFreeTicketAmount) : 0;
        return freeTicketAmount;
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
    @Override
    public String toString() {
        return name;
    }
    public int getUsedFreeTicketAmount() {
        return usedFreeTicketAmount;
    }
    public void setUsedFreeTicketAmount(int usedFreeTicketAmount) {
        this.usedFreeTicketAmount = usedFreeTicketAmount;
    }

}
