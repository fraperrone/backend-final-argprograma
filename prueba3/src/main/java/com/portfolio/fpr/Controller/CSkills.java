

package com.portfolio.fpr.Controller;

import com.portfolio.fpr.Entity.Skills;
import com.portfolio.fpr.Interface.ISkills;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins= "https://argp-fpr.web.app/")
public class CSkills {
    @Autowired ISkills iSkills;
    
    @GetMapping("/skills/traer")
    public List<Skills> getSkills(){
        return iSkills.getSkills();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skills/editar/id={id}")
    public Skills editSkills(@PathVariable Long id,
                             @RequestBody  Skills nuevosSkills 
                             ){
        Skills skills= iSkills.findSkills(id);
        skills.setHtm(nuevosSkills.getHtm());
        skills.setCs(nuevosSkills.getCs());
        skills.setJs(nuevosSkills.getJs());
        skills.setGi(nuevosSkills.getGi());
        skills.setEn(nuevosSkills.getEn());
        
        iSkills.saveSkills(skills);
        return skills;
    }
    
    @GetMapping("/skills/traer/{id}")
    public Skills findSkills(@PathVariable Long id){
        return iSkills.findSkills(id);
    } 
}
