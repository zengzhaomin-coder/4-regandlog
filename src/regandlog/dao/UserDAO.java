package regandlog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import regandlog.bean.User;
import regandlog.util.DBHelper;

public class UserDAO {
	// Ϊ��ע��
	// ���ݴ���� User�������ݿ����һ������
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
	
	// Ϊ�˵�¼
	// �����ݿ�У�飬��û�д� username��password ���û�������У�ȡ����
	public User get (String username, String password) throws Exception {
		String sql = "select usertype from myuser where username = ? and password = ?";
		
		try (Connection conn = DBHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, username);
			statement.setString(2, password);
			
			try (ResultSet rs = statement.executeQuery()) {
				// ���ݲ鵽�ˣ�˵���û����������ǶԵģ���ô��װ����
				if (rs.next()) {
					int userType = rs.getInt(1);
					
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setUserType(userType);
					
					return user;
				} else {
					// û�в鵽������ null��˵���û������������
					return null;
				}
			}
		}
	}

}
