package br.com.fatec.oqfazer.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import com.google.common.collect.Lists;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

public class EventDAOImpl implements EventDAO{

	private RegionDAO daoRegion;
	private UserDAO daoUser;
	
	public EventDAOImpl() {
		this.daoRegion = ImplFinder.getImpl(RegionDAO.class);
		this.daoUser = ImplFinder.getImpl(UserDAO.class);
	}
	
	@Override
	public Long inserEvent(Event event) {
		Connection conn = null;
		PreparedStatement insert = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String columns = DAOUtils.getColumns(getDefaultConnectionType(), Event.getColumns());
			
			String values = DAOUtils.completeClauseValues(getDefaultConnectionType(), Event.getColumns().size()-1, "SEQ_EVENT");
			
			String sql = "INSERT INTO "+ Event.TABLE + columns +" VALUES "+ values;
			
			insert = DAOUtils.buildStatment(sql, conn, getDefaultConnectionType(), Event.getColumnsArray());
			
			insert.setString(1, event.getName());
			insert.setString(2, event.getDescription());
			insert.setDate(3, DAOUtils.convertDateToSqlDate(event.getRegistration_date()));
			insert.setDate(4, DAOUtils.convertDateToSqlDate(event.getEvent_date()));
			insert.setString(5, event.getLocal());
			insert.setString(6,event.getImageURL());
			insert.setLong(7, event.getRegion().getId());
			insert.setLong(8, event.getOwner().getId());
			insert.execute();
			
			ResultSet generatedKeys = insert.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			}
			return null;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void deleteEvent(Long id) {
		Connection conn = null;
		PreparedStatement delete = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM " + Event.TABLE + " WHERE EVN_ID = ?;";
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
	public void updateEvent(Event event) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Event.TABLE + " SET " + Event.COL_NAME + " = ?," 
																			 + Event.COL_DESCRIPTION + " = ?,"
																			 + Event.COL_REGISTRATION_DATE + " = ?,"
																			 + Event.COL_EVENT_DATE + " = ?,"
																			 + Event.COL_LOCAL + " = ?,"
																			 + Event.COL_IMAGE_URL + " = ?,"
																			 + Event.COL_REGION_ID + " = ?,"
																			 + Event.COL_OWNER_ID + " = ? "
																			 + " WHERE " + Event.COL_ID + " = ?");
			update.setString(1, event.getName());
			update.setString(2, event.getDescription());
			update.setDate(3, DAOUtils.convertDateToSqlDate(event.getRegistration_date()));
			update.setDate(4, DAOUtils.convertDateToSqlDate(event.getEvent_date()));
			update.setString(5, event.getLocal());
			update.setString(6,event.getImageURL());
			update.setLong(7, event.getRegion().getId());
			update.setLong(8, event.getOwner().getId());
			update.setLong(9, event.getId());
			update.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(update);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public Event searchEventById(Long id) {
		Connection conn = null;
		PreparedStatement search = null;
		Event event = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM "+ Event.TABLE +" WHERE "+ Event.COL_ID +" = ?";
			search = conn.prepareStatement(sql);
			search.setLong(1,id);
			ResultSet rs = search.executeQuery();
			if(rs.next()){
				event =  this.buildEvent(rs);
			}
			return event;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Event> searchAllEvents() {
		Connection conn = null;
		PreparedStatement search = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM "+ Event.TABLE +" ORDER BY "+ Event.COL_ID;
			search = conn.prepareStatement(sql);
			ResultSet rs = search.executeQuery();
			return this.buildEvents(rs);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Event> searchEventsByListIds(List<Long> idsEvent) {
		List<Event> events = Lists.newArrayList();
		if(idsEvent != null){
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement search = null;
			try{
				String args = DAOUtils.preparePlaceHolders(idsEvent.size());
				String sql = "SELECT * FROM " + Event.TABLE + " WHERE " + Event.COL_ID + " IN ("+ args +") ORDER BY "+ Event.COL_ID;
				search = conn.prepareStatement(sql);
				DAOUtils.setValues(search, idsEvent);
				ResultSet rs = search.executeQuery();
				if(rs != null) {
					events = this.buildEvents(rs);
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}finally{
				DbUtils.closeQuietly(search);
				DbUtils.closeQuietly(conn);
			}
		}
		return events;
	}
	
	@Override
	public List<Event> searchEventsByIdUser(Long idUser) {
		List<Event> events = Lists.newArrayList();
		Connection conn = ConfigDBMapper.getDefaultConnection();
		PreparedStatement search = null;
		try{
			String sql = "SELECT * FROM "+ Event.TABLE +" WHERE "+ Event.COL_OWNER_ID+" = ? ORDER BY "+Event.COL_ID;
			search = conn.prepareStatement(sql);
			ResultSet rs = search.executeQuery();
			if(rs != null){
				events = this.buildEvents(rs);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
		return events;
	}
	
	private Event buildEvent(ResultSet rs) throws SQLException{
		Event event = new Event();
		event.setId(rs.getLong(Event.COL_ID));
		event.setName(rs.getString(Event.COL_NAME));
		event.setDescription(rs.getString(Event.COL_DESCRIPTION));
		event.setRegistration_date(rs.getDate(Event.COL_REGISTRATION_DATE));
		event.setEvent_date(rs.getDate(Event.COL_EVENT_DATE));
		event.setLocal(rs.getString(Event.COL_LOCAL));
		event.setImageURL(rs.getString(Event.COL_IMAGE_URL));
		Long idRegion = rs.getLong(Event.COL_REGION_ID);
		Long idUser = rs.getLong(Event.COL_OWNER_ID);
		event.setRegion(this.daoRegion.searchRegionById(idRegion));
		event.setOwner(this.daoUser.searchUserById(idUser));		
		return event;
	}
	
	private List<Event> buildEvents(ResultSet rs) throws SQLException{
		List<Event> events = Lists.newArrayList();
		while(rs.next()){
			events.add(this.buildEvent(rs));
		}
		return events;
	}

	@Override
	public List<Long> searchEvents(Long id) {
		List<Long> eventsIds = Lists.newArrayList();
		if (id != null){
			Connection conn = null;
			PreparedStatement search = null;
			try {
				conn = ConfigDBMapper.getDefaultConnection();
				String sql = "SELECT" + Event.COL_ID +" FROM " + Event.TABLE + " WHERE " + Event.COL_ID + " = ?";
				search = conn.prepareStatement(sql);
				ResultSet rs = search.executeQuery();
				return buildIdEvents(rs);
			} catch (Exception e){
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(search);
				DbUtils.closeQuietly(conn);
			}
		}
		return eventsIds;
	}
	
	public Long buildEventId(ResultSet rs) throws SQLException {
		Long eventId;
		eventId = rs.getLong(Event.COL_ID);
		return eventId;
	}

	private List<Long> buildIdEvents(ResultSet rs) throws SQLException {
		List<Long> eventsIds = Lists.newArrayList();
		while (rs.next()) {
			eventsIds.add(this.buildEventId(rs));
		}
		return eventsIds;
	}
}
