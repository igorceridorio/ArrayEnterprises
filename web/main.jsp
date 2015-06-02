<%-- 
    Document   : index
    Created on : 07/10/2014, 10:11:26
    Author     : daniel
--%>

<%@page import="model.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <div class="modal fade" id="modalLimite" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span></button>
                        <h4 class="modal-title">Alterar limite de dias próximo à validade para a venda automática.</h4>
                    </div>
                    <div class="modal-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>Produto</th>
                                        <th>Limite de dias</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Produto> produtos = (List<Produto>) request.getAttribute("listProdutos");
                                        if (!produtos.isEmpty()) {
                                            for (Iterator iterator = produtos.iterator(); iterator.hasNext();) {
                                                Produto produto = (Produto) iterator.next();
                                    %>
                                    <tr>
                                        <td><%=produto.getNome()%></td>
                                        <td>
                                            <input type="number" min="0" style="width: 50px; margin-left: 25px;" value='<%=produto.getLimite()%>' id='<%=produto.getCodigo()%>'>
                                            <button type="button" class="btn btn-sm btn-success pull-right salvaLimite">Salvar alteração</button>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <div class="modal fade" id="modalLimiteAlterado" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span></button>
                        <h4 class="modal-title">Limite de dias próximo à validade para a venda automática.</h4>
                    </div>
                    <div class="modal-body">
                        <div class="table-responsive">
                            <div class="alert alert-success" role="alert">Limite alterado com sucesso!</div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <div id="wrapper">
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <div id="page-wrapper">
                <!--
                  --
                  -- Area de vendas concluidas
                  --
                  -->
                <div id="VendasEfetuadas">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Vendas Efetuadas</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>#Código</th>
                                            <th>Cliente</th>
                                            <th>Ramo</th>
                                            <th>Espcialização</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            List<Venda> vendas = (List<Venda>) request.getAttribute("listVendas");
                                            if (!vendas.isEmpty()) {
                                                for (Iterator iterator = vendas.iterator(); iterator.hasNext();) {
                                                    Venda venda = (Venda) iterator.next();
                                                    Cliente cli = venda.getCliente();
                                        %>
                                        <tr>
                                            <td><%=venda.getCodigo()%><a href="ServletVendas?tipo=visualiza&cod=<%=venda.getCodigo()%>" class="btn btn-primary btn-xs pull-right">Visualizar venda</a></td>
                                            <td><%=cli.getNome()%></td>
                                            <td><%=cli.getRamo()%></td>
                                            <td><%=cli.getEsp_ramo()%></td>
                                            <td>R$ <%=venda.getPreco_total()%></td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!--
                  --
                  -- Area de vendas automaticas
                  --
                  -->
                <div id="NovaVenda">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Nova Venda Automatica  <button class="btn btn-info pull-right" data-toggle="modal" data-target="#modalLimite" style="text-align: left;"><i class="glyphicon glyphicon-cog"></i> Limite de dias</button></h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="col-lg-6">
                                    <div class="pull-left" style="margin-top: 7px;"><b>Cliente:</b></div>
                                    <select class="form-control" style="width: 200px;" id="SelCliente">
                                        <option value="-1">Selecione um cliente</option>
                                        <%
                                            List<Cliente> clientes = (List<Cliente>) request.getAttribute("listClientes");
                                            if (!clientes.isEmpty()) {
                                                for (Iterator iterator = clientes.iterator(); iterator.hasNext();) {
                                                    Cliente cli = (Cliente) iterator.next();
                                        %>
                                        <option value="<%=cli.getCodigo()%>"><%=cli.getNome()%></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-lg-6 alert alert-warning" role="alert" id="AlertVenda" style="margin : 0;">
                                    <h4>É necessário selecionar o cliente e ao menos um produto para realizar uma venda. =)</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="panelVendas">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Produtos</h3>
                                </div>
                                <div class="panel-body">
                                    <table class="display" id="TableProdutos">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>Produto</th>
                                                <th>Valor</th>
                                                <th>Qtde. disponível</th>
                                                <th>Sel. quantidade</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Produto> produtosV = (List<Produto>) request.getAttribute("listProdutosV");
                                                if (!produtosV.isEmpty()) {
                                                    for (Iterator iterator = produtosV.iterator(); iterator.hasNext();) {
                                                        Produto produto = (Produto) iterator.next();
                                            %>
                                            <tr class="gradeA <%=produto.getRamo()%>">
                                                <td><%=produto.getCodigo()%></td>
                                                <td><%=produto.getNome()%></td>
                                                <td>R$ <%=produto.getPreco_unit()%></td>
                                                <td><%=produto.getQuantidade()%></td>
                                                <td>
                                                    <input type="number" id="QtdeProd" min="0" max="<%=produto.getQuantidade()%>" value="1" style="width: 50px;">
                                                    <button type="button" class="btn btn-primary btn-xs pull-right BotaoAdd">>></button>
                                                </td>
                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Itens na Venda</h3>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped table-hover" id="TabelaVenda">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>Produto</th>
                                                <th>Qtde.</th>
                                                <th>Lote</th>
                                                <th>Fabricação</th>
                                                <th>Validade</th>
                                                <th>Valor</th>
                                            </tr>
                                        </thead>
                                        <tbody id="ProdutosVenda">
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label>Valor total: <p class='btn btn-info'id="total">0</p></label>
                                            <input type="hidden" id="flagTotal" value="0">
                                            <input type="hidden" id="flagProduto" value="0">
                                            <button class="btn btn-success pull-right" id="FinalizaVenda" disabled>Finalizar Venda</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--
                  --
                  -- Area de vendas manuais por lote
                  --
                  -->
                <div id="NovaVendaM">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Nova Venda Manual</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="col-lg-6">
                                    <div class="pull-left" style="margin-top: 7px;"><b>Cliente:</b></div>
                                    <select class="form-control" style="width: 200px;" id="SelClienteM">
                                        <option value="-1">Selecione um cliente</option>
                                        <%
                                            clientes = (List<Cliente>) request.getAttribute("listClientes");
                                            if (!clientes.isEmpty()) {
                                                for (Iterator iterator = clientes.iterator(); iterator.hasNext();) {
                                                    Cliente cli = (Cliente) iterator.next();
                                        %>
                                        <option value="<%=cli.getCodigo()%>"><%=cli.getNome()%></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-lg-6 alert alert-warning" role="alert" id="AlertVendaM" style="margin : 0;">
                                    <h4>É necessário selecionar o cliente e ao menos um produto para realizar uma venda. =)</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="panelVendasM">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Produtos</h3>
                                </div>
                                <div class="panel-body">
                                    <table class="display" id="TableProdutosM">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>Produto</th>
                                                <th>Lote</th>
                                                <th>Val.</th>
                                                <th>Valor</th>
                                                <th>Qtde. disponível</th>
                                                <th>Sel. quantidade</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Lote> lotes = (List<Lote>) request.getAttribute("listLotes");
                                                if (!lotes.isEmpty()) {
                                                    for (Iterator iterator = lotes.iterator(); iterator.hasNext();) {
                                                        Lote lote = (Lote) iterator.next();
                                                        Produto prod = lote.getProduto();
                                            %>
                                            <tr class="gradeA <%=prod.getRamo()%>">
                                                <td><%=prod.getCodigo()%></td>
                                                <td><%=prod.getNome()%></td>
                                                <td><%=lote.getCodigo()%></td>
                                                <td><%=lote.getDt_validade()%></td>
                                                <td>R$ <%=prod.getPreco_unit()%></td>
                                                <td><%=lote.getQtde_atual()%></td>
                                                <td>
                                                    <input type="number" id="QtdeProdM" min="0" max="<%=lote.getQtde_atual()%>" value="1" style="width: 50px;">
                                                    <button type="button" class="btn btn-primary btn-xs pull-right BotaoAddM">>></button>
                                                </td>
                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Itens na Venda</h3>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped table-hover" id="TabelaVendaM">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>Produto</th>
                                                <th>Qtde.</th>
                                                <th>Lote</th>
                                                <th>Fabricação</th>
                                                <th>Validade</th>
                                                <th>Valor</th>
                                            </tr>
                                        </thead>
                                        <tbody id="ProdutosVendaM">
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label>Valor total: <p class='btn btn-info'id="totalM">0</p></label>
                                            <input type="hidden" id="flagTotalM" value="0">
                                            <input type="hidden" id="flagProdutoM" value="0">
                                            <button class="btn btn-success pull-right" id="FinalizaVendaM" disabled>Finalizar Venda</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
            if(request.getAttribute("limite").equals("alterado")){
        %>
        <script>$("#modalLimiteAlterado").modal('show');</script>
        <%
            }
        %>
    </body>
</html>
