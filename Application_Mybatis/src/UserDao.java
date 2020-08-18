

public interface UserDao {
	public void selectAll(PageUtil<User> page);

	public int del(int id);

	public User ById(int id);

	public int upd(User u);

	public int add(User u);
}
