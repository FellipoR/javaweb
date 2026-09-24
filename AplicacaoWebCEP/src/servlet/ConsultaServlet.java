package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Consulta;

@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cep = request.getParameter("cep");
		HttpSession session = request.getSession();
		String digits = cep == null ? "" : cep.replaceAll("\\D", "");

		if (!digits.matches("\\d{8}")) {
			session.setAttribute("erro", "Digite um CEP válido com 8 dígitos.");
		} else {
			String result = Consulta.consultarCEP(digits);
			if (result == null) {
				session.setAttribute("erro", "Não foi possível consultar o CEP agora. Tente novamente.");
			} else {
				session.setAttribute("res", result);
			}
		}
		response.sendRedirect("paginas/res.jsp");
	}
}
