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
                <div class="container mt-3">
                    <div class="row">
                        <div class="col-md-6 col-12 align-items-center mx-auto">
                            <form:form method="post" action="/admin/user/create1" modelAttribute="newUser">
                                <h1 class="border border-0 border-bottom-1">Create a user</h1>
                                <hr>
                                <div class="mb-3 mt-3">
                                    <label for="email" class="form-label">Email:</label>
                                    <form:input type="email" class="form-control" id="email" path="email"
                                        name="email" />
                                </div>
                                <div class="mb-3">
                                    <label for="pwd" class="form-label">Password:</label>
                                    <form:input type="password" class="form-control" id="pwd" path="password"
                                        name="pswd" />
                                </div>
                                <div class="mb-3">
                                    <label for="pwd" class="form-label">Phone number:</label>
                                    <form:input type="text" class="form-control" id="pwd" path="phone" name="pswd" />
                                </div>
                                <div class="mb-3">
                                    <label for="pwd" class="form-label">Full name:</label>
                                    <form:input type="text" class="form-control" id="pwd" path="fullName" name="pswd" />
                                </div>
                                <div class="mb-3">
                                    <label for="pwd" class="form-label">Address:</label>
                                    <form:input type="text" class="form-control" id="pwd" path="address" name="pswd" />
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>