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

import in.indigenous.xpense.daos.IncomeDao;
import in.indigenous.xpense.models.Income;

@Component("incomeDao")
public class DefaultIncomeDaoImpl implements IncomeDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int addIncome(Income income) {
		String SQL = "insert into income(income_opt, amount, created, remarks) values(?,?,?,?)";
		try {
			PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, income.getIncomeOption());
			pstmt.setDouble(2, income.getAmount());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(4, income.getRemarks());
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

	public List<Income> getIncomeList() {
		String SQL = "select income_id, income_opt, amount, created, remarks from income";

		return jdbcTemplate.query(SQL, new RowMapper<Income>() {

			public Income mapRow(ResultSet rs, int row) throws SQLException {
				Income income = new Income();
				income.setId(rs.getInt("income_id"));
				income.setIncomeOption(rs.getString("income_opt"));
				income.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				income.setCreated(date);
				income.setRemarks(rs.getString("remarks"));
				return income;
			}

		});
	}
	
	public List<Income> getIncomeListBetweenDates(Date date1, Date date2)
	{
		String SQL = "select income_id, income_opt, amount, created, remarks from income where created between ? and ?";

		return jdbcTemplate.query(SQL, new Object[]{date1,date2},new RowMapper<Income>() {

			public Income mapRow(ResultSet rs, int row) throws SQLException {
				Income income = new Income();
				income.setId(rs.getInt("income_id"));
				income.setIncomeOption(rs.getString("income_opt"));
				income.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				income.setCreated(date);
				income.setRemarks(rs.getString("remarks"));
				return income;
			}

		});
	}
}
