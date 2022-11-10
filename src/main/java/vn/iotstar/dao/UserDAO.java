package vn.iotstar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.iotstar.connection.DBConnection;
import vn.iotstar.model.UserModel;

public class UserDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void editRole(UserModel user) {
        String query = "UPDATE USERS SET role = ? WHERE users_username = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, user.getRole());
            ps.setString(2, user.getUsers_username());
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void insertUser(UserModel user) {
        String query = "INSERT INTO USERS(users_username, users_pass_word, role) VALUES(?, ?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsers_username());
            ps.setString(2, user.getUsers_pass_word());
            ps.setInt(3, user.getRole());
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void insertUserGG(UserModel user) {
        String query = "INSERT INTO USERS(users_username, users_last_name) VALUES(?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsers_username());
            ps.setString(2, user.getUsers_last_name());
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public UserModel getUser(String userName) {

        String query = "SELECT * FROM USERS WHERE users_username LIKE ?";

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + userName + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                return new UserModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    public UserModel getUserById(int id) {

        String query = "SELECT * FROM USERS WHERE users_id LIKE ?";

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new UserModel(
                		rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public UserModel checkAccount(String userName, String pass) {

        String query = "SELECT * FROM USERS WHERE users_username LIKE ? AND users_pass_word LIKE ?";

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new UserModel(
                		rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

}
