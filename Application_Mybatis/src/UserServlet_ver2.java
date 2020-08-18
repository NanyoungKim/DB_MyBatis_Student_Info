package com.zdq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdq.dao.ClassesDao;
import com.zdq.dao.UserDao;
import com.zdq.dao.impl.ClassesDaoImpl;
import com.zdq.dao.impl.UserDaoImpl;
import com.zdq.entiy.Classes;
import com.zdq.entiy.User;
import com.zdq.util.PageUtil;

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
		PrintWriter out = response.getWriter();
		String stop = request.getParameter("stop");
		if (stop != null) {
			List<Classes> list = cd.all();
			request.setAttribute("clsList", list);
			request.getRequestDispatcher("insert.jsp").forward(request,
					response);
		} else {
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			int clsId = Integer.parseInt(request.getParameter("clsId"));
			User u = new User();
			u.setName(name);
			u.setSex(sex);
			u.setEmail(email);
			u.getCls().setId(clsId);
			int num = ud.add(u);
			if (num != 0) {
				out
						.print("<script type='text/javascript'>alert('保存成功!');location.href='loading.jsp';</script>");
			} else {
				out
						.print("<script type='text/javascript'>alert('保存失败!');location.href='loading.jsp';</script>");
			}
		}
	}

	public void doUpd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String stop = request.getParameter("stop");
		if (stop != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			User u = ud.ById(id);
			List<Classes> list = cd.all();
			request.setAttribute("u", u);
			request.setAttribute("clsList", list);
			request.getRequestDispatcher("update.jsp").forward(request,
					response);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			int clsId = Integer.parseInt(request.getParameter("clsId"));
			User u = new User();
			u.setId(id);
			u.setName(name);
			u.setSex(sex);
			u.setEmail(email);
			u.getCls().setId(clsId);
			int num = ud.upd(u);
			if (num != 0) {
				out
						.print("<script type='text/javascript'>alert('修改成功!');location.href='loading.jsp';</script>");
			} else {
				out
						.print("<script type='text/javascript'>alert('修改失败!');location.href='loading.jsp';</script>");
			}
		}
	}

	public void doDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int num = ud.del(id);
		if (num != 0) {
			out
					.print("<script type='text/javascript'>alert('删除成功!');location.href='loading.jsp';</script>");
		} else {
			out
					.print("<script type='text/javascript'>alert('删除失败!');location.href='loading.jsp';</script>");
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
		ud.selectAll(page);
		request.setAttribute("page", page);
		request.setAttribute("index", page.getIndex());
		request.setAttribute("sum", page.pageCount(page.getCount(), page
				.getSize()));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}