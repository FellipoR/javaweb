<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BuscaCEP | Consulte um endereço</title>
<link rel="stylesheet" href="assets/styles.css">
</head>
<body>
<main class="page-shell">
    <section class="hero-card">
        <div class="brand-mark" aria-hidden="true">⌖</div>
        <p class="eyebrow">CONSULTA DE ENDEREÇO</p>
        <h1>Encontre um endereço pelo CEP</h1>
        <p class="subtitle">Digite o CEP para consultar rua, bairro, cidade e estado em poucos segundos.</p>
        <form class="search-form" action="ConsultaServlet" method="post">
            <label for="cep">CEP</label>
            <div class="input-row">
                <input type="text" id="cep" name="cep" inputmode="numeric"
                       maxlength="9" placeholder="00000-000" autocomplete="postal-code"
                       pattern="[0-9]{5}-?[0-9]{3}" required>
                <button type="submit">Consultar <span aria-hidden="true">→</span></button>
            </div>
            <small>Informe os 8 dígitos do CEP, com ou sem hífen.</small>
        </form>
    </section>
    <footer>Dados fornecidos por <a href="https://viacep.com.br/" target="_blank" rel="noopener">ViaCEP</a></footer>
</main>
<script>
document.getElementById("cep").addEventListener("input", function (event) {
    var digits = event.target.value.replace(/\D/g, "").slice(0, 8);
    event.target.value = digits.length > 5 ? digits.slice(0, 5) + "-" + digits.slice(5) : digits;
});
</script>
</body>
</html>
