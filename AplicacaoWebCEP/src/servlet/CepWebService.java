package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/api/consultar")
public class CepWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {       
        String cep = request.getParameter("cep");
        if (cep == null || cep.trim().isEmpty()) {
            response.setStatus(400); 
            response.getWriter().write("{\"erro\": \"CEP nao informado\"}");
            return;
        }

        try {
            String viaCepUrl = "https://viacep.com.br/ws/" + cep + "/json";
            HttpURLConnection con = (HttpURLConnection) new URL(viaCepUrl).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String dados = "";
            String linha;

            while ((linha = br.readLine()) != null) {
                dados += linha;
            }
            con.disconnect();

            //resposta como JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");

            //devolve o JSON
            response.getWriter().write(dados);
            
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"erro\": \"Erro ao buscar o CEP\"}");
        }
    }
}
