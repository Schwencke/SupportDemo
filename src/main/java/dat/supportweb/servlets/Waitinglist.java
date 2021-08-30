
package dat.supportweb.servlets;

import dat.supportweb.DatabaseException;
import dat.supportweb.Persistance.DBmapper;
import dat.supportweb.entities.Student;
import dat.supportweb.entities.Ticket;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "waitinglist", urlPatterns = {"/waitinglist"})
public class Waitinglist extends HttpServlet {

    private List<Ticket> ticketList;
    private List<Student> studentList;
    HashMap<Integer, String> studentIds = new HashMap<>();
    
    public void init() throws ServletException
    {
        ServletContext application = getServletContext();
        ticketList = new ArrayList<>();
        studentList = new ArrayList<>();
        try {
            ticketList = DBmapper.getAllTickets();
            studentList = DBmapper.getAllStudents();
            studentIds = (HashMap<Integer, String>) studentList.stream().collect(Collectors.toMap(Student::getStudent_id, Student::getName));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        application.setAttribute("listofidsandnames", studentIds);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("ticketList", ticketList);
        session.setAttribute("studentList", studentList);

        request.getRequestDispatcher("/waitinglist.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String command = request.getParameter("command");
            switch (command){
                case "add":
                    int studentId = Integer.parseInt(request.getParameter("requestid"));
                    try {
                        DBmapper.setTicket(new Ticket(studentId));
                        ticketList = DBmapper.getAllTickets();
                    } catch (SQLException | DatabaseException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    //ticketList.add(new Ticket(new Student(name)));
                    //TODO: getTheID of the student from the database to compare against.
//                    DBmapper.setTicket(ticketList);
//                    ticketList = DBmapper.getAllTickets();
                    break;
                case "remove":
                    if (ticketList.size() > 0)
                    {
                        try {
                            int ticketId = ticketList.get(0).getTicketId();
                            DBmapper.removeTicket(ticketId);
                            ticketList.remove(0);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            HttpSession session = request.getSession();
            session.setAttribute("ticketList", ticketList);
            request.getRequestDispatcher("/waitinglist.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
