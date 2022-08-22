
package com.portfolio.fpr.Repository;

import com.portfolio.fpr.Entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RSkills extends JpaRepository<Skills,Long> {
    
}
