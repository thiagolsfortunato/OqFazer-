package br.com.fatec.oqfazer.core.dao;

import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class UserDAOImpl implements UserDAO {

	@Override
	public Long insertUser(User user) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();

			String columns = DAOUtils.getColumns(getDefaultConnectionType(), User.getColumns());

			String values = DAOUtils.completeClauseValues(getDefaultConnectionType(), User.getColumns().size()-1,"SEQ_USER");

			String sql = "INSERT INTO " + User.TABLE + columns + " VALUES " + values;

			insert = DAOUtils.buildStatment(sql, conn, getDefaultConnectionType(), User.getColumnsArray());

			insert.setString(1, user.getName());
			insert.setString(2, user.getPassword());
			insert.setString(3, user.getEmail());
			insert.setLong(4, user.getPhone());
			insert.execute();

			ResultSet generatedKeys = insert.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void deleteUser(Long id) {
		Connection conn = null;
		PreparedStatement delete = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM " + User.TABLE + " WHERE USR_ID = ?;";
			delete = conn.prepareStatement(sql);
			delete.setLong(1, id);
			delete.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(delete);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void updateUser(User user) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + User.TABLE + " SET " + User.COL_NAME + " = ?," 
																			+ User.COL_EMAIL + " = ?," 
																			+ User.COL_PASSWORD + " = ?," 
																			+ User.COL_PHONE + " = ?"
																			+ " WHERE " + User.COL_ID + " = ?");
			update.setString(1, user.getName());
			update.setString(2, user.getEmail());
			update.setString(3, user.getPassword());
			update.setInt(4, user.getPhone());
			update.setLong(5, user.getId());
			update.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(update);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public User searchUserById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		User user = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + User.TABLE + " WHERE " + User.COL_ID + " =?;";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				user = this.buildUser(rs);
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(find);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<User> searchAllUsers() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + User.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildUsers(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(findAll);
			DbUtils.closeQuietly(conn);
		}
	}
	
	@Override
	public List<User> searchUsersByIds(List<Long> idsUser) {
		List<User> users = Lists.newArrayList();
		if(idsUser != null){
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement search = null;
			try{
				String args = DAOUtils.preparePlaceHolders(idsUser.size());
				String sql = "SELECT * FROM " + User.TABLE + " WHERE " + User.COL_ID + " IN ("+ args +") ORDER BY "+ User.COL_ID;
				search = conn.prepareStatement(sql);
				DAOUtils.setValues(search, idsUser);
				ResultSet rs = search.executeQuery();
				users = buildUsers(rs);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(search);
				DbUtils.closeQuietly(conn);
			}
		}
		return users;
	}

	private User buildUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(User.COL_ID));
		user.setName(rs.getString(User.COL_NAME));
		user.setPassword(rs.getString(User.COL_PASSWORD));
		user.setEmail(rs.getString(User.COL_EMAIL));
		user.setPhone(rs.getInt(User.COL_PHONE));
		return user;
	}
	
	private List<User> buildUsers(ResultSet rs) throws SQLException {
		List<User> usuarios = Lists.newArrayList();
		while (rs.next()) {
			usuarios.add(this.buildUser(rs));
		}
		return usuarios;
	}	
}
