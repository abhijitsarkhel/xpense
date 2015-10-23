package in.indigenous.xpense.daos.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.CategoryDao;
import in.indigenous.xpense.models.Category;

@Component(value = "categoryDao")
public class DefaultCategoryDaoImpl implements CategoryDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Category> getCategories() {
		String SQL = "select category_id, category_name from category";
		return jdbcTemplate.query(SQL, new RowMapper<Category>() {
			public Category mapRow(ResultSet rs, int row) throws SQLException {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				return category;
			}
		});
	}

	public Category getCategory(int categoryId) {
		String SQL = "select category_id, category_name from category where category_id = ?";
		return jdbcTemplate.queryForObject(SQL, new Object[] { categoryId }, new RowMapper<Category>() {
			public Category mapRow(ResultSet rs, int row) throws SQLException {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				return category;
			}
		});
	}

	public int addCategory(Category category) {
		String SQL = "insert into category (category_name) value(?)";
		try {
			PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, category.getCategoryName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
