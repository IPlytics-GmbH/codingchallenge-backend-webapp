package de.iplytics.codingchallenge_backend_webapp.patents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patents")
public class PatentController {

    @Autowired
    PatentService patentService;

    @GetMapping("/{publicationNumber}")
    @ResponseBody
    public Patent getPatent(@PathVariable("publicationNumber") String id){
        return patentService.getSinglePatent(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Patent> searchPatents(@RequestParam("title") String title){
        return patentService.searchPatentByTitle(title);
    }
}
