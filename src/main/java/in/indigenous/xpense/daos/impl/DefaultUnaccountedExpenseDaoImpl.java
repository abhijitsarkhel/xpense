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

import in.indigenous.xpense.daos.UnaccountedExpenseDao;
import in.indigenous.xpense.models.UnaccountedExpense;

@Component("unaccountedExpenseDao")
public class DefaultUnaccountedExpenseDaoImpl implements UnaccountedExpenseDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<UnaccountedExpense> getUnaccountedExpenseList() {
		String SQL = "select unacc_exp_id, amount, created from unaccounted_expense";
		return jdbcTemplate.query(SQL, new RowMapper<UnaccountedExpense>() {

			public UnaccountedExpense mapRow(ResultSet rs, int rowNum) throws SQLException {
				UnaccountedExpense expense = new UnaccountedExpense();
				expense.setId(rs.getInt("unacc_exp_id"));
				expense.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				expense.setCreated(date);
				return expense;
			}

		});
	}

	public int addUnaccountedExpense(double amount) {
		String SQL = "insert into unaccounted_expense(amount, created) values (?,?)";
		try {
			PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setDouble(1, amount);
			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public UnaccountedExpense getUnaccountedExpense(Date date) {
		String SQL = "select unacc_exp_id, amount, created from unaccounted_expense where created = ?";
		return jdbcTemplate.queryForObject(SQL, new Object[]{date},new RowMapper<UnaccountedExpense>() {
			public UnaccountedExpense mapRow(ResultSet rs, int rowNum) throws SQLException {
				UnaccountedExpense expense = new UnaccountedExpense();
				expense.setId(rs.getInt("unacc_exp_id"));
				expense.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				expense.setCreated(date);
				return expense;
			}
		});
	}
}
