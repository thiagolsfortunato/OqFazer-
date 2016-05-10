package br.com.fatec.oqfazer.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import com.google.common.collect.Lists;
import br.com.fatec.oqfazer.api.dao.Participation;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class ParticipationDAOImpl implements Participation {

	public static final String TABLE = "PARTICIPATION";
	public static final String COL_ID_EVENT = "PTC_EVENT_ID";
	public static final String COL_ID_USER = "PTC_USER_ID";

	@Override
	public Long insertParticipation(Long eventId, Long userId) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "INSERT INTO " + TABLE + " VALUES (?,?)";
			insert = conn.prepareStatement(sql);
			insert.setLong(1, eventId);
			insert.setLong(2, userId);
			insert.execute();
			return eventId;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void deleteParticipation(Long eventId, Long userId) {
		Connection conn = null;
		PreparedStatement delete = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM " + TABLE + " WHERE " + COL_ID_EVENT + " = ? AND " + COL_ID_USER + " = ?";
			delete = conn.prepareStatement(sql);
			delete.setLong(1, eventId);
			delete.setLong(2, userId);
			delete.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(delete);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Long> searchUsers(Long eventId) {
		List<Long> users = Lists.newArrayList();
		if (eventId != null) {
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement search = null;
			try {
				String sql = "SELECT " + COL_ID_USER + " FROM " + TABLE + " WHERE " + COL_ID_EVENT + " = ?";
				search = conn.prepareStatement(sql);
				search.setLong(1, eventId);
				ResultSet rs = search.executeQuery();
				while (rs.next()) {
					users.add(rs.getLong(COL_ID_USER));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(search);
				DbUtils.closeQuietly(conn);
			}
		}
		return users;
	}
	
	@Override
	public List<Long> searchEvents(Long userId){
		List<Long> events = Lists.newArrayList();
		if (userId != null) {
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement search = null;
			try {
				String sql = "SELECT " + COL_ID_EVENT + " FROM " + TABLE + " WHERE " + COL_ID_USER + " = ?";
				search = conn.prepareStatement(sql);
				search.setLong(1, userId);
				ResultSet rs = search.executeQuery();
				while (rs.next()) {
					events.add(rs.getLong(COL_ID_EVENT));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(search);
				DbUtils.closeQuietly(conn);
			}
		}
		return events;
	}

	@Override
	public void updateEventParticipations(long userId, List<Event> events) {
		if(!events.isEmpty()){
			events.removeAll(Collections.singleton(null));
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement delete = null;
			PreparedStatement insert = null;
			try{
				String sqlDelete = "DELETE * FROM "+ TABLE +" WHERE "+ COL_ID_USER +" = ?";
				delete = conn.prepareStatement(sqlDelete);
				delete.setLong(1, userId);
				delete.execute();
				delete.close();
				for (Event event : events) {
					String sqlInsert = "INSERT INTO "+ TABLE + " VALUES (?,?)";
					insert = conn.prepareStatement(sqlInsert);
					insert.setLong(1, userId);
					insert.setLong(2, event.getId());
					insert.execute();
				}
			}catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(insert);
				DbUtils.closeQuietly(delete);
				DbUtils.closeQuietly(conn);
			}
		}
	}

	@Override
	public void updateUserParticipations(long eventId, List<User> users) {
		if(users != null){
			users.removeAll(Collections.singleton(null));
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement delete = null;
			PreparedStatement insert = null;
			try{
				String sqlDelete = "DELETE * FROM "+ TABLE +" WHERE "+ COL_ID_EVENT +" = ?";
				delete = conn.prepareStatement(sqlDelete);
				delete.setLong(1, eventId);
				delete.execute();
				delete.close();
				for (User user : users) {
					String sqlInsert = "INSERT INTO "+ TABLE + " VALUES (?,?)";
					insert = conn.prepareStatement(sqlInsert);
					insert.setLong(1, eventId);
					insert.setLong(2, user.getId());
					insert.execute();
				}
			}catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(insert);
				DbUtils.closeQuietly(delete);
				DbUtils.closeQuietly(conn);
			}
		}	
	}
}
