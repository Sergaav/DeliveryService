package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.EntityMapper;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.Parcel;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.bean.DestinationsBean;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.EntityService;
import com.savaz.delivery.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class JDBCOrderDao implements OrderDao {
    private static final String SQL_SELECT_USER_ORDERS = "SELECT * FROM orders WHERE users_id=?";
    private static final String SQL_FIND_PARCEL_BY_ID = "SELECT * FROM parsels WHERE id=?";
    private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";
    private Connection connection;

    private static final String SQL_CREATE_NEW_PARCEL = "INSERT INTO parsels values (default,?,?,?,?,?,?)";
    private static final String SQL_CREATE_NEW_ORDER = "INSERT INTO orders values (default,?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_FIND_ALL_ORDERS_BY_PAGE = "SELECT * FROM orders LIMIT 7 OFFSET ?";

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(OrderBean entity) {
        ResultSet resultSetParcel = null;
        ResultSet resultSet = null;

        try (PreparedStatement statementParcel = connection.prepareStatement(SQL_CREATE_NEW_PARCEL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementOrder = connection.prepareStatement(SQL_CREATE_NEW_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statementParcel.setString(1, entity.getParcel().getDescription());
            statementParcel.setInt(2, entity.getParcel().getLength());
            statementParcel.setInt(3, entity.getParcel().getWidth());
            statementParcel.setInt(4, entity.getParcel().getHeight());
            statementParcel.setInt(5, entity.getParcel().getWeight());
            statementParcel.setInt(6, entity.getParcel().getWeightRateId());
            statementParcel.executeUpdate();
            int parcelId = 0;
            resultSetParcel = statementParcel.getGeneratedKeys();
            while (resultSetParcel.next()) {
                parcelId = resultSetParcel.getInt(1);
            }
            entity.getParcel().setId(parcelId);
            statementOrder.setString(1, entity.getAddress());
            statementOrder.setString(2, entity.getStatus().toString());
            statementOrder.setDate(3, Date.valueOf(entity.getDateCreation()));
            statementOrder.setInt(4, entity.getParcel().getId());
            statementOrder.setInt(5, entity.getUser().getId());
            statementOrder.setInt(6, entity.getCityDepartureId());
            statementOrder.setInt(7, entity.getCityArriveId());
            statementOrder.setDate(8, Date.valueOf(entity.getDateDeparture()));
            statementOrder.setString(9, entity.getRecipientName());
            statementOrder.setLong(10, entity.getBill());
            statementOrder.executeUpdate();
            resultSet = statementParcel.getGeneratedKeys();
            while (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeResultSet(resultSetParcel);
            closeConnection(connection);
        }

    }

    public static void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<OrderBean> findAllUserOrdersByUserId(int userId) {
        List<OrderBean> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ORDERS)) {
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderBean bean = new OrderBeanMapper().mapRow(resultSet);
                list.add(bean);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public Parcel findParcel(int id) {
        ResultSet resultSet = null;
        Parcel parcel = new Parcel();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_PARCEL_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                parcel.setId(resultSet.getInt("id"));
                parcel.setLength(resultSet.getInt("length"));
                parcel.setWidth(resultSet.getInt("width"));
                parcel.setHeight(resultSet.getInt("height"));
                parcel.setDescription(resultSet.getString("description"));
                parcel.setWeightRateId(resultSet.getInt("weight_rate_id"));
                parcel.setWeight(resultSet.getInt("weight"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return parcel;
    }

    @Override
    public List<OrderBean> findAllByPage(int page) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS_BY_PAGE);
            statement.setInt(1, 7 * (page - 1));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderBean bean = new OrderBeanMapper().mapRow(resultSet);
                orderBeanList.add(bean);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
        return orderBeanList;
    }

    @Override
    public List<OrderBean> findAllByPageWithFilters(int page, HttpServletRequest request) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Status status = Status.valueOf(request.getParameter("status"));
        LocalDate dateCreation = LocalDate.parse(request.getParameter("date_creation"));
        int cityArriveId = Integer.parseInt(request.getParameter("cityArriveId"));

        return null;
    }


    @Override
    public OrderBean findById(int id) {
        return null;
    }

    @Override
    public List<OrderBean> findAll() {
        ResultSet resultSet = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderBean bean = new OrderBeanMapper().mapRow(resultSet);
                orderBeanList.add(bean);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return orderBeanList;
    }

    @Override
    public void update(OrderBean entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    private static class OrderBeanMapper implements EntityMapper<OrderBean> {

        @Override
        public OrderBean mapRow(ResultSet rs) {
            OrderBean bean = new OrderBean();
            try {
                bean.setId(rs.getInt("id"));
                bean.setAddress(rs.getString("address"));
                bean.setStatus(Status.valueOf(rs.getString("status")));
                bean.setDateCreation(rs.getDate("date_creation").toLocalDate());
                bean.setParcel(new EntityService().getParcelById(rs.getInt("parsels_id")));
                bean.setCityDepartureId(rs.getInt("departure_id"));
                bean.setCityArriveId(rs.getInt("arrive_id"));
                bean.setDateDeparture(rs.getDate("date_departure").toLocalDate());
                bean.setRecipientName(rs.getString("recipient_name"));
                bean.setBill(rs.getLong("bill"));
                bean.setUser(new UserService().getUserById(rs.getInt("users_id")));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return bean;
        }
    }
}
