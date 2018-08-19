package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Person;
import play.mvc.*;
import play.libs.Json;
import io.ebean.*;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result addPerson(){
        JsonNode json = request().body().asJson();
        Person person = Json.fromJson(json, Person.class);
        if(person.toString().equals("")){
            return badRequest("Missing Parameter");
        }
        person.save();
        return ok();

    }

    public Result listPerson(){
        List<Person> people = Person.find.all();
        return ok(Json.toJson(people));
    }

    public Result listPersonById(Integer id){
        Person person = Person.find.byId(id);
        if(person == null){
            return notFound("User not found");
        }
        return ok(Json.toJson(person));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updatePerson(Integer id){
        Person person = Person.find.byId(id);
        if(person == null)
                return notFound("User not found");
        Person personToBe = Json.fromJson(request().body().asJson(), Person.class);
        person.setName(personToBe.name);
        person.update();
        return ok("Person Updated");
    }

    public Result deletePerson(Integer id){
        Person person = Person.find.byId(id);
        if(person == null)
            return notFound("User not found");

        person.delete();
        return ok("Deletion Occurred");
    }

}
