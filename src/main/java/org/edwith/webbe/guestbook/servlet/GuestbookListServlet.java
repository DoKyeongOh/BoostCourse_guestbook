package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guestbooks")
public class GuestbookListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 코드를 작성하세요.
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	GuestbookDao guestbookDao = new GuestbookDao();
		List<Guestbook> guestList = guestbookDao.getGuestbooks();
    	
		out.println("<html>");
		out.println("<head><title>form</title></head>");
		out.println("<body>");
		
		for (Guestbook gg : guestList) {
    		out.print("id : " + gg.getId() + "<br>");
    		out.print("name : " + gg.getName() + "<br>");
    		out.print(gg.getContent() + "<br>");
    		out.print("regdate : " + gg.getRegdate() + "<br><hr>");
    	}
		
		out.println("<form method='post' action='/guestbooks/write'>");
		out.println("이름 : <input type='text' name='name'><br>");
		out.println("내용 : <textarea name='content'cols='50' rows='5'></textarea><br>");
		out.println("<input type='submit' value='ok'><br>");                                                 
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
            
    	out.close();
    }

}
 