package pl.edu.anstar.reservation.model;

import javax.persistence.*;

@Entity
@Table(name = "App_User", schema = "bpmn_projekt")
public class User {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "app_user_seq")
    @SequenceGenerator(
        name = "app_user_seq",
        schema = "bpmn_projekt",
        initialValue = 6)
    private Long user_id;
    private String first_name;
    private String last_name;
    private String company_name;

    // Getters and setters

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
