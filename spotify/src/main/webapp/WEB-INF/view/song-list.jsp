<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Songs List</title>
</head>
<body>
    <h2>All Songs</h2>
    <a href="add">Add New Song</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Artist</th>
            <th>Genre</th>
            <th>Length</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="song" items="${songs}">
            <tr>
                <td>${song.songID}</td>
                <td>${song.name}</td>
                <td>${song.artistName}</td>
                <td>${song.genre}</td>
                <td>${song.length}</td>
                <td>
                    <a href="edit/${song.songID}">Edit</a> |
                    <a href="delete/${song.songID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
