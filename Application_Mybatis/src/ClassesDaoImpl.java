

import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class ClassesDaoImpl implements ClassesDao {

	public List<Classes> all() {
		SqlSession session = MySqlSession.getSession();
		List<Classes> list = session.selectList("ClassesInfo.all");
		MySqlSession.closeSession();
		return list;
	}

}
