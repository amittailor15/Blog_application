package com.techBlog.servlets;

import com.techBlog.dao.CommentDao;
import com.techBlog.entities.Message;
import com.techBlog.entities.Comment;
import com.techBlog.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;


public class CommentServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            int pid=Integer.parseInt(request.getParameter("pid"));
            int uid=Integer.parseInt(request.getParameter("uid"));
            String cmmt=request.getParameter("cmmt");
            
            Comment ru = new Comment(pid,uid,cmmt);
            CommentDao dao = new CommentDao(ConnectionProvider.getConnection());
            boolean f= dao.saveCommentOnPost(ru);
            if(f){
            out.print("done");
            }
            
//            Message msg = new Message("Registration successful! Login here ", "success", "success");
//                HttpSession session = request.getSession();
//                session.setAttribute("msg", msg);
//
//                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//                rd.forward(request, response);
                
            out.println("</body>");
            out.println("</html>");

        } 
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
