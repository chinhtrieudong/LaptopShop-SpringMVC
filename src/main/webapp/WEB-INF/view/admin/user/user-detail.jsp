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
                <title>User detail</title>
                <!-- <link rel="stylesheet" href="/css/style.css"> -->
            </head>

            <body>
                <!-- <h1>${users}</h1> -->
                <div class="container mt-3">
                    <div class="row ml-0">
                        <div class="d-flex justify-content-between mt-5 ">
                            <h3>User detail with id = ${userInfo.id}</h3>
                        </div>

                        <div class="card  " style="width: 60%;">
                            <div class="card-header">
                                <h4> User infomation</h4>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Address: ${userInfo.address}</li>
                                <li class="list-group-item">Email: ${userInfo.email}</li>
                                <li class="list-group-item">Phone number: ${userInfo.phone}</li>
                            </ul>
                        </div>

                    </div>

                    <a href="/admin/user" class="btn btn-success mt-3 ">Back</a>
                </div>
                </div>
            </body>

            </html>