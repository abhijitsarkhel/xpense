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

import in.indigenous.xpense.daos.AccountMoneyDao;
import in.indigenous.xpense.models.AccountMoney;

@Component("accountMoneyDao")
public class DefaultAccountMoneyDaoImpl implements AccountMoneyDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<AccountMoney> getAccountMoneyList() {
		String SQL = "select acc_bal_id, amount, created from account_balance";
		return jdbcTemplate.query(SQL, new RowMapper<AccountMoney>() {

			public AccountMoney mapRow(ResultSet rs, int rowNum) throws SQLException {
				AccountMoney money = new AccountMoney();
				money.setId(rs.getInt("acc_bal_id"));
				money.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				money.setCreated(date);
				return money;
			}

		});
	}

	public int addAccountMoney(double amount) {
		String SQL = "insert into account_balance(amount, created) values (?,?)";
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
	
	public AccountMoney getAccountMoney(Date date) {
		String SQL = "select acc_bal_id, amount, created from account_balance where created = ?";
		return jdbcTemplate.queryForObject(SQL, new Object[]{date},new RowMapper<AccountMoney>() {
			public AccountMoney mapRow(ResultSet rs, int rowNum) throws SQLException {
				AccountMoney money = new AccountMoney();
				money.setId(rs.getInt("acc_bal_id"));
				money.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				money.setCreated(date);
				return money;
			}
		});
	}
}
