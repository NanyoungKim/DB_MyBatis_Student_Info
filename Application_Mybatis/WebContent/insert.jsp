<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Students info System</title>
		<style type="text/css">
input {
	border: 0px;
}
</style>
	</head>
	<script type="text/javascript">
	function 
	back() {
		var name = document.getElementById("name").value;
		var email = document.getElementById("email").value;
		if (name == "") {
			alert("can not be empty?");
			return false;
		} else if (email == "") {
			alert("can not be empty");
			return false;
		} else {
			return true;
		}
	}
</script>
	<body>
		<h2 align="center">
			Add-[
			<a href="loading.jsp">Return</a>]
		</h2>
		<form action="UserServlet?type=add" method="post">
			<table border="1" cellspacing="0" align="center">
				<tr>
					<td>
						Name
					</td>
					<td>
						<input type="text" name="name" id="name" />
					</td>
				</tr>
				<tr>
					<td>
						Gender
					</td>
					<td>
						<input type="radio" name="gender" value="男" checked="checked" />
						M
						<input type="radio" name="gender" value="女" />
						F
					</td>
				</tr>
				<tr>
					<td>
						Mail
					</td>
					<td>
						<input type="text" name="email" id="email" />
					</td>
				</tr>
				<tr>
					<td>
						Class
					</td>
					<td>
						<select name="clsId">
							<c:forEach var="c" items="${clsList}">
								<option value="${c.id}">
									${c.name}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="submit" onclick="return back();" />
						<input type="reset" value="reset" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
