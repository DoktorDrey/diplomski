package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.ProgramOceneDao;
import com.diplomski.katedra.db.model.Program;
import com.diplomski.katedra.db.model.ProgramOcene;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrija Ilic on 9/3/2015.
 */
@Repository("ProgramOceneDao")
public class ProgramOceneDaoImpl extends HibernateDao<ProgramOcene, Integer> implements ProgramOceneDao {
    @Override
    public void addOrUpdate(ProgramOcene programOcene) {
        Query query = currentSession().createQuery("from ProgramOcene PO where PO.programId = "+programOcene.getProgramId().getId()+"");
        List result = query.list();
        if(result.isEmpty()) {
            dodaj(programOcene);
        } else {
            ProgramOcene programOcene1 = (ProgramOcene) result.get(0);
            programOcene1.setDeset(programOcene.getDeset());
            programOcene1.setDevet(programOcene.getDevet());
            programOcene1.setOsam(programOcene.getOsam());
            programOcene1.setSedam(programOcene.getSedam());
            programOcene1.setSest(programOcene.getSest());
            sacuvaj(programOcene1);
        }
    }

    @Override
    public ProgramOcene getOcene(Program program) {
        Query query = currentSession().createQuery("from ProgramOcene PO where PO.programId = "+program.getId());
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (ProgramOcene) result.get(0);
    }
}
