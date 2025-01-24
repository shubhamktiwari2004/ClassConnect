package com.eduportal.core.ClassConnect.Config;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomJdbcTokenRepositoryImpl implements PersistentTokenRepository {

    private final DataSource dataSource;
    private final String tableName;

    public CustomJdbcTokenRepositoryImpl(DataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.tableName = tableName;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        String sql = "INSERT INTO " + tableName + " (username, series, token, last_used) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, token.getUsername());
            statement.setString(2, token.getSeries());
            statement.setString(3, token.getTokenValue());
            statement.setTimestamp(4, new java.sql.Timestamp(token.getDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create token in " + tableName, e);
        }
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String sql = "UPDATE " + tableName + " SET token = ?, last_used = ? WHERE series = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tokenValue);
            statement.setTimestamp(2, new java.sql.Timestamp(lastUsed.getTime()));
            statement.setString(3, series);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update token in " + tableName, e);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String sql = "SELECT username, series, token, last_used FROM " + tableName + " WHERE series = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, seriesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new PersistentRememberMeToken(
                            resultSet.getString("username"),
                            resultSet.getString("series"),
                            resultSet.getString("token"),
                            resultSet.getTimestamp("last_used")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve token from " + tableName, e);
        }
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        String sql = "DELETE FROM " + tableName + " WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove tokens from " + tableName, e);
        }
    }
}
