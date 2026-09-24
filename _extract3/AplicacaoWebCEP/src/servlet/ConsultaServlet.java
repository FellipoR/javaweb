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

	public ConsultaServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cep = request.getParameter("cep");
		String res = Consulta.consultarCEP(cep);
		HttpSession session = request.getSession();
		session.setAttribute("res", res);
		response.sendRedirect("paginas/res.jsp");

	}

}


