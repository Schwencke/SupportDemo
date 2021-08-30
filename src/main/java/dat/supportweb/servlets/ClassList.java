package dat.supportweb.servlets;

import dat.supportweb.DatabaseException;
import dat.supportweb.Persistance.DBmapper;
import dat.supportweb.entities.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Application;

@WebServlet(name = "ClassList", urlPatterns = {"/classlist"})
public class ClassList extends HttpServlet {

    private List<Student> studentList;

    
    public void init() throws ServletException
    {
        if (studentList == null){
        studentList = new ArrayList<>();}
        try {
            studentList = DBmapper.getAllStudents();

        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            session.setAttribute("studentList", studentList);
            request.getRequestDispatcher("/classlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("/waitinglist.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
