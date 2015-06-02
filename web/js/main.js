/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#NovaVenda").hide(0.0000001);
    $("#NovaVendaM").hide(0.0000001);

    $("#BotaoNovo").click(function () {
        $('#BotaoEfetuadas').removeClass("active");
        $('#BotaoNovoM').removeClass("active");
        $('#BotaoNovo').addClass("active");
        $("#VendasEfetuadas").hide(0.0000001);
        $("#NovaVendaM").hide(0.0000001);
        $('#panelVendas').hide(0.0000001);
        $("#SelCliente").val('-1');
        $('#AlertVenda').fadeIn('slow');
        $("#NovaVenda").fadeIn('slow');
    });
    
    $("#BotaoNovoM").click(function () {
        $('#BotaoEfetuadas').removeClass("active");
        $('#BotaoNovo').removeClass("active");
        $('#BotaoNovoM').addClass("active");
        $("#VendasEfetuadas").hide(0.0000001);
        $("#NovaVenda").hide(0.0000001);
        $('#panelVendasM').hide(0.0000001);
        $("#SelClienteM").val('-1');
        $('#AlertVendaM').fadeIn('slow');
        $("#NovaVendaM").fadeIn('slow');
    });

    $("#BotaoEfetuadas").click(function () {
        $('#BotaoNovo').removeClass("active");
        $('#BotaoNovoM').removeClass("active");
        $('#BotaoEfetuadas').addClass("active");
        $("#NovaVenda").hide(0.0000001);
        $("#NovaVendaM").hide(0.0000001);
        $("#VendasEfetuadas").fadeIn('slow');
    });
    
    $(".BotaoAdd").click(function () {
        var cod = $(this).parent().prev().prev().prev().prev().html();
        var qtde = $(this).prev().val();
        var nome = $(this).parent().prev().prev().prev().html();
        var aux = $(this).parent().prev().prev().html().split(' ');
        var preco = parseFloat(aux[1]);
        var total = parseFloat($('#total').html());
        var app;
        
        var max = $(this).prev().attr("max");
        $(this).prev().attr("max",max - qtde);
        
        $.ajax({
            url:  "ServletLote",
            type: 'post',
            data: {'codigo':cod, 'quantidade':qtde, 'tipo':'add'},
            success: function(data, textStatus, jqXHR) {
                data.trim();
                var arr = data.split('#');
                if(arr.length == 5){
                    app = "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+arr[4]+"</td><td>"+arr[1]+"</td><td>"+arr[2]+"</td><td>"+arr[3]+"</td><td>"+(preco*parseFloat(arr[4]))+"</td></tr>";
                    total = total + (preco*parseFloat(arr[4]));
                    $('#total').html(total);
                    $('#flagTotal').val(total);
                }else{
                    i = 1;
                    while(i < arr.length){
                        if((i+4) >= arr.length ) {
                            app = app + "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+qtde+"</td><td>"+arr[i]+"</td><td>"+arr[i+1]+"</td><td>"+arr[i+2]+"</td><td>"+(preco*parseFloat(qtde))+"</td></tr>";
                            total = total + (preco*parseFloat(qtde));
                            $('#total').html(total);
                            $('#flagTotal').val(total);
                        } else {
                            app = app + "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+arr[i+3]+"</td><td>"+arr[i]+"</td><td>"+arr[i+1]+"</td><td>"+arr[i+2]+"</td><td>"+(preco*parseFloat(arr[i+3]))+"</td></tr>";
                            total = total + (preco*parseFloat(arr[i+3]));
                            $('#total').html(total);
                            $('#flagTotal').val(total);
                        }
                        qtde = qtde-arr[i+3];
                        i = i+4;
                    }
                }
                $('#TabelaVenda').children().next().append(app);
            }
        });
        
        $(this).parent().prev().html(max-qtde);       
        
        $('#FinalizaVenda').removeAttr('disabled');
        
    });
    
    $(".BotaoAddM").click(function () {
        var cod = $(this).parent().prev().prev().prev().prev().prev().prev().html();
        var nome = $(this).parent().prev().prev().prev().prev().prev().html();
        var qtde = $(this).prev().val(); 
        var lote = $(this).parent().prev().prev().prev().prev().html();
        var validade = $(this).parent().prev().prev().prev().html();
        var aux = $(this).parent().prev().prev().html().split(' ');
        var preco = parseFloat(aux[1]);
        var total = parseFloat($('#totalM').html());
        var app;
        
        var max = $(this).prev().attr("max");
        $(this).prev().attr("max",max - qtde);
        
        $.ajax({
            url:  "ServletLote",
            type: 'post',
            data: {'codigo':cod, 'quantidade':qtde, 'tipo':'addM'},
            success: function(data, textStatus, jqXHR) {
                
                app = "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+qtde+"</td><td>"+lote+"</td><td>"+data+"</td><td>"+validade+"</td><td>"+(preco*parseFloat(qtde))+"</td></tr>";
                
                total = total + (preco*parseFloat(qtde));
                $('#totalM').html(total);
                $('#flagTotalM').val(total);
                $('#TabelaVendaM').children().next().append(app);
            }
        });
        
        $(this).parent().prev().html(max-qtde);       
        
        $('#FinalizaVendaM').removeAttr('disabled');
        
    });
    
    $("#SelCliente").change(function (){
        $('#ProdutosVenda').children().remove();
        $('#total').html("0");
        $('#flagTotal').val("0");
        $('#FinalizaVenda').attr('disabled','true');
        
        var cli = $("#SelCliente").val();
        
        if (cli !== "-1"){        
            $('#panelVendas').fadeIn('slow');

            $.ajax({
                url:  "ServletMain",
                type: 'get',
                data: {'codigo':cli, 'tipo':'tipo'},
                success: function(data, textStatus, jqXHR) {
                    if (data === '1'){
                        $('.2').hide(0.0000001);
                        $('.1').fadeIn('slow');
                    }
                    if (data === '2'){
                        $('.1').hide(0.0000001);
                        $('.2').fadeIn('slow');
                    }
                }
            });

            $('#AlertVenda').hide(0.0000001);
        } else {
            $('#panelVendas').hide(0.0000001);
            $('#AlertVenda').fadeIn('slow');
        }
    });
    
    $("#SelClienteM").change(function (){
        $('#ProdutosVendaM').children().remove();
        $('#totalM').html("0");
        $('#flagTotalM').val("0");
        $('#FinalizaVendaM').attr('disabled','true');
        
        var cli = $("#SelClienteM").val();
        
        if (cli !== "-1"){        
            $('#panelVendasM').fadeIn('slow');

            $.ajax({
                url:  "ServletMain",
                type: 'get',
                data: {'codigo':cli, 'tipo':'tipo'},
                success: function(data, textStatus, jqXHR) {
                    if (data === '1'){
                        $('.2').hide(0.0000001);
                        $('.1').fadeIn('slow');
                    }
                    if (data === '2'){
                        $('.1').hide(0.0000001);
                        $('.2').fadeIn('slow');
                    }
                }
            });

            $('#AlertVendaM').hide(0.0000001);
        } else {
            $('#panelVendasM').hide(0.0000001);
            $('#AlertVendaM').fadeIn('slow');
        }
    });
    
    $("#FinalizaVenda").click(function () {
        var cli = $('#SelCliente').val();
        var lotes = "";
        var quantidade = "";
        var total = $('#flagTotal').val();
        
        var apontador = $('#TabelaVenda').children().next().children();
        while(apontador.children().length > 0){
            lotes = lotes + apontador.children().next().next().next().html();
            apontador = apontador.next();
            if (apontador.children().length > 0)
                lotes = lotes+"#";
        }
        
        var apontador = $('#TabelaVenda').children().next().children();
        while(apontador.children().length > 0){
            quantidade = quantidade + apontador.children().next().next().html();
            apontador = apontador.next();
            if (apontador.children().length > 0)
                quantidade = quantidade+"#";
        }
        
        $('<form action="ServletVendas" method="POST">' + 
            '<input type="hidden" name="tipo" value="salva">' +
            '<input type="hidden" name="cliente" value="'+cli+'">' +
            '<input type="hidden" name="quantidade" value="'+quantidade+'">' +
            '<input type="hidden" name="lotes" value="'+lotes+'">' +
            '<input type="hidden" name="total" value="'+total+'">' +
            '</form>').submit();
    });
    
    $("#FinalizaVendaM").click(function () {
        var cli = $('#SelClienteM').val();
        var lotes = "";
        var quantidade = "";
        var total = $('#flagTotalM').val();
        
        var apontador = $('#TabelaVendaM').children().next().children();
        while(apontador.children().length > 0){
            lotes = lotes + apontador.children().next().next().next().html();
            apontador = apontador.next();
            if (apontador.children().length > 0)
                lotes = lotes+"#";
        }
        
        var apontador = $('#TabelaVendaM').children().next().children();
        while(apontador.children().length > 0){
            quantidade = quantidade + apontador.children().next().next().html();
            apontador = apontador.next();
            if (apontador.children().length > 0)
                quantidade = quantidade+"#";
        }
        
        $('<form action="ServletVendas" method="POST">' + 
            '<input type="hidden" name="tipo" value="salva">' +
            '<input type="hidden" name="cliente" value="'+cli+'">' +
            '<input type="hidden" name="quantidade" value="'+quantidade+'">' +
            '<input type="hidden" name="lotes" value="'+lotes+'">' +
            '<input type="hidden" name="total" value="'+total+'">' +
            '</form>').submit();
    });

    $('#TableProdutos').dataTable({
        info: false,
        ordering: false,
        paging: false,
        "language": {
            "lengthMenu":     "Mostrar _MENU_ produtos",
            "infoEmpty":      "Não há produtos cadastrados.",
            "search":         "Filtre um produto:"
        }
    });
    
    $('#TableProdutosM').dataTable({
        info: false,
        ordering: false,
        paging: false,
        "language": {
            "lengthMenu":     "Mostrar _MENU_ produtos",
            "infoEmpty":      "Não há produtos cadastrados."
        }
    });
    
    $(".salvaLimite").click(function () {
        var id = $(this).prev().attr('id');
        var limite = $(this).prev().val();
        
        $('<form action="ServletProduto" method="POST">' + 
            '<input type="hidden" name="tipo" value="alteraLimite">' +
            '<input type="hidden" name="id" value="'+id+'">' +
            '<input type="hidden" name="limite" value="'+limite+'">' +
            '</form>').submit();
    });
});