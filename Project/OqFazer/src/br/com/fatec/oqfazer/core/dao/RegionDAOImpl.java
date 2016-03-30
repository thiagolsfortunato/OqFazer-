package br.com.fatec.oqfazer.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

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
			String values = DAOUtils.completeClauseValues(getDefaultConnectionType(), Region.getColumns().size(), "SEQ_REGION");
			
			String sql = "INSERT INTO "+ Region.TABLE + columns + " VALUES " + values;
			
			insert = DAOUtils.buildStatment(sql, conn, getDefaultConnectionType(), Region.getColumnsArray());
			
			insert.setLong(1, region.getId());
			insert.setString(2, region.getName());
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
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRegion(Region region) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Region searchRegionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Region> searchAllRegions() {
		// TODO Auto-generated method stub
		return null;
	}

}
