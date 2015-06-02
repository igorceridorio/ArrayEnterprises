<%-- 
    Document   : index
    Created on : 13/10/2014, 11:35:26
    Author     : daniel
--%>

<%@page import="model.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <title>ERP Jabuti</title>
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
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="ServletMain?tipo=carrega">ERP Jabuti</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <form method="post" action="ValidaLogin" id="formlogout" style="margin-top: 15px;">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:{}" onclick="document.getElementById('formlogout').submit();
                                    return false;">
                                <i class="fa fa-user fa-fw"></i><b><%= login.getLogin()%></b><i class="fa fa-sign-out fa-fw"></i>
                            </a>
                            <input type="hidden" name="tipo" value="logout">
                        </form>       
                        <!-- /.dropdown-user -->
                    </li>
                </ul>
            </nav>
            <div id="page-wrapper" style="margin-left: 0">
                <div id="VendasEfetuadas">
                    <div class="row">
                        <div class="col-lg-9 col-centered" style="margin-top: 10px;">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="row" style="text-align: center;">
                                        <h1 class="page-header">Relatório de Venda</h1>
                                    </div>
                                    <!-- /.row -->
                                    <%
                                        Venda venda = (Venda) request.getAttribute("venda");
                                        Cliente cliente = venda.getCliente();
                                        List<Lote> lotes = venda.getLotes();
                                        if (venda != null) {
                                    %>
                                    <div class="row" style='margin: 10px 10px 10px 10px;'>
                                        <form class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Cód. venda:</label>
                                                <div class="col-sm-2">
                                                    <p class="form-control-static"><%=venda.getCodigo()%></p>
                                                </div>
                                                <label class="col-sm-2 control-label">Cód. cliente:</label>
                                                <div class="col-sm-2">
                                                    <p class="form-control-static"><%=cliente.getCodigo()%></p>
                                                </div>
                                                <label class="col-sm-2 control-label">Cliente:</label>
                                                <div class="col-sm-2">
                                                    <p class="form-control-static"><%=cliente.getNome()%></p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-2"></div>
                                                <div class="col-sm-2"></div>
                                                <label class="col-sm-2 control-label">Ramo:</label>
                                                <div class="col-sm-2">
                                                    <p class="form-control-static"><%=cliente.getRamo()%></p>
                                                </div>
                                                <label class="col-sm-2 control-label">Especialização:</label>
                                                <div class="col-sm-2">
                                                    <p class="form-control-static"><%=cliente.getEsp_ramo()%></p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">Lotes no pedido:</label>
                                                <div class="col-sm-9">
                                                    <div class="well" style="background-color: #fcfcfc;">
                                                        <% for (Iterator iterator = lotes.iterator(); iterator.hasNext();) {
                                                                Lote lote = (Lote) iterator.next();
                                                                Produto pro = lote.getProduto();
                                                        %>
                                                        <div class="form-group">
                                                            <label class="col-sm-3 control-label">Cod. Lote:</label>
                                                            <div class="col-sm-3">
                                                                <p class="form-control-static"><%=lote.getCodigo()%></p>
                                                            </div>
                                                            <label class="col-sm-3 control-label">Cod. Produto:</label>
                                                            <div class="col-sm-3">
                                                                <p class="form-control-static"><%=pro.getCodigo()%></p>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-3 control-label">Nome:</label>
                                                            <div class="col-sm-3">
                                                                <p class="form-control-static"><%=pro.getNome()%></p>
                                                            </div>
                                                            <label class="col-sm-3 control-label">Fabricação:</label>
                                                            <div class="col-sm-3">
                                                                <p class="form-control-static"><%=lote.getDt_fabricacao()%></p>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-3 control-label">Validade:</label>
                                                            <div class="col-sm-3">
                                                                <p class="form-control-static"><%=lote.getDt_validade()%></p>
                                                            </div>
                                                            <label class="col-sm-3 control-label">Qtde. pedido:</label>
                                                            <div class="col-sm-3">
                                                                <p class="form-control-static"><%=lote.getQtde_pedido()%></p>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-3 control-label">Valor unitario:</label>
                                                            <div class="col-sm-3">
                                                                <p class="form-control-static">R$ <%=pro.getPreco_unit()%></p>
                                                            </div>
                                                            <label class="col-sm-3 control-label">Valor total:</label>
                                                            <div class="col-sm-3">
                                                                <p class="form-control-static">R$ <%= (pro.getPreco_unit()*lote.getQtde_pedido()) %></p>
                                                            </div>
                                                        </div>
                                                        <%
                                                            if (iterator.hasNext()) {
                                                        %>
                                                        <hr>
                                                        <%  }
                                                            } %>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">Valor total do pedido:</label>
                                                <div class="col-sm-3"><p class="form-control-static">R$ <%=venda.getPreco_total()%></p></div>
                                            </div>
                                    </div>
                                    <% }%>
                                    <a href="ServletMain?tipo=carrega" class="btn btn-primary pull-right" style="margin-top: -20px;">Voltar</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3"></div>
                    </div>
                </div>
            </div>
            <!-- /#wrapper -->
    </body>
</html>
