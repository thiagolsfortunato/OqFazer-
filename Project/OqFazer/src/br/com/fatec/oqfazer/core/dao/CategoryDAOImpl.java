package br.com.fatec.oqfazer.core.dao;

import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;
import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public Long insertCategory(Category category) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();

			String columns = DAOUtils.getColumns(ConfigDBMapper.getDefaultConnectionType(), Category.getColumns());

			String values = DAOUtils.completeClauseValues(getDefaultConnectionType(), Category.getColumns().size() - 1,
					"SEQ_CATEGORY");

			String sql = "INSERT INTO " + Category.TABLE + columns + " VALUES " + values;

			insert = DAOUtils.buildStatment(sql, conn, getDefaultConnectionType(), Category.getColumnsArray());

			insert.setString(1, category.getName());
			if (category.getParent() != null) {
				insert.setLong(2, category.getParent());
			} else {
				insert.setNull(2, Types.BIGINT);
			}
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
	public void deleteCategory(Long id) {
		Connection conn = null;
		PreparedStatement delete = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM " + Category.TABLE + " WHERE " + Category.COL_ID + " = ?";
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
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			List<String> sets = Lists.newArrayList();
			sets.add(Category.COL_NAME + " = ?");
			sets.add(Category.COL_ID_CATEGORY + " = ?");

			String sql = "UPDATE " + Category.TABLE + " SET " + StringUtils.join(sets, ", ") + " WHERE "
					+ Category.COL_ID + " = ?";

			update = conn.prepareStatement(sql);
			update.setString(1, category.getName());
			if (category.getParent() != null) {
				update.setLong(2, category.getParent());
			} else {
				update.setNull(2, Types.BIGINT);
			}
			update.setLong(3, category.getId());
			update.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(update);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public Category searchCategoryById(Long id) {
		Connection conn = null;
		PreparedStatement search = null;
		Category category = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + Category.TABLE + " WHERE " + Category.COL_ID + " = ?";
			search = conn.prepareStatement(sql);
			search.setLong(1, id);
			ResultSet rs = search.executeQuery();
			if (rs.next()) {
				category = buildCategory(rs);
			}
			return category;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Category> searchAllCategory() {
		Connection conn = null;
		PreparedStatement search = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + Category.TABLE + " ORDER BY " + Category.COL_ID;
			search = conn.prepareStatement(sql);
			ResultSet rs = search.executeQuery();
			return buildCategories(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Category> searchCategoriesByListIds(List<Long> idsCategory) {
		List<Category> categories = Lists.newArrayList();
		if (idsCategory.size() > 0) {
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement search = null;
			try {
				for (long id : idsCategory) {
					String sql = "SELECT * FROM " + Category.TABLE + " WHERE " + Category.COL_ID + " = ?;";
					search = conn.prepareStatement(sql);
					search.setLong(1, id);
					ResultSet rs = search.executeQuery();
					categories = buildCategories(rs);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(search);
				DbUtils.closeQuietly(conn);
			}
		}
		return categories;
	}

	public Category buildCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getLong(Category.COL_ID));
		category.setName(rs.getString(Category.COL_NAME));
		category.setParent(rs.getLong(Category.COL_ID_CATEGORY));
		return category;
	}

	public List<Category> buildCategories(ResultSet rs) throws SQLException {
		List<Category> categories = Lists.newArrayList();
		while (rs.next()) {
			categories.add(this.buildCategory(rs));
		}
		return categories;
	}

	@Override
	public List<Long> searchCategoriesChildren (Long id) {
		List<Long> categoriesIds = Lists.newArrayList();
		Connection conn = null;
		PreparedStatement search = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			//String sql = "SELECT C1.CTG_ID FROM CATEGORY C1 JOIN (SELECT CTG_ID FROM CATEGORY) C2 ON C1.CTG_CATEGORY_ID = C2.CTG_ID WHERE C2.CTG__ID = ?;";
			//String sql = "SELECT C1.CTG_ID FROM CATEGORY C1 LEFT JOIN CATEGORY C2 ON C2.CTG_CATEGORY_ID = ?;";
			//String sql = "SELECT C1.CTG_ID FROM CATEGORY C1 JOIN (SELECT CTG_CATEGORY_ID FROM CATEGORY) C2 ON C1.CTG_ID = C2.CTG_CATEGORY_ID WHERE C2.CTG_CATEGORY_ID = ?;";
			String sql = "SELECT CTG_ID FROM CATEGORY WHERE CTG_CATEGORY_ID = ?;";
			search = conn.prepareStatement(sql);
			search.setLong(1, id);
			ResultSet rs = search.executeQuery();
			categoriesIds = buildIdCategories(rs);
			return categoriesIds;
		} catch (Exception e){
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
	}
	
	public Long buildCategoryId(ResultSet rs) throws SQLException {
		Long categoryId;
		categoryId = rs.getLong(Category.COL_ID);
		return categoryId;
	}

	private List<Long> buildIdCategories(ResultSet rs) throws SQLException {
		List<Long> categoriesIds = Lists.newArrayList();
		while (rs.next()) {
			categoriesIds.add(this.buildCategoryId(rs));
		}
		return categoriesIds;
	}

	@Override
	public List<Category> searchByCategory(Long idCategory) {
		List<Category> categories = Lists.newArrayList();
		if (idCategory != null) {
			Connection conn = ConfigDBMapper.getDefaultConnection();
			PreparedStatement search = null;
			try {
				String sql = "SELECT * FROM " + Category.TABLE + " WHERE " + Category.COL_ID + " = ?;";
				search = conn.prepareStatement(sql);
				search.setLong(1, idCategory);
				ResultSet rs = search.executeQuery();
				categories = buildCategories(rs);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(search);
				DbUtils.closeQuietly(conn);
			}
		}
		return categories;
	}

	@Override
	public Long searchChildCategory(Long id) {
		Connection conn = ConfigDBMapper.getDefaultConnection();
		PreparedStatement search = null;
		try {
			//String sql = "SELECT "+ Category.COL_ID_CATEGORY +" FROM " + Category.TABLE + " WHERE " + Category.COL_ID + " = ?;";
			String sql = "SELECT C1.CTG_ID_CATEGORY FROM CATEGORY C1 JOIN (SELECT CTG_ID FROM CATEGORY) C2 ON ? = C2.CTG_ID;";
			search = conn.prepareStatement(sql);
			search.setLong(1, id);
			ResultSet rs = search.executeQuery();
			return rs.getLong(Category.COL_ID_CATEGORY);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(search);
			DbUtils.closeQuietly(conn);
		}
	}
}
