package edu.itplus.bibliospring.backend.repository.JDBC;
import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.RepositoryException;
import edu.itplus.bibliospring.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
@Profile("JDBC")
public class JdbcDao implements UserRepository{
    @Autowired
    private Logger LOG;
    @Autowired
    private ConnectionManager connectionManager;
    @Override
    public User findById(Long id) {
        Connection conn = null;
        try{
            conn=connectionManager.getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM users WHERE id=? ");
            ps.setLong(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUUID(rs.getString("uuid"));
                return user;
            }
            return null;
        } catch (SQLException e){
            LOG.error("ERROR: Connection pooling failure: ", e);
            throw new RepositoryException("Error:findId",e);
        }
        finally {
            connectionManager.returnConnection(conn);
        }
    }

    @Override
    public User create(User user) {
        Connection conn = null;
        try{
            conn=connectionManager.getConnection();
            PreparedStatement ps=conn.prepareStatement("INSERT INTO users (username,password,uuid) VALUES (?,?,?)"
                    , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUUID());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(1));
            return user;
        } catch(SQLException e){

            throw new RepositoryException("error: create ",e);
        }
        finally {
            connectionManager.returnConnection(conn);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {
        Connection conn = null;
        try{
            conn=connectionManager.getConnection();
            PreparedStatement ps=conn.prepareStatement("DELETE FROM users WHERE id=? ");
            ps.setLong(1,user.getId());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(1));
        } catch(SQLException e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<User> findAll() {
        Connection conn = null;
        try{
            conn=connectionManager.getConnection();
            List<User> users=new ArrayList<User>();
            Statement ps=conn.createStatement();
            ResultSet rs=ps.executeQuery("SELECT * FROM users");
            while(rs.next()){
                User user=new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUUID(rs.getString("uuid"));
                users.add(user);
            }
            return users;
        } catch(SQLException e){
            throw new RepositoryException("ERROR : findAll",e);
        }
        finally {
            connectionManager.returnConnection(conn);
        }
    }

    @Override
    public User findByUsername(String username) {
        Connection conn = null;
        try{
            conn=connectionManager.getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM users WHERE username=? ");
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUUID(rs.getString("uuid"));
                return user;
            }
            return null;
        } catch (SQLException e){
            throw new RepositoryException("ERROR : findByUsername",e);
        }
        finally {
            connectionManager.returnConnection(conn);
        }
    }
}
