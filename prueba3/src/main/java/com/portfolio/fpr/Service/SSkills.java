
package com.portfolio.fpr.Service;

import com.portfolio.fpr.Entity.Skills;
import com.portfolio.fpr.Interface.ISkills;
import com.portfolio.fpr.Repository.RSkills;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SSkills implements ISkills {
    @Autowired RSkills rSkills;
    
    @Override
    public void saveSkills(Skills skills) {
        rSkills.save(skills);
    }

    @Override
    public Skills findSkills(Long id) {
        Skills skills= rSkills.findById(id).orElse(null);
        return skills;
    }

    @Override
    public List<Skills> getSkills() {
        List<Skills> skills= rSkills.findAll();
        return skills;
    }
    
}
