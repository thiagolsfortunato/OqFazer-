package br.com.fatec.oqfazer.core.dao;

import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class CityDAOImpl implements CityDAO{

	private RegionDAO regionDAO;
	
	public CityDAOImpl() {
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
	}
	
	
	@Override
	public Long insertCity(Region region, List<City> cities) {
		Connection conn = null;
		PreparedStatement insert = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String columns = DAOUtils.getColumns(getDefaultConnectionType(), City.getColumns());
			
			String sql = "INSERT INTO "+ City.TABLE + columns + " VALUES "; 
			return null;
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void deleteCity(Region region) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateCity(Region region, List<City> cities) {
		Connection conn = null;
		PreparedStatement update = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "UPDATE ";
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(update);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void updateCity(Region region, City city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public City searchCityById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> searchAllCity() {
		// TODO Auto-generated method stub
		return null;
	}
}
