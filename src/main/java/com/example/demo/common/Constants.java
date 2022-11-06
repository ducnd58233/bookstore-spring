package com.example.demo.common;

public final class Constants {
    private Constants() {}

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_INACTIVE = 0;

    public static final int SALT_LEN = 10;

    public static final String ROLE_USER = "user";

    public static final int DbTypeBook = 1;
    public static final int DbTypeUser = 1 << 1;

    public static final int ShardTypeBook = 1;
    public static final int ShardTypeUser = 1 << 1;

    public static final int JWT_LIFE_TIME =  3*60*60;


    public static final String JWT_SECRET = "springboot";

    public static final String CLOUDINARYNAME = System.getProperty("CLOUDINARY_NAME");
    public static final String CLOUDINARYKEY = System.getProperty("CLOUDINARY_KEY");
}
