

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class UserDaoImpl implements UserDao {

	public void selectAll(PageUtil<User> page) {
		SqlSession session = MySqlSession.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num1", page.getIndex() * page.getSize());
		map.put("num2", (page.getIndex() - 1) * page.getSize());
		List<User> list = session.selectList("UserInfo.all", map);
		page.setCount((Integer) session.selectOne("UserInfo.count"));
		page.setList(list);
		MySqlSession.closeSession();
	}

	public int del(int id) {
		
		//session.delete("UserInfo."파라미터) //
	    SqlSession session = null;
		
	   session = MySqlSession.getSession(); //
	    
	  try{
		  session.selectOne("UserInfo.delete",id);
	  }
	  finally{
		  session.commit();
		  MySqlSession.closeSession();
		 
	  }


		
	  return 1;
		
	}

	public User ById(int id) {
		
		//document.getElementById(id);
		
		return new User();
	}

	public int upd(User u) {//유저 객체를 만들어서 사용함. 
		//u를 파라미터로 던져야함.
		//session.update(파라미터전송)
		
		
		SqlSession session = null;
		
		   session = MySqlSession.getSession(); //
		    
		  try{
			  session.selectOne("UserInfo.update",u);
		  }
		  finally{
			  session.commit();
			  MySqlSession.closeSession();
			 
		  }


			
		  return 1;
			
		

	}

	public int add(User u) {
		
	    SqlSession session = null;
		
		   session = MySqlSession.getSession(); //
		    
		  try{
			  session.selectOne("UserInfo.insert",u);
		  }
		  finally{
			  session.commit();
			  MySqlSession.closeSession();
			 
		  }


			
		  return 1;
			
		 
	}
	
	
	
	
	
	
	
	
	
}
