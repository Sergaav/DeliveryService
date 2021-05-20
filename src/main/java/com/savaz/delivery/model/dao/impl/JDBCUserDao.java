package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.Fields;
import com.savaz.delivery.model.dao.EntityMapper;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASS =
            "SELECT * FROM users WHERE login=? AND password=?";

    private static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";

    private static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    private static final String SQL_UPDATE_USER =
            "UPDATE users SET password=?, first_name=?, last_name=?, locale_name=?" +
                    "	WHERE id=?";

    public User findUserByLoginAndPass(String login, String password) {
        User user = null;
        ResultSet resultSet = null;
        PreparedStatement statement=null;
        try {
            statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASS);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new UserMapper().mapRow(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
           closeResultSet(resultSet);
           closeStatement(statement);
        }
        return user;
    }

    public User findUserByLogin(String login) {
        User user = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new UserMapper().mapRow(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return user;
    }


    public static void closeStatement (PreparedStatement statement){
        if (statement !=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeResultSet (ResultSet resultSet){
        if (resultSet !=null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean userIsExist(String login, String password) {
        return false;
    }


    public void create(User entity) {

    }


    public User findById(int id) {
        return null;
    }


    public List<User> findAll() {
        return null;
    }


    public void update(User entity) {

    }


    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getInt(Fields.USER__ID));
                user.setLogin(rs.getString(Fields.USER__LOGIN));
                user.setPassword(rs.getString(Fields.USER__PASSWORD));
                user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER__LAST_NAME));
                user.setLocale(rs.getString(Fields.USER__LOCALE));
                user.setRole(rs.getInt(Fields.USER__ROLE));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
