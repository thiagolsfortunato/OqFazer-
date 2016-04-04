package br.com.fatec.oqfazer.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;


public class RegionDAOImpl implements RegionDAO{

	@Override
	public Long insertRegion(Region region) {
		Connection conn = null;
		PreparedStatement insert = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String columns = DAOUtils.getColumns(getDefaultConnectionType(), Region.getColumns());
			String values = DAOUtils.completeClauseValues(getDefaultConnectionType(), Region.getColumns().size()-1, "SEQ_REGION");
			
			String sql = "INSERT INTO "+ Region.TABLE + columns + " VALUES " + values;
			
			insert = DAOUtils.buildStatment(sql, conn, getDefaultConnectionType(), Region.getColumnsArray());
			
			//insert.setLong(1, region.getId());
			insert.setString(1, region.getName());
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
	public void deleteRegion(Long id) {
		Connection conn = null;
		PreparedStatement delete = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM "+ Region.TABLE + " WHERE RGN_ID = ?;";
			delete = conn.prepareStatement(sql);
			delete.setLong(1, id);
			delete.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(delete);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void updateRegion(Region region) {
		Connection conn = null;
		PreparedStatement update = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "UPDATE "+Region.TABLE+" SET "+ Region.COL_NAME +" = ? WHERE "+ Region.COL_ID +" = ?";
			update = conn.prepareStatement(sql);
			update.setString(1, region.getName());
			update.setLong(2, region.getId());
			update.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(update);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public Region searchRegionById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		Region region = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM "+ Region.TABLE +" WHERE "+ Region.COL_ID +" = ?";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if(rs.next()){
				region = this.buildRegion(rs);
			}
			return region;
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(find);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Region> searchAllRegions() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM "+ Region.TABLE +" ORDER BY "+ Region.COL_ID;
			findAll = conn.prepareStatement(sql);
			ResultSet rs = findAll.executeQuery();
			return buildRegions(rs);
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(findAll);
			DbUtils.closeQuietly(conn);
		}
	}
	
	public Region buildRegion(ResultSet rs) throws SQLException{
		Region region = new Region();
		region.setId(rs.getLong(Region.COL_ID));
		region.setName(rs.getString(Region.COL_NAME));
		return region;
	}
	
	public List<Region> buildRegions(ResultSet rs) throws SQLException{
		List<Region> regions = Lists.newArrayList();
		while(rs.next()){
			regions.add(this.buildRegion(rs));
		}
		return regions;
	}

}