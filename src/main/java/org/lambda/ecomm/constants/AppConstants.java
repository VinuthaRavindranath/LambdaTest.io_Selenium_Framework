package org.lambda.ecomm.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppConstants {

    public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
    public static final int DEFAULT_SHORT_TIME_OUT = 5;
    public static final int DEFAULT_LONG_TIME_OUT = 20;
    public static final String DEFAULT_SEARCH_PAGE_HEADER="Search - Touch";
    public static final String DEFAULT_SEARCH_TERM="Touch";
    public static final String PRICE_LOW_TO_HIGH_FILTER="Price (Low > High)";
    public static final String PRICE_HIGH_TO_LOW_FILTER="Price (High > Low)";
    public static final String HOME_PAGE_TITLE="Your Store";
    public static final String LOGIN_PAGE_TITLE="Account Login";

    public static final String WISHLIST_PAGE_TITLE="My Wish List";
    public static final String FORGOT_PASSWORD_SUCCESS_Message= "An email with a confirmation link has been sent your email address.";
    public static final String PRODUCT_NAME="HTC Touch HD";

    public static final String ADD_TO_CART_SUCCESS_MESSAGE="Success: You have added "+PRODUCT_NAME+" to your shopping cart !";
    public static final String EMPTY_WISHLIST_MESSAGE="No results!";
    public static final List<String> MANDATORY_INPUTS=Arrays.asList("input-firstname","input-lastname",
            "input-email","input-telephone","input-password","input-confirm");
    public static final List<String> MANDATORY_FIELDS_SYMBOL= Arrays.asList("*", "*", "*", "*", "*", "*");
    public static final String REGISTER_SHEET_NAME = "register";
    public static final CharSequence REGISTER_SUCCESS_MSG = "Your Account Has Been Created";
}
