<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <link rel="stylesheet" type="text/css" href="../css/datatable.css">
      <link rel="stylesheet" href="../css/menu.css">
         <link rel="stylesheet" href="../css/main.css">
          <link rel="stylesheet" href="../css/sidebar.css">
          <link rel="stylesheet" href="../css/searchbar.css">
              <link rel="stylesheet" href="../css/vehicleinspection.css">
    <title>Carchek</title>
</head>
<body>
<jsp:include page="../menu.jsp" />
<div class="container">
<jsp:include page="../sidebar.jsp" />
<div class="content">
<jsp:include page="../searchbar.jsp" />
    <div class="tableContainer">
        <table class="table">
            <tr>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Data</th>
                <th class="lastCell">Scadenza</th>
            </tr>
            <tr>
                <td class="mainCell">Milo</td>
                <td>
                    <div class='statusCell processing'>
                        Processing
                    </div>
                </td>
                <td>10/10/12</td>
                <td>si</td>
            </tr>
            <tr>
                <td>Lio</td>
                <td>
                    <div class='statusCell approved'>
                        Approved
                    </div>
                </td>
                <td>10/10/12</td>
                <td>Amy Shanks</td>
            </tr>
        </table>
    </div>
    </div>
  </div>
</body>

</html>