package in.indigenous.xpense.daos.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.ExpenseDao;
import in.indigenous.xpense.models.ExpenseLineItem;

@Component(value = "expenseDao")
public class DefaultExpenseDaoImpl implements ExpenseDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ExpenseLineItem> getExpenseList() {
		String SQL = "select expense_id, category_id, sub_category_id, amount, created, remarks from expense";
		return jdbcTemplate.query(SQL, new RowMapper<ExpenseLineItem>() {

			public ExpenseLineItem mapRow(ResultSet rs, int row) throws SQLException {
				ExpenseLineItem expense = new ExpenseLineItem();
				expense.setId(rs.getInt("expense_id"));
				expense.setCategory(rs.getInt("category_id"));
				expense.setSubCategory(rs.getInt("sub_category_id"));
				expense.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				expense.setCreated(date);
				expense.setRemarks(rs.getString("remarks"));
				return expense;
			}

		});
	}
	
	public List<ExpenseLineItem> getExpenseListBetweenDates(Date date1, Date date2)
	{
		String SQL = "select expense_id, category_id, sub_category_id, amount, created, remarks from expense where created between ? and ?";
		return jdbcTemplate.query(SQL, new Object[]{date1,date2}, new RowMapper<ExpenseLineItem>() {

			public ExpenseLineItem mapRow(ResultSet rs, int row) throws SQLException {
				ExpenseLineItem expense = new ExpenseLineItem();
				expense.setId(rs.getInt("expense_id"));
				expense.setCategory(rs.getInt("category_id"));
				expense.setSubCategory(rs.getInt("sub_category_id"));
				expense.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				expense.setCreated(date);
				expense.setRemarks(rs.getString("remarks"));
				return expense;
			}

		});
	}

	public int addExpense(ExpenseLineItem expense) {
		String SQL = "insert into expense (category_id, sub_category_id, amount, created,remarks) values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, expense.getCategory());
			pstmt.setInt(2, expense.getSubCategory());
			pstmt.setDouble(3, expense.getAmount());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(5, expense.getRemarks());
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
