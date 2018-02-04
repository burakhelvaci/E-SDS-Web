<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<c:if test="${empty sessionScope.admin}"><c:redirect url = "/account"/></c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <title>Mesaj</title>
</head>

<body>
    <jsp:include page="navbar.jsp"/>
<div class="container">
    <br/><br/>
    <div class="col-md-6 offset-md-3">
        <form class="form-group" action="<s:url value="/messenger/sendmessage"></s:url>" method="post">
                <div class="card">
                    <div class="card-header"><h4>Mesaj Gönder</h4></div>
                    <div class="card-body">
                        <label for="topic">Alıcılar:</label>
                        <select class="form-control" name="topic">
                            <option value="esds">Herkes</option>
                            <c:if test="${not empty personnelListForVisit}">  
                                <c:forEach var="item" items="${personnelListForVisit}">
                                    <option value="${item.getUserName()}">${item.getName()}</option>
                                </c:forEach>
                            </c:if>
                        </select>

                    <label for="message" style="margin-top: 15px;">Mesaj:</label>
                    <textarea class="form-control" id="adress" style="resize: none; height: 150px;" name="message" required></textarea>

                </div> 
                <div class="card-footer">
                    <button class="btn btn-primary pull-right" style="width: 80px; cursor: pointer;">Gönder</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
