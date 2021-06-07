package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.EntityMapper;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.Parcel;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.EntityService;
import com.savaz.delivery.service.UserService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    private static final String SQL_SELECT_USER_ORDERS = "SELECT * FROM orders WHERE users_id=?";
    private static final String SQL_FIND_PARCEL_BY_ID = "SELECT * FROM parsels WHERE id=?";
    private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id=?";
    private static final String SQL_FIND_ALL_ORDERS_STATUS = "SELECT * FROM orders WHERE status=?";
    private static final String SQL_FIND_ALL_ORDERS_BY_DATE = "SELECT * FROM orders WHERE date_creation=?";
    private static final String SQL_FIND_ALL_ORDERS_BY_STATUS_DATE = "SELECT * FROM orders " +
            "WHERE status=? AND date_creation=?";
    private static final String SQL_FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id=?";
    private static final String SQL_FIND_USER_ORDERS_BY_STATUS_DATE = "SELECT * FROM orders WHERE users_id=? AND" +
            " status=? AND date_creation=?";
    private static final String SQL_FIND_USER_ORDERS_STATUS = "SELECT * FROM orders WHERE users_id=? AND" +
            " status=?";
    private static final String SQL_FIND_USER_ORDERS_BY_DATE = "SELECT * FROM orders WHERE users_id=? AND" +
            " date_creation=?";
    private static final String SQL_DELETE_PARCEL = "DELETE FROM parsels WHERE id=?";
    private static final String SQL_UPDATE_ORDER_STATUS = "UPDATE orders SET status=? WHERE id=?";
    private static final String SQL_UPDATE_PARCEL = "UPDATE parsels SET description=?,length=?," +
            "width=?,height=?,weight=?,weight_rate_id=? WHERE id=?";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET address=?,status=?,date_creation=?," +
            "parsels_id=?,users_id=?,departure_id=?,arrive_id=?,date_departure=?,recipient_name=?," +
            "bill=? WHERE id=?";
    private static final String SQL_FIND_ORDERS_BY_DATE_ARRIVE_DEPARTURE = "select * from orders where " +
            "date_creation=? and arrive_id=? and departure_id=?";
    private static final String SQL_FIND_ORDERS_BY_DATE_ARRIVE = "select * from orders where " +
            "date_creation=? and arrive_id=? ";
    private static final String SQL_FIND_ORDERS_BY_DATE_DEPARTURE ="select * from orders where " +
            "date_creation=? and departure_id=?";
    private static final String SQL_FIND_ORDERS_BY_DATE = "select * from orders where " +
            "date_creation=?";
    private static final String SQL_FIND_ORDERS_BY_ARRIVE_DEPARTURE = "select * from orders where " +
            "arrive_id=? and departure_id=?";
    private static final String SQL_FIND_ORDERS_BY_ARRIVE = "select * from orders where " +
            "arrive_id=?";
    private static final String SQL_FIND_ORDERS_BY_DEPARTURE = "select * from orders where " +
            "departure_id=?";
    private static final String SQL_CREATE_NEW_PARCEL = "INSERT INTO parsels values (default,?,?,?,?,?,?)";
    private static final String SQL_CREATE_NEW_ORDER = "INSERT INTO orders values (default,?,?,?,?,?,?,?,?,?,?)";


    private Connection connection;

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
    public List<OrderBean> findAllByWithStatus(Status status) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS_STATUS);
            statement.setString(1, status.name());
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
    public List<OrderBean> findAllByPageWithDate(LocalDate date) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS_BY_DATE);
            statement.setDate(1, Date.valueOf(date));
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
    public List<OrderBean> findAllByPageWithStatusAndDate(Status status, LocalDate date) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS_BY_STATUS_DATE);
            statement.setString(1, status.name());
            statement.setDate(2, Date.valueOf(date));
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
    public List<OrderBean> findAllByPageWithStatusAndDateByUser(int userId, Status status, LocalDate date) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_USER_ORDERS_BY_STATUS_DATE);
            statement.setInt(1, userId);
            statement.setString(2, status.name());
            statement.setDate(3, Date.valueOf(date));
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
    public List<OrderBean> findAllByPageWithStatusByUser(int userId, Status status) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_USER_ORDERS_STATUS);
            statement.setInt(1, userId);
            statement.setString(2, status.name());
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
    public List<OrderBean> findAllByPageWithDateByUser(int userId, LocalDate date) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_USER_ORDERS_BY_DATE);
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(date));
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
    public void deleteParcel(int parcelId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PARCEL)) {
            statement.setInt(1, parcelId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void updateStatus(int orderId, Status status) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS)) {
            statement.setString(1, status.name());
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<OrderBean> findAllOrdersByDateArriveDeparture(LocalDate date, int cityArriveId, int cityDepartureId) {
        List<OrderBean> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_DATE_ARRIVE_DEPARTURE)) {
            statement.setDate(1, Date.valueOf(date));
            statement.setInt(2,cityArriveId);
            statement.setInt(3,cityDepartureId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(new OrderBeanMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<OrderBean> findAllOrdersByDateArrive(LocalDate date, int cityArriveId) {
        List<OrderBean> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_DATE_ARRIVE)) {
            statement.setDate(1, Date.valueOf(date));
            statement.setInt(2,cityArriveId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(new OrderBeanMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<OrderBean> findAllOrdersByDateDeparture(LocalDate date, int cityDepartureId) {
        List<OrderBean> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_DATE_DEPARTURE)) {
            statement.setDate(1, Date.valueOf(date));
            statement.setInt(2,cityDepartureId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(new OrderBeanMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<OrderBean> findAllOrdersByDate(LocalDate date) {
        List<OrderBean> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_DATE)) {
            statement.setDate(1, Date.valueOf(date));
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(new OrderBeanMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<OrderBean> findAllOrdersByArriveDeparture(int cityArriveId, int cityDepartureId) {
        List<OrderBean> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_ARRIVE_DEPARTURE)) {
            statement.setInt(1,cityArriveId);
            statement.setInt(2,cityDepartureId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(new OrderBeanMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<OrderBean> findAllOrdersByArrive(int cityArriveId) {
        List<OrderBean> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_ARRIVE)) {
            statement.setInt(1,cityArriveId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(new OrderBeanMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<OrderBean> findAllOrdersByDeparture(int cityDepartureId) {
        List<OrderBean> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_DEPARTURE)) {
            statement.setInt(1,cityDepartureId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(new OrderBeanMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return list;
    }


    @Override
    public OrderBean findById(int id) {
        ResultSet resultSet = null;
        OrderBean bean = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bean = new OrderBeanMapper().mapRow(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return bean;
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
        try (PreparedStatement statementParcel = connection.prepareStatement(SQL_UPDATE_PARCEL);
             PreparedStatement statementOrder = connection.prepareStatement(SQL_UPDATE_ORDER)) {
            statementParcel.setString(1, entity.getParcel().getDescription());
            statementParcel.setInt(2, entity.getParcel().getLength());
            statementParcel.setInt(3, entity.getParcel().getWidth());
            statementParcel.setInt(4, entity.getParcel().getHeight());
            statementParcel.setInt(5, entity.getParcel().getWeight());
            statementParcel.setInt(6, entity.getParcel().getWeightRateId());
            statementParcel.setInt(7, entity.getId());
            statementParcel.executeUpdate();
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
            statementOrder.setInt(11, entity.getId());
            statementOrder.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection(connection);
        }
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
