package models;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import javax.persistence.*;
import javax.validation.Constraint;
import java.util.*;

@Entity
public class Person extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    public static Finder<Integer, Person> find = new Finder<Integer, Person>(Person.class);

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
