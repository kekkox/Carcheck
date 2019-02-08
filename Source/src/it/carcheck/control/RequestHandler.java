package it.carcheck.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.interfaces.IAction;

/**
 * Servlet implementation class RequestHandler
 */
@WebServlet("/RequestHandler/*")
public class RequestHandler extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAction action = ActionFactory.getAction(request);
		String view = action.execute(request, response);
		String pathInfo = request.getPathInfo().substring(1);
		
		if (view.equals(pathInfo)) {
			request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
		} 
		else {
			response.sendRedirect(view);
		}
	}
	
	private static final long serialVersionUID = 1L;
}
