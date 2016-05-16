package br.com.fatec.oqfazer.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import com.google.common.collect.Lists;
import br.com.fatec.oqfazer.api.dao.EventCategory;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class EventCategoryDAOImpl implements EventCategory {
	
	public static final String TABLE = "EVENT_CATEGORY";
	public static final String COL_ID_EVENT = "ECT_EVENT_ID";
	public static final String COL_ID_CATEGORY = "ECT_CATEGORY_ID";
	
	@Override
	public Long insertEventCategory(Long idEvent, Long idCategory) {
		Connection conn = null;
		PreparedStatement insert = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "INSERT INTO "+ TABLE +" VALUES (?,?)";
			insert = conn.prepareStatement(sql);
			insert.setLong(1, idEvent);
			insert.setLong(2, idCategory);
			insert.execute();
			return idEvent;
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public Long insertEventCategory(Long idEvent, List<Category> categories) {
		Connection conn = null;
		PreparedStatement insert = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			for(Category category : categories){
				String sql = "INSERT INTO "+ TABLE +" VALUES (?,?)";
				insert = conn.prepareStatement(sql);
				insert.setLong(1, idEvent);
				insert.setLong(2, category.getId());
				insert.execute();
			}			
			return idEvent;
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void updateEventCategory(Long idEvent, List<Category> categories) {
		if(categories != null){
			categories.removeAll(Collections.singleton(null));
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement delete = null;
			PreparedStatement insert = null;
			try{
				String sqlDelete = "DELETE FROM "+ TABLE +" WHERE "+ COL_ID_EVENT +" =?";
				delete = conn.prepareStatement(sqlDelete);
				delete.setLong(1, idEvent);
				delete.execute();
				delete.close();
				
				for(Category category : categories){
					String sqlInsert = "INSERT INTO "+ TABLE +" VALUES (?,?)";
					insert = conn.prepareStatement(sqlInsert);
					insert.setLong(1, idEvent);
					insert.setLong(2, category.getId());
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
	public void deleteEventCategory(Long idEvent, Long idCategory) {
		Connection conn = null;
		PreparedStatement delete = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM "+ TABLE +" WHERE "+ COL_ID_EVENT +" =? and "+ COL_ID_CATEGORY +" = ?";
			delete = conn.prepareStatement(sql);
			delete.setLong(1, idEvent);
			delete.setLong(2, idCategory);
			delete.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(delete);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Long> searchCategories(Long idEvent) {
		Connection conn = null;
		PreparedStatement select = null;
		List<Long> idsCategories = Lists.newArrayList();
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT "+ COL_ID_CATEGORY +" FROM "+ TABLE + " WHERE "+ COL_ID_EVENT +" = ?";
			select = conn.prepareStatement(sql);
			select.setLong(1, idEvent);
			ResultSet rs = select.executeQuery();
			while(rs.next()){
				idsCategories.add(rs.getLong(COL_ID_CATEGORY));
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(select);
			DbUtils.closeQuietly(conn);
		}
		return idsCategories;
	}
	
	@Override
	public List<Long> searchEvents(Long idCategory) {
		List<Long> idsEvents = Lists.newArrayList();
		if(idCategory != null){
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement search = null;
			try{
				String sql = "SELECT "+ COL_ID_EVENT +" FROM "+ TABLE + " WHERE "+ COL_ID_CATEGORY +" = ?";
				search = conn.prepareStatement(sql);
				search.setLong(1, idCategory);
				ResultSet rs = search.executeQuery();
				while(rs.next()){
					idsEvents.add(rs.getLong(COL_ID_EVENT));
				}				
			}catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(search);
				DbUtils.closeQuietly(conn);
			}			
		}
		return idsEvents;
	}
}
