package it.carcheck.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.exception.ActionNotFoundException;
import it.carcheck.control.interfaces.IAction;

/**
 * Servlet implementation class RequestHandler
 */
@WebServlet("/RequestHandler/*")
public class RequestHandler extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			IAction action = ActionFactory.getAction(request);
			String view = action.execute(request, response);
			String pathInfo = request.getPathInfo().substring(1);
			
			if(!view.equals(pathInfo))
				response.sendRedirect(view);
		}
		catch (ActionNotFoundException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		catch (ActionException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	private static final long serialVersionUID = 1L;
}
