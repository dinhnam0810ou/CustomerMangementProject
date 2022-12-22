package com.ndn.utils;

import java.util.HashMap;
import java.util.Map;

import com.ndn.enums.Error;

public class PageUtils {
    public static final int PAGE_SIZE = 10;
    public static final Map<Error, String> ERROR_MESSAGE = new HashMap<>();
    static {
        ERROR_MESSAGE.put(Error.Quantity, "Quantity can not be empty");
        ERROR_MESSAGE.put(Error.FreeTicket, "Free ticket sold out or greater than quantity");
    }
    public static String getErrorMessage(Error error) {
        return ERROR_MESSAGE.get(error);
    }
    public static int caculateOffset(int pageIndex) {
        return pageIndex * PAGE_SIZE;
    }
}
