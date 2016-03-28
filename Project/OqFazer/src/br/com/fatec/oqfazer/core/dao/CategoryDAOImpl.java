package br.com.fatec.oqfazer.core.dao;

import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;
import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;


public class CategoryDAOImpl implements CategoryDAO{

	@Override
	public Long insertCategory(Category category) {
		Connection conn = null;
		PreparedStatement insert = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			
			String columns = DAOUtils.getColumns(ConfigDBMapper.getDefaultConnectionType(), Category.getColumns());
			
			String values = DAOUtils.completeClauseValues(getDefaultConnectionType(), Category.getColumns().size(), "SEQ_CATEGORY");
			
			String sql = "INSERT INTO" + Category.TABLE + columns + " VALUES " + values;
			
			insert = DAOUtils.buildStatment(sql, conn, getDefaultConnectionType(), Category.getColumnsArray());
			
			insert.setString(1, category.getName());
			Category cat = category.getCategory();
			if(cat != null){
				insert.setLong(2, cat.getId());
			}else{
				insert.setNull(2, Types.BIGINT);
			}
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
	public void deleteCategory(Long id) {
		Connection conn = null;
		PreparedStatement delete = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM " + Category.TABLE + " WHERE ID = ?;";
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
	public void updateCategory(Category category) {
		Connection conn = null;
		PreparedStatement update = null;
		try{
			conn = ConfigDBMapper.getDefaultConnection();
			List<String> sets = Lists.newArrayList();
			sets.add(Category.COL_NAME + "= ?");
			sets.add(Category.COL_ID_CATEGORY + "= ?");
			
			String sql = "UPDATE "+ Category.TABLE +" SET "+ StringUtils.join(sets, ", ")+" WHERE "+ Category.COL_ID +" = ?";
			
			update = conn.prepareStatement(sql);
			update.setString(1, category.getName());
			update.setLong(2, category.getCategory().getId());
			update.setLong(3, category.getId());
			update.execute();			
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(update);
		}		
	}

	@Override
	public Category searchCategoryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> searchAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}
}
