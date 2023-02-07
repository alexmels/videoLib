package com.company.db;

import com.company.db.configurations.DatabaseConstants;
import com.company.db.connection.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseModification {
    DatabaseConnection dbConnection = new DatabaseConnection();
    PreparedStatement preparedStatement;
    public void deleteFilmsYearMoreThanN(int year) {
        try {
            String deleteFilmsYearMoreThanNStr = "DELETE FROM " + DatabaseConstants.FILMS_TABLE + " WHERE " +
                                                 DatabaseConstants.FILMS_YEAR + " > ?;";
            preparedStatement = dbConnection.getDbConnection().prepareStatement(deleteFilmsYearMoreThanNStr);
            preparedStatement.setInt(1, year);
            int deleteRows = preparedStatement.executeUpdate();
            System.out.printf("\n... %d films have been just deleted permanently ...\n", deleteRows);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("\nFATAL ERROR /// " + e);
        }
    }
}