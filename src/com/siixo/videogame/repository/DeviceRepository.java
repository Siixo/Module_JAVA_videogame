package com.siixo.videogame.repository;
import com.siixo.videogame.database.Mysql;
import com.siixo.videogame.entity.Device;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeviceRepository {
    private final Connection connection;

    public DeviceRepository() {
        this.connection = Mysql.getConnection();
    }

    public Device find(Integer id) {
        Device device = null;
        try {
            String sql = "SELECT id, name, type, manufacturer FROM device WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                device = new Device(
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("manufacturer"));
                device.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Device> findAll() {
        ArrayList<Device> devices = new ArrayList<>();
        try {
            String sql = "SELECT id, name, type, manufacturer FROM device";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Device device = new Device(
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("manufacturer")
                );
                device.setId(resultSet.getInt("id"));
                devices.add(device);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return devices;
    }

    public Device findOneByName(String name) {
        Device device = null;
        try {
            String sql = "SELECT id, name, type, manufacturer FROM device WHERE name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                device.setName(rs.getString("name"));
                device.setType(rs.getString("type"));
                device.setManufacturer(rs.getString("manufacturer"));
                device.setId(rs.getInt("id"));
            } else {
                System.out.println("No device found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return device;
    }

    public boolean existsByName(String name) {
        try {
            String sql = "SELECT id FROM device WHERE name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            boolean exists = resultSet.next();
            return exists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDevice(Device device) {
        try {
            String sql = `INSERT INTO device(name, type, manufacturer) VALUE( ?,?,?)`;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, device.getName());
            preparedStatement.setString(2, device.getType());
            preparedStatement.setString(3, device.getManufacturer());
            int row = preparedStatement.executeUpdate();
            if (row < 1) {
                System.out.println("Enregistrement impossible");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Device updateDevice(Device device) {
        try {
            String sql = "UPDATE device SET name=?, type=?, manufacturer=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, device.getName());
            ps.setString(2, device.getType());
            ps.setString(3, device.getManufacturer());
            int row = ps.executeUpdate();
            if (row < 1) {
                System.out.println("Enregistrement impossible");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void deleteDevice (Integer id){
            try {
                String sql = "DELETE FROM device WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                int row = ps.executeUpdate();
                if (row < 1) {
                    System.out.println("Deletion impossible");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
