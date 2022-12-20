package com.ndn.enums;

import java.util.ArrayList;
import java.util.List;

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
    public static List<MembershipLevel> getMembershipLevels() {
        List<MembershipLevel> membershipLevels = new ArrayList<>();
        membershipLevels.add(Silver);
        membershipLevels.add(Gold);
        membershipLevels.add(Platinum);
        return membershipLevels;
    }
}
