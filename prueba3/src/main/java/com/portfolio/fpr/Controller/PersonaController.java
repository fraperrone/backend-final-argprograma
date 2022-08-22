
package com.portfolio.fpr.Controller;

import com.portfolio.fpr.Entity.Persona;
import com.portfolio.fpr.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://argp-fpr.web.app/")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam("nombre") String nuevoNombre,
                               @RequestParam("apellido") String nuevoApellido,
                               @RequestParam("img") String nuevoImg,
                               @RequestParam("sobreMi") String nuevoSobreMi,
                               @RequestParam("banner") String nuevoBanner
                               ){
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        persona.setSobreMi(nuevoSobreMi);
        persona.setBanner(nuevoBanner);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
        
    @GetMapping("personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaService.findPersona((long)1);
    }
   
    // Modifcar sobre mi:
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/sobremi/{id}")
    public Persona editSobreMi(@PathVariable Long id,
                               @RequestParam("sobreMi") String nuevoSobreMi){
        Persona persona = ipersonaService.findPersona(id);
        
        
        persona.setSobreMi(nuevoSobreMi);
        
        ipersonaService.savePersona(persona);
        return persona;
        
        
        
        
        
    }
    //modificar banner
        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/personas/editar/banner/{id}")
        public Persona editBanner(@PathVariable Long id,
                                  @RequestParam("banner") String nuevoBanner  
                                    ){
            Persona persona= ipersonaService.findPersona(id);
            
            persona.setBanner(nuevoBanner);
            ipersonaService.savePersona(persona);
            return persona;
            
        }
        
    //modificar foto perfil
        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/personas/editar/img/{id}")
        public Persona editarImg(@PathVariable Long id,
                                 @RequestParam("img") String nuevoImg
                                ){
            Persona persona= ipersonaService.findPersona(id);
            
            persona.setImg(nuevoImg);
            ipersonaService.savePersona(persona);
            return persona;
            
        }
}

