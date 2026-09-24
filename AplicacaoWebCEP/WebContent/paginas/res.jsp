<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String res = (String) session.getAttribute("res");
String erro = (String) session.getAttribute("erro");
session.removeAttribute("res");
session.removeAttribute("erro");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Resultado | BuscaCEP</title>
<link rel="stylesheet" href="../assets/styles.css">
</head>
<body>
<main class="page-shell">
    <section class="result-card">
        <a class="back-link" href="../index.jsp">← Nova consulta</a>
        <p class="eyebrow">RESULTADO DA BUSCA</p>
        <% if (erro != null) { %>
            <div class="alert" role="alert"><%= erro %></div>
        <% } else { %>
            <h1 id="result-title">Endereço encontrado</h1>
            <div id="result-content" class="address-grid">
                <p class="loading">Carregando dados do endereço...</p>
            </div>
            <pre id="raw-result" hidden><%= res == null ? "" :
                res.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") %></pre>
        <% } %>
    </section>
    <footer>Dados fornecidos por <a href="https://viacep.com.br/" target="_blank" rel="noopener">ViaCEP</a></footer>
</main>
<% if (erro == null) { %>
<script>
(function () {
    var raw = document.getElementById("raw-result").textContent;
    var content = document.getElementById("result-content");
    try {
        var data = JSON.parse(raw);
        if (data.erro) {
            document.getElementById("result-title").textContent = "CEP não encontrado";
            content.innerHTML = '<p class="empty-state">Confira os números informados e tente novamente.</p>';
            return;
        }
        var fields = [
            ["Logradouro", data.logradouro], ["Bairro", data.bairro],
            ["Cidade", data.localidade], ["Estado", data.uf],
            ["CEP", data.cep], ["DDD", data.ddd]
        ];
        content.innerHTML = fields.map(function (field) {
            return '<div class="address-field"><span>' + field[0] + '</span><strong>' +
                (field[1] || "Não informado") + '</strong></div>';
        }).join("");
    } catch (error) {
        content.innerHTML = '<p class="empty-state">Não foi possível interpretar o resultado. Tente novamente.</p>';
    }
}());
</script>
<% } %>
</body>
</html>
