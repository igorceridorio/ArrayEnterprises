<%-- 
    Document   : index
    Created on : 07/10/2014, 10:11:26
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

<head>
    <title>ERP Jabuti</title>
    <%@page import="model.Usuario"%>
    <%@include file="WEB-INF/jspf/head.jspf"%>
</head>

<body>
    <%//recupera a sessao
        HttpSession s = request.getSession(false);
        Usuario login = null;
        if (s != null) {
            login = (Usuario) s.getAttribute("Usuario");
        }
    %>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Login</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" method="post" action="ValidaLogin">
                            <input type="hidden" name="tipo" value="login">
                            <fieldset>
                                <div class="form-group">
                                    <% if( login != null && !login.getAutorizado() ) { %>
                                    <div class="alert alert-danger" role="alert"><b>Usuario ou senha invalidos!</b></div>
                                    <%  } %>
                                    <input class="form-control" placeholder="Entre com seu ID" name="login" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Senha" name="senha" type="password" value="">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Entrar">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery Version 1.11.0 -->
    <script src="js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>

</body>

</html>
