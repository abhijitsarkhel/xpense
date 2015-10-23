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

import in.indigenous.xpense.daos.PurseMoneyDao;
import in.indigenous.xpense.models.PurseMoney;

@Component("purseMoneyDao")
public class DefaultPurseMoneyDaoImpl implements PurseMoneyDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<PurseMoney> getPurseMoneyList() {
		String SQL = "select purse_money_id, amount, created from purse_money";
		return jdbcTemplate.query(SQL, new RowMapper<PurseMoney>() {

			public PurseMoney mapRow(ResultSet rs, int rowNum) throws SQLException {
				PurseMoney money = new PurseMoney();
				money.setId(rs.getInt("purse_money_id"));
				money.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				money.setCreated(date);
				return money;
			}

		});
	}

	public int addPurseMoney(double amount) {
		String SQL = "insert into purse_money(amount, created) values (?,?)";
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
	
	public PurseMoney getPurseMoney(Date date) {
		String SQL = "select purse_money_id, amount, created from purse_money where created = ?";
		System.out.println("timestamp :: " + new Timestamp(date.getTime()));
		return jdbcTemplate.queryForObject(SQL, new Object[] {new Timestamp(date.getTime())}, new RowMapper<PurseMoney>() {
			public PurseMoney mapRow(ResultSet rs, int rowNum) throws SQLException {
				PurseMoney money = new PurseMoney();
				money.setId(rs.getInt("purse_money_id"));
				money.setAmount(rs.getDouble("amount"));
				Date date = new Date(rs.getTimestamp("created").getTime());
				money.setCreated(date);
				return money;
			}
		});
	}
}
