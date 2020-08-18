

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//기본으로 인덱스 화면 띄우면 30퍼줌. 딜리트 성공하면 60플. 인서트와 업데이트 하면 100.
@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
	UserDao ud = new UserDaoImpl();
	ClassesDao cd = new ClassesDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		if (type.equals("all")) {
			doAll(request, response);
		} else if (type.equals("del")) {
			doDel(request, response);
		} else if (type.equals("upd")) {
			doUpd(request, response);
		} else if (type.equals("add")) {
			doAdd(request, response);
		}
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("clsList" , cd.all());
	    request.getRequestDispatcher("insert.jsp").forward(request, response);
	    
	    
	     PageUtil<User> page = new PageUtil<User>();
    	 ud.selectAll(page);

	     User u = new User();
	     Classes clss = new Classes();
	    

	     int id = page.getCount()+1;
	     String name = request.getParameter("name");
	     String gender = request.getParameter("gender");
	     String email = request.getParameter("email");
	     int clsid = Integer.parseInt( request.getParameter("clsId"));
	     
	     if(clsid==1){
	    	 clss.setId(1);
	    	 clss.setName("S1");
	     }
	     else if(clsid==2){
	    	 clss.setId(2);
	    	 clss.setName("S2");
	     }
	     else{
	    	 clss.setId(3);
	    	 clss.setName("Y1");
	     }
	    
	     
	     u.setId(id);
	    System.out.println(u.getId());

	     u.setName(name);
	     System.out.println(u.getName());
	 
	     u.setGender(gender);
	     System.out.println(u.getGender());
	     u.setEmail(email);
	     System.out.println(u.getEmail());
	     u.setCls(clss);
	     System.out.println(clss.getId());
	     
	   
	     
	     int chk = ud.add(u);

	     
	     if(chk==1){
		
				page.setIndex(id);

				

	    	 ud.selectAll(page);
		
			request.setAttribute("page", page); //이 아래는 화면에 뿌려야 하는 정보를 전송해주는 역할. 화면에 request를 보냄. 마지막엔 jsp 띄움.
			request.setAttribute("index", page.getIndex());
			request.setAttribute("sum", page.pageCount(page.getCount(), page.getSize()));
			request.getRequestDispatcher("loading.jsp").forward(request, response);
			
	}
	}

	public void doUpd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//스톱해서널인지 값 있는지 확인. 액션분리해서 없으면 있으
		request.setAttribute("clsList" , cd.all());
	    request.getRequestDispatcher("update.jsp").forward(request, response);
	    
	    

	     User u = new User();
	     Classes clss = new Classes();
	     
		 

	     
	     
	     PageUtil<User> page = new PageUtil<User>();

	     
	     int id = Integer.parseInt(request.getParameter("id"));
	     String name = request.getParameter("name");
	     String gender = request.getParameter("gender");
	     String email = request.getParameter("email");
	     int clsid = Integer.parseInt( request.getParameter("clsId"));
	     
	     if(clsid==1){
	    	 clss.setId(1);
	    	 clss.setName("S1");
	     }
	     else if(clsid==2){
	    	 clss.setId(2);
	    	 clss.setName("S2");
	     }
	     else{
	    	 clss.setId(3);
	    	 clss.setName("Y1");
	     }
	   
	     
	     
	     
	     u.setId(id);
		System.out.println(u.getId());

	     u.setName(name);
	     System.out.println(u.getName());
	 
	      u.setGender(gender);
	   System.out.println(u.getGender());
	     u.setEmail(email);
	     System.out.println(u.getEmail());
	     u.setCls(clss);
	     System.out.println(clss.getId());
	     
	   
	     int chk = ud.upd(u);
	     
	     if(chk==1){
		
			page.setIndex(id);


	    	 ud.selectAll(page);
		
			request.setAttribute("page", page); //이 아래는 화면에 뿌려야 하는 정보를 전송해주는 역할. 화면에 request를 보냄. 마지막엔 jsp 띄움.
			request.setAttribute("index", page.getIndex());
			request.setAttribute("sum", page.pageCount(page.getCount(), page.getSize()));
			request.getRequestDispatcher("loading.jsp").forward(request, response);
			
	     }
	}

	

	public void doDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//아이디를 받아옴.그 후에 딜리트 함수 호출.호출시 아이디 전송함 
		//그 후에 밑에 부분은 doAll에서와 같다. 
		//아마 아래처럼 복붙.
				
		//int id=1;
		//String strs = request.getParameter("index");
		//ud.del(id);
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		//System.out.println(id);
		PageUtil<User> page = new PageUtil<User>();
	//	page.setIndex(id);
		
		int chk = ud.del(id);
		
		if(chk==1){
			page.setIndex(id);
	
			ud.selectAll(page);
				
			request.setAttribute("page", page); //이 아래는 화면에 뿌려야 하는 정보를 전송해주는 역할. 화면에 request를 보냄. 마지막엔 jsp 띄움.
			request.setAttribute("index", page.getIndex());
			request.setAttribute("sum", page.pageCount(page.getCount(), page.getSize()));
			request.getRequestDispatcher("loading.jsp").forward(request, response);
				
		}
	}

	public void doAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int index = 1;
		String strs = request.getParameter("index");
		if (strs != null) {
			index = Integer.parseInt(strs);
		}
		PageUtil<User> page = new PageUtil<User>();
		page.setIndex(index);
		
		ud.selectAll(page);//어플리케이션 호출 부분. 그러면 서버에서 뿌릴 정보가 담긴 테이블 넘어옴. 그럼 브라우저에다가 받은 테이블을 출력.
		request.setAttribute("page", page); //이 아래는 화면에 뿌려야 하는 정보를 전송해주는 역할. 화면에 request를 보냄. 마지막엔 jsp 띄움.

		request.setAttribute("index", page.getIndex());
		request.setAttribute("sum", page.pageCount(page.getCount(), page
				.getSize()));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}//브라우저내용ㅇㄹ 파라미터로 받아옴 url을 통해서.  앞은 파라미터를 받아오는 부분.(request) / 어플리케이션 호출 부
}