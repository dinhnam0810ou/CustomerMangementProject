package com.ndn.enums;



public enum MembershipLevel {
    Silver(0, 0), Gold(2, 100), Platinum(5, 250);
    
    MembershipLevel() {}
    
    MembershipLevel(int ticketFree, int point) {
        this.ticketFree = ticketFree;
        this.point = point;
    }
    
    private int ticketFree;
    private int point;
    
    public int getTicketFree() {
        return ticketFree;
    }

    public int getPoint() {
        return point;
    }
  
}
