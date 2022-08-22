
package com.portfolio.fpr.Controller;

import com.portfolio.fpr.Dto.dtoProyecto;
import com.portfolio.fpr.Entity.Proyecto;
import com.portfolio.fpr.Security.Controller.Mensaje;
import com.portfolio.fpr.Service.SProyecto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proy")
@CrossOrigin(origins = "https://argp-fpr.web.app/")
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping ("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list= sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("detail/{id}")
    public ResponseEntity<Proyecto> getById (@PathVariable("id") int id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto= sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("producto elminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody dtoProyecto dtoproy){
        if(StringUtils.isBlank(dtoproy.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es oblig"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existsByNombreE(dtoproy.getNombreE()))
            return new ResponseEntity(new Mensaje("Ese proy existe"),HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto= new Proyecto(dtoproy.getNombreE(),dtoproy.getDescripcionE(),dtoproy.getImagenE());
        sProyecto.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody dtoProyecto dtoproy){
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existsByNombreE(dtoproy.getNombreE()) && sProyecto.getByNombreE(dtoproy.getNombreE()).get().getId() !=id )
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoproy.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto= sProyecto.getOne(id).get();
        proyecto.setNombreE(dtoproy.getNombreE());
        proyecto.setDescripcionE(dtoproy.getDescripcionE());
        proyecto.setImagenE(dtoproy.getImagenE());
        
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto Actualizado"), HttpStatus.OK);
    }
}
