package br.com.fatec.oqfazer.core.dao;

import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class CityDAOImpl implements CityDAO{
	
	@Override
	public Long insertCity(Region region, List<City> cities) {
		Connection conn = null;
		PreparedStatement insert = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String columns = DAOUtils.getColumns(getDefaultConnectionType(), City.getColumns());
			for(City city : cities){
				String sql = "INSERT INTO "+ City.TABLE + columns + " VALUES (?,?)";
				insert = conn.prepareStatement(sql);
				insert.setLong(1, region.getId());
				insert.setString(2, city.getNome());
				insert.execute();
			}
			return region.getId();
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void deleteCity(Long regionId, List<City> cities) {
		Connection conn = null;
		PreparedStatement delete = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			for(City city : cities){
				String sql = "DELETE FROM "+ City.TABLE +" WHERE "+ City.COL_ID +"= ? and "+ City.COL_NAME +" = ?";
				delete = conn.prepareStatement(sql);
				delete.setLong(1, regionId);
				delete.setString(2, city.getNome());
				delete.execute();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(delete);
			DbUtils.closeQuietly(conn);
		}
		
	}

	@Override
	public List<String> searchCityByRegionId(Long regionId) {
		Connection conn = null;
		PreparedStatement search = null;
		List<String> cities = Lists.newArrayList();
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT CTY_NAME FROM "+ City.TABLE +" WHERE "+ City.COL_ID +" = ?";
			search = conn.prepareStatement(sql);
			search.setLong(1, regionId);
			ResultSet rs = search.executeQuery();
			while(rs.next()){
				cities.add(rs.getString("CTY_NAME"));
			}
			return cities;
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
	}

	public List<String> searchCityByRegionName(String nameRegion) {
		Connection conn = null;
		PreparedStatement search = null;
		List<String> cities = Lists.newArrayList();
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT CTY_NAME FROM "+ City.TABLE +" JOIN "+ Region.TABLE +" ON ("+ City.COL_ID +" = "+ Region.COL_ID+") WHERE "+ Region.COL_NAME +" = ?";
			search = conn.prepareStatement(sql);
			search.setString(1, nameRegion);
			ResultSet rs = search.executeQuery();
			while(rs.next()){
				cities.add(rs.getString("CTY_NAME"));
			}
			return cities;
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
	}
	
	@Override
	public List<String> searchAllCity() {
		Connection conn = null;
		PreparedStatement searchAll = null;
		List<String> cities = Lists.newArrayList();
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM CITY ORDER BY "+ City.COL_ID;
			searchAll = conn.prepareStatement(sql);
			ResultSet rs = searchAll.executeQuery();
			while(rs.next()){
				cities.add(rs.getString("CTY_NAME"));
			}
			return cities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(searchAll);
			DbUtils.closeQuietly(conn);
		}
	}
}
