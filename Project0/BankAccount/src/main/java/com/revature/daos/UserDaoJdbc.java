package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao {
	private ConnectionUtil cu = ConnectionUtil.cu;

	@Override
	public void createUser(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO bankaccount "
					+ "(username, pword, firstname, lastname, checkingsbalance, savingsbalance, pin) "
					+ "VALUES (?,?,?,?,?,?,?)", new String[] {"userid"});
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstname());
			ps.setString(4, u.getLastname());
			ps.setInt(5, u.getCBalance());
			ps.setInt(6, u.getSBalance());
			ps.setInt(7, u.checkPin());
			int updated = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt("userid");
				u.setId(id);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findUsernameAndPassword(String username, String password) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankaccount WHERE username=? and pword=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("pword"));
				u.setCBalance(rs.getInt("checkingsbalance"));
				u.setSBalance(rs.getInt("savingsbalance"));
				u.setId(rs.getInt("userid"));
				return u;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int checkPin(String username, String password, int pin) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT pin FROM bankaccount WHERE username=? and pword=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.createPin(rs.getInt("pin"));
				return u.checkPin();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public void updateUser(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE bankaccount SET checkingsbalance=?, savingsbalance=? WHERE username=? and pword=?");

			ps.setInt(1, u.getCBalance());
			ps.setInt(2, u.getSBalance());
			ps.setString(3, u.getUsername());
			ps.setString(4, u.getPassword());
			ps.executeUpdate();
			
//			PreparedStatement pps = conn.prepareStatement(
//					"UPDATE transactions SET userid=?, checkings=?,savings=?");
//			pps.setInt(1, u.getId());
//			pps.setString(2, u.getCheckings());
//			pps.setString(3, u.getSavings());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User u) {

	}

	@Override
	public User adminSearch(String username) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankaccount WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("pword"));
				u.setCBalance(rs.getInt("checkingsbalance"));
				u.setSBalance(rs.getInt("savingsbalance"));
				u.setId(rs.getInt("userid"));
				return u;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setTransactions(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO transactions (userid, checkings, savings)VALUES(?,?,?)");

			ps.setInt(1, u.getId());
			ps.setString(2, u.getCheckings());
			ps.setString(3, u.getSavings());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getTransactions(User u) {
		String result = "";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT checkings, savings FROM transactions WHERE userid=?");

			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				result = result + rs.getString("checkings");
				result = result +" checkings ";
				result = result + rs.getString("savings");
				result = result + " savings" + "\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
