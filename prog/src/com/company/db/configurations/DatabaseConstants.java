package com.company.db.configurations;

public class DatabaseConstants {
    public static final String DIRECTORS_TABLE = "directors";

    public static final String DIRECTORS_ID = DIRECTORS_TABLE + ".Director_ID";
    public static final String DIRECTORS_NAME = DIRECTORS_TABLE + ".Full_name";
    public static final String DIRECTORS_BIRTHDAY = DIRECTORS_TABLE + ".Birthday";

    public static final String ACTORS_TABLE = "actors";

    public static final String ACTORS_ID = ACTORS_TABLE + ".Actor_ID";
    public static final String ACTORS_NAME = ACTORS_TABLE + ".Full_name";
    public static final String ACTORS_BIRTHDAY = ACTORS_TABLE + ".Birthday";

    public static final String FILMS_TABLE = "films";

    public static final String FILMS_ID = FILMS_TABLE + ".Film_ID";
    public static final String FILMS_NAME = "Film_name";
    public static final String FILMS_DIRECTOR = FILMS_TABLE + ".Director_ID";
    public static final String FILMS_COUNTRY = "Country";
    public static final String FILMS_YEAR = "_Year";

    public static final String FILMS_ACTORS_TABLE = "films_actors";

    public static final String FILMS_ACTORS_FILM = FILMS_ACTORS_TABLE + ".Film_ID";
    public static final String FILMS_ACTORS_ACTOR = FILMS_ACTORS_TABLE + ".Actor_ID";
}