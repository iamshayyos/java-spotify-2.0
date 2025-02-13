<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Song Form</title>
</head>
<body>
    <h2>Song Form</h2>
    <form:form action="save" modelAttribute="song">
        <input type="hidden" name="songID" value="${song.songID}" />
        Name: <form:input path="name" /> <br/>
        Artist: <form:input path="artistName" /> <br/>
        Genre: <form:input path="genre" /> <br/>
        Length: <form:input path="length" /> <br/>
        <input type="submit" value="Save" />
    </form:form>
</body>
</html>
