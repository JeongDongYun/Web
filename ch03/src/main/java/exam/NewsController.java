package exam;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/news/*")
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsDAO newsDAO;

	public NewsController() {
		newsDAO = new NewsDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println(action);
		if (action == null || action.equals("/list.do")) {
			List<NewsVO> newsList = newsDAO.listNews();
			request.setAttribute("newsList", newsList);
			nextPage = "/list.jsp";
		} else if (action.equals("/read.do")) {
			NewsVO news = new NewsDAO().ReadNews(Integer.parseInt(request.getParameter("id")), 1);
			request.setAttribute("news", news);
			nextPage = "/read.jsp";
		} else if (action.equals("/update_clk.do")) {
			NewsVO news = new NewsDAO().ReadNews(Integer.parseInt(request.getParameter("id")), 0);
			request.setAttribute("news", news);
			nextPage = "/update.jsp";

		} else if (action.equals("/update.do")) {
			NewsVO newsVO = new NewsVO();
			newsVO.setId(Integer.parseInt(request.getParameter("id")));
			newsVO.setTitle(request.getParameter("title"));
			newsVO.setBody(request.getParameter("body"));
			newsVO.setWriter(request.getParameter("writer"));
			newsDAO.UpdateNews(newsVO);
			
			nextPage = "/news";
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}