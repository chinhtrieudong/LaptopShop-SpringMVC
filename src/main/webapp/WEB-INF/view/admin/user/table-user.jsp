<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <title>Document</title>
                <!-- <link rel="stylesheet" href="/css/style.css"> -->
            </head>

            <body>
                <!-- <h1>${users}</h1> -->
                <div class="container mt-3">
                    <div class="row">

                        <div class="d-flex justify-content-between mt-5 ">
                            <h3>Table users</h3>
                            <a href="/admin/user/create">
                                <btn class="btn btn-primary">Create user</btn>
                            </a>
                        </div>
                        <hr />
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Full name</th>
                                    <th scope="col">Password</th>
                                    <th scope="col">Phone</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr>
                                        <th scope="row">${user.id}</th>
                                        <th scope="row">${user.address}</th>
                                        <td>${user.email}</td>
                                        <td>${user.fullName}</td>
                                        <td>${user.password}</td>
                                        <td>${user.phone}</td>
                                        <td>
                                            <btn class="btn btn-success">View</btn>
                                            <btn class="btn btn-warning">Update</btn>
                                            <btn class="btn btn-danger">Delete</btn>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                </div>
            </body>

            </html>