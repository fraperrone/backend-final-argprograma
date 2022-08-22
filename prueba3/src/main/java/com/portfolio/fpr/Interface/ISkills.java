
package com.portfolio.fpr.Interface;

import com.portfolio.fpr.Entity.Skills;
import java.util.List;


public interface ISkills {
    
    //guardar skills
    public void saveSkills(Skills skills);
    
    /// buscar skills
    public Skills findSkills(Long id);
    
    //traer skills
    public List<Skills> getSkills();
}
