package com.company.db;

import com.company.db.configurations.DatabaseConstants;
import com.company.db.connection.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSelection {
    DatabaseConnection dbConnection = new DatabaseConnection();
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private String sqlSelectStatement;

    public void getFilmsCurLastYear() {
        try  {
            statement = dbConnection.getDbConnection().createStatement();
            setSqlSelectStatement("SELECT * FROM " + DatabaseConstants.FILMS_TABLE + " WHERE " +
                  DatabaseConstants.FILMS_YEAR + " = year(curdate()) OR " + DatabaseConstants.FILMS_YEAR +
                  " = year(curdate())-1 ORDER BY " + DatabaseConstants.FILMS_ID + ";");
            resultSet = statement.executeQuery(getSqlSelectStatement());
            System.out.println("\nFILM_ID /// NAME /// DIRECTOR_ID /// COUNTRY /// YEAR\n");
            while (resultSet.next()) {
                int filmId = resultSet.getInt(DatabaseConstants.FILMS_ID);
                String name = resultSet.getString(DatabaseConstants.FILMS_NAME);
                int directorId = resultSet.getInt(DatabaseConstants.FILMS_DIRECTOR);
                String country = resultSet.getString(DatabaseConstants.FILMS_COUNTRY);
                int year = resultSet.getInt(DatabaseConstants.FILMS_YEAR);
                System.out.printf("%d /// %s /// %d /// %s /// %d\n", filmId, name, directorId, country, year);
            }
            System.out.println("... the end of the table ...");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("\nFATAL ERROR /// " + e);
        }
    }

    public void getActorsFromFilm(String filmName) {
        try {
            setSqlSelectStatement("SELECT " + DatabaseConstants.ACTORS_ID + ", " + DatabaseConstants.ACTORS_NAME +
                  " AS Actor, " + DatabaseConstants.ACTORS_BIRTHDAY + " FROM " + DatabaseConstants.ACTORS_TABLE +
                  " JOIN " + DatabaseConstants.FILMS_ACTORS_TABLE + " ON " + DatabaseConstants.ACTORS_ID + " = " +
                  DatabaseConstants.FILMS_ACTORS_ACTOR + " JOIN " + DatabaseConstants.FILMS_TABLE + " ON " +
                  DatabaseConstants.FILMS_ID + " = " + DatabaseConstants.FILMS_ACTORS_FILM + " WHERE " +
                  DatabaseConstants.FILMS_NAME + " = ? ORDER BY " + DatabaseConstants.ACTORS_ID + ";");
            preparedStatement = dbConnection.getDbConnection().prepareStatement(getSqlSelectStatement());
            preparedStatement.setString(1, filmName);
            resultSet = preparedStatement.executeQuery();
            System.out.println("\nACTOR_ID /// ACTOR /// BIRTHDAY\n");
            while (resultSet.next()) {
                int id = resultSet.getInt(DatabaseConstants.ACTORS_ID);
                String actor = resultSet.getString("Actor");
                String  birthday = resultSet.getString(DatabaseConstants.ACTORS_BIRTHDAY);
                System.out.printf("%d /// %s /// %s\n", id, actor, birthday);
            }
            System.out.println("... the end of the table ...");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("\nFATAL ERROR /// " + e);
        }
    }

    public void getCountOfRolesNotLessThanN(int countRoles) {
        try  {
            setSqlSelectStatement("SELECT " + DatabaseConstants.ACTORS_ID + ", " +
                  DatabaseConstants.ACTORS_NAME + " AS Actor, " + DatabaseConstants.ACTORS_BIRTHDAY +
                  ", count(*) AS Roles FROM " + DatabaseConstants.ACTORS_TABLE + " JOIN " +
                  DatabaseConstants.FILMS_ACTORS_TABLE + " ON " + DatabaseConstants.ACTORS_ID + " = " +
                  DatabaseConstants.FILMS_ACTORS_ACTOR + " JOIN " + DatabaseConstants.FILMS_TABLE + " ON " +
                  DatabaseConstants.FILMS_ID + " = " + DatabaseConstants.FILMS_ACTORS_FILM + " GROUP BY " +
                  DatabaseConstants.ACTORS_ID + " HAVING Roles >= ? ORDER BY " + DatabaseConstants.ACTORS_ID + ";");
            preparedStatement = dbConnection.getDbConnection().prepareStatement(getSqlSelectStatement());
            preparedStatement.setInt(1, countRoles);
            resultSet = preparedStatement.executeQuery();
            System.out.println("\nACTOR_ID /// ACTOR /// BIRTHDAY /// ROLES\n");
            while (resultSet.next()) {
                int id = resultSet.getInt(DatabaseConstants.ACTORS_ID);
                String actor = resultSet.getString("Actor");
                String  birthday = resultSet.getString(DatabaseConstants.ACTORS_BIRTHDAY);
                int roles = resultSet.getInt("Roles");
                System.out.printf("%d /// %s /// %s /// %d\n", id, actor, birthday, roles);
            }
            System.out.println("... the end of the table ...");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("\nFATAL ERROR /// " + e);
        }
    }

    public void getActorsDirectors() {
        try {
            statement = dbConnection.getDbConnection().createStatement();
            setSqlSelectStatement("SELECT " + DatabaseConstants.ACTORS_ID + ", " +
                  DatabaseConstants.ACTORS_NAME + " AS Actor, " + DatabaseConstants.DIRECTORS_BIRTHDAY +
                  ", count(*) AS Films FROM " + DatabaseConstants.DIRECTORS_TABLE + " JOIN " +
                  DatabaseConstants.FILMS_TABLE + " ON " + DatabaseConstants.DIRECTORS_ID + " = " +
                  DatabaseConstants.FILMS_DIRECTOR + " JOIN " + DatabaseConstants.ACTORS_TABLE + " ON " +
                  DatabaseConstants.ACTORS_NAME + " = " + DatabaseConstants.DIRECTORS_NAME + " GROUP BY " +
                  DatabaseConstants.ACTORS_ID + " ORDER BY " + DatabaseConstants.ACTORS_ID + ";");
            resultSet = statement.executeQuery(getSqlSelectStatement());
            System.out.println("\nACTOR_ID /// ACTOR /// BIRTHDAY /// FILMS\n");
            while (resultSet.next()) {
                int id = resultSet.getInt(DatabaseConstants.ACTORS_ID);
                String actor = resultSet.getString("Actor");
                String birthday = resultSet.getString(DatabaseConstants.DIRECTORS_BIRTHDAY);
                int films = resultSet.getInt("Films");
                System.out.printf("%d /// %s /// %s /// %d\n", id, actor, birthday, films);
            }
            System.out.println("... the end of the table ...");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("\nFATAL ERROR /// " + e);
        }
    }

    protected String getSqlSelectStatement() {
        return sqlSelectStatement;
    }

    protected void setSqlSelectStatement(String sql) {
        sqlSelectStatement = sql;
    }
}