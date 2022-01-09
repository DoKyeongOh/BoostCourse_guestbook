package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.*;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 코드를 작성하세요.
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	GuestbookDao guestbookDao = new GuestbookDao();
    	
    	String name = request.getParameter("name");
    	String content = request.getParameter("content");
    	
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    	guestbookDao.addGuestbook(new Guestbook((long) 1, name, content, LocalDate.now().toString()));
    	
    	out.print("<meta http-equiv=\"refresh\" content=\"5;url=http://localhost:8080/guestbooks\">");
    	
    	out.close();
    }

}
