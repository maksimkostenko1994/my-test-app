package maks.project.controllers;

import maks.project.dao.PersonDAO;
import maks.project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/getAll";
    }

    @GetMapping("/{id}")
    public String getOne(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/getOne";
    }

    @GetMapping("/create")
    public String newPerson(
            @ModelAttribute("person") Person person
    ) {
        return "people/createPerson";
    }

    @PostMapping()
    public String create(
            @ModelAttribute("person") Person person
    ) {
        personDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/editPerson";
    }

    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("person") Person person,
            @PathVariable("id") int id
    ) {
        personDAO.update(id, person);
        return "redirect:/people";
    }
}
