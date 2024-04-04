<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Dashboard - Hỏi Dân IT</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manage Products</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Product detail</li>
                                </ol>
                                <div class="mt-5">
                                    <div class="row ml-0">
                                        <div class="d-flex justify-content-between mt-5 mb-3 ">
                                            <h3>Product detail with id = ${productInfo.id}</h3>
                                        </div>
                                        <hr>

                                        <div class="card" style="width: 60%;">
                                            <img src="/images/product/${productInfo.image}" class="card-img-top"
                                                alt="Card image cap">

                                            <div class="card-header">
                                                <h4> Product infomation:</h4>
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">ID: ${productInfo.id}</li>
                                                <li class="list-group-item">Name: ${productInfo.name}</li>
                                                <li class="list-group-item">Price: ${productInfo.price}</li>
                                                <li class="list-group-item">Quantity: ${productInfo.quantity}</li>
                                                <li class="list-group-item">Description: ${productInfo.detailDesc}</li>
                                                <li class="list-group-item">Sold: ${productInfo.sold}</li>
                                                <li class="list-group-item">Factory: ${productInfo.factory}</li>
                                            </ul>
                                        </div>

                                    </div>

                                    <a href="/admin/product" class="btn btn-success mt-3 ">Back</a>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />

                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>