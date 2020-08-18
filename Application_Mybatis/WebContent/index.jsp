<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Students Info System</title>
	</head>
	<body>
		<h2 align="center">
			Management-[
			<a href="UserServlet?type=add&stop=a">Add</a>]
		</h2>
		<table border="1" cellspacing="0" align="center" id="tables">
			<tr>
				<td>
					ID
				</td>
				<td>
					Name
				</td>
				<td>
					Gender
				</td>
				<td>
					Mail
				</td>
				<td>
					Class
				</td>
				<td>
					Action
				</td>
			</tr>
			<c:forEach var="u" items="${page.list}">
				<tr>
					<td>
						${u.id}
					</td>
					<td>
						${u.name}
					</td>
					<td>
						${u.gender}
					</td>
					<td>
						${u.email}
					</td>
					<td>
						${u.cls.name}
					</td>
					<td>
						<a href="UserServlet?type=del&id=${u.id}">Del</a>&nbsp;
						<a href="UserServlet?type=upd&stop=a&id=${u.id}">Update</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<script type="text/javascript">
	function back() {
		var trs = document.getElementById("tables").getElementsByTagName("tr");
		for ( var i = 0; i < trs.length; i++) {
			if (i % 2 == 0) {
				trs[i].style.backgroundColor = "red";
			}
		}
	}
	back();
</script>
		<h5 align="center">
			<c:if test="${page.index > 1}">
				<a href="UserServlet?type=all&index=1">Home</a>&nbsp;<a
					href="UserServlet?type=all&index=${index - 1}">Up</a>&nbsp;
			</c:if>
			<c:if test="${page.index < sum}">
				<a href="UserServlet?type=all&index=${index + 1}">Down</a>&nbsp;<a
					href="UserServlet?type=all&index=${sum}">End</a>
			</c:if>
		</h5>
	</body>
</html>
