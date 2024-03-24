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
                <title>Create user</title>
                <!-- <link rel="stylesheet" href="/css/style.css"> -->
            </head>

            <body>
                <div class="container mt-3">
                    <div class="row">
                        <div class="mt-5 ">
                            <h3>Table users</h3>
                        </div>
                        <div class="alert alert-danger" role="alert">
                            Are your sure about delete user!
                        </div>
                    </div>
                    <form:form method="post" modelAttribute="newUser" action="/admin/user/delete">
                        <div class="mb-3 d-none ">
                            <label class="form-label">Id:</label>
                            <form:input type="text" class="form-control" path="id" />
                        </div>
                        <button class="btn btn-primary">Confirm</button>
                    </form:form>
                </div>
            </body>

            </html>