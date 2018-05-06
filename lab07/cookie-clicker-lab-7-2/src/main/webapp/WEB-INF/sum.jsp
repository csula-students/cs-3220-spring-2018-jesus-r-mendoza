<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
</head>
<body>
	<h1>${test}</h1>
	<c:out value="hello world in JSTL" />
	<ol>
		<c:forEach items="${list}" var="number">
			<li>${number}</li>
		</c:forEach>
	</ol>
	<p>Welcome ${user.getPassword()}</p>
	<form method="post">
		<input type="text" name="a">
		<input type="text" name="b">
		<button>Send</button>
		<button type="button">Don't click me</button>
	</form>
</body>
</html>
