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

import in.indigenous.xpense.daos.SubCategoryDao;
import in.indigenous.xpense.models.SubCategory;

@Component(value = "subCategoryDao")
public class DefaultSubCategoryDaoImpl implements SubCategoryDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<SubCategory> getSubCategories()
	{
		String SQL = "select sub_category_id, sub_category_name, category_id from sub_category";
		return jdbcTemplate.query(SQL, new RowMapper<SubCategory>() {

			public SubCategory mapRow(ResultSet rs, int row) throws SQLException {
				SubCategory subCategory = new SubCategory();
				subCategory.setSubCategoryId(rs.getInt("sub_category_id"));
				subCategory.setSubCategoryName(rs.getString("sub_category_name"));
				subCategory.setCategoryId(rs.getInt("category_id"));
				return subCategory;
			}
		});
	}
	
	public List<SubCategory> getSubCategories(int categoryId)
	{
		String SQL = "select sub_category_id, sub_category_name, category_id from sub_category where category_id = ?";
		return jdbcTemplate.query(SQL, new Object[] {categoryId},new RowMapper<SubCategory>() {

			public SubCategory mapRow(ResultSet rs, int row) throws SQLException {
				SubCategory subCategory = new SubCategory();
				subCategory.setSubCategoryId(rs.getInt("sub_category_id"));
				subCategory.setSubCategoryName(rs.getString("sub_category_name"));
				subCategory.setCategoryId(rs.getInt("category_id"));
				return subCategory;
			}
		});
	}
	
	public SubCategory getSubCategory(int subCategoryId)
	{
		String SQL = "select sub_category_id, sub_category_name, category_id from sub_category where sub_category_id = ?";
		return jdbcTemplate.queryForObject(SQL, new Object[] {subCategoryId},new RowMapper<SubCategory>() {

			public SubCategory mapRow(ResultSet rs, int row) throws SQLException {
				SubCategory subCategory = new SubCategory();
				subCategory.setSubCategoryId(rs.getInt("sub_category_id"));
				subCategory.setSubCategoryName(rs.getString("sub_category_name"));
				subCategory.setCategoryId(rs.getInt("category_id"));
				return subCategory;
			}
		});
	}
	
	public int addSubCategory(SubCategory subCategory)
	{
		String SQL = "insert into sub_category(sub_category_name, category_id) values(?,?)";
		try {
			PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, subCategory.getSubCategoryName());
			pstmt.setInt(2, subCategory.getCategoryId());
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
