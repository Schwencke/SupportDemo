/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.supportweb.entities;

public class Ticket {
    
    private int studentId;
    private int ticketId;

    public Ticket() {
    }

    public Ticket(int student) {
        this.studentId = student;
    }

    public Ticket(int student, int ticketId) {
        this.studentId = student;
        this.ticketId = ticketId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
