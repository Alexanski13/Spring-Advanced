package bg.softuni.errors.web;

import bg.softuni.errors.model.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserManualController {

    @GetMapping("/manuals/{id}")
    public String getCategoryById(@PathVariable("id") Long id) {
        throw new ObjectNotFoundException(id, "User manual");
    }

}
