package regandlog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import regandlog.bean.User;
import regandlog.util.DBHelper;

public class UserDAO {
	// 为了注册
	// 根据传入的 User，向数据库插入一条数据
	public void insert (User user) throws Exception {
		String sql = "insert into myuser (username, password, usertype) values (?,?,?)";
		
		try (Connection conn = DBHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getUserType());
			statement.executeUpdate();
		}
	}
	
	// 为了登录
	// 跟数据库校验，有没有此 username、password 的用户，如果有，取出来
	public User get (String username, String password) throws Exception {
		String sql = "select usertype from myuser where username = ? and password = ?";
		
		try (Connection conn = DBHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, username);
			statement.setString(2, password);
			
			try (ResultSet rs = statement.executeQuery()) {
				// 数据查到了，说明用户名、密码是对的，那么封装返回
				if (rs.next()) {
					int userType = rs.getInt(1);
					
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setUserType(userType);
					
					return user;
				} else {
					// 没有查到，返回 null，说明用户名或密码错了
					return null;
				}
			}
		}
	}

}
