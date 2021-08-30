/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.supportweb.entities;

public class Student {
    
    private String name;
    private int student_id;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int student_id) {
        this.name = name;
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
