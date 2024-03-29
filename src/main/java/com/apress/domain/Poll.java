package com.apress.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;



/*/
This stores the poll question
"OnetoMany indicates that a Poll instance can contain zero or more Option instances
"Cascade" shows that any database operations such as remove or merge etc. needs to increase to all related Option
instances.


 */
@Entity
public class Poll {

    @Id
    @GeneratedValue
    @Column(name = "POLL_ID")
    private Long id;
    @Column(name = "QUESTION")
    private String question;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    @OrderBy
    @Size(min = 2, max = 6)
    private Set<Option> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
