package com.example.demo.common;

public final class Constants {
    private Constants() {}

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_INACTIVE = 0;

    public static final int DbTypeBook = 1;
    public static final int DbTypeBookLike = 2;
    public static final int DbTypeUser = 3;

    public static final int ShardTypeBook = 1;
    public static final int ShardTypeBookLike = 2;
    public static final int ShardTypeUser = 3;


    public static final String CLOUDINARYNAME = System.getenv("CLOUDINARY_NAME");
    public static final String CLOUDINARYKEY = System.getenv("CLOUDINARY_KEY");
}
