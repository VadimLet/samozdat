package letunov.samozdat.domain;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the text")
    @Length(max = 8192, message = "Message too long (more than 8kB)")
    @Column(length = 8192)
    private String text;

    @NotBlank(message = "Please fill the description")
    @Length(max = 255, message = "Message too long (more than 255)")
    @Column(length = 255)
    private String description;

    @NotBlank(message = "Please fill the description")
    @Length(max = 50, message = "Message too long (more than 50)")
    @Column(length = 255)
    private String title;

    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Book(String text, String description,String title) {
        this.text = text;
        this.description = description;
        this.title = title;
    }

    public Book() {
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText() {

        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
