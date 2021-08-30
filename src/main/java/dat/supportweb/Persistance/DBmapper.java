package dat.supportweb.Persistance;

import dat.supportweb.DatabaseException;
import dat.supportweb.entities.Student;
import dat.supportweb.entities.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBmapper {

    public static List<Ticket> getAllTickets() throws DatabaseException {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection con = Connector.connection()){
            String SQL = "SELECT * FROM ticket";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idStudent = rs.getInt("id_student");
                int id = rs.getInt("id_ticket");
                ticketList.add(new Ticket(idStudent, id));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseException(ex.getMessage());
        }
        return ticketList;
    }

    public static List<Student> getAllStudents() throws DatabaseException {
        List<Student> studentList = new ArrayList<>();

        try(Connection con = Connector.connection()) {
            String SQL = "SELECT * FROM student";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                studentList.add(new Student(name, id));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseException(ex.getMessage());
        }
        return studentList;
    }

    public static int setTicket(Ticket ticket) throws SQLException, ClassNotFoundException {

        try (Connection con = Connector.connection()) {
            String sql = "INSERT INTO `ticket` (`id_student`) VALUES (?)";

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, ticket.getStudentId());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int ticketId = ids.getInt(1);
                return ticketId;
            }
        }

    }

    public static void removeTicket(int idTicket) throws SQLException, ClassNotFoundException {

        try (Connection con = Connector.connection()) {
            String sql = "DELETE FROM `ticket` WHERE `id_ticket` =?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idTicket);
                ps.executeUpdate();
            }
        }
    }
}
