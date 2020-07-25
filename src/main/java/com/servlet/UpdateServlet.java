package com.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;

public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noteid=Integer.parseInt(request.getParameter("note_id"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		Session se=FactoryProvider.getFactory().openSession();
		Transaction tx=se.beginTransaction();
		
		Note note=se.get(Note.class, noteid);
		note.setTitle(title);
		note.setContent(content);
		note.setAddedDate(new Date());
		tx.commit();
		se.close();
		response.sendRedirect("show_notes.jsp");
		
	}

}
