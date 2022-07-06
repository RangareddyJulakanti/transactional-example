package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;
@Service
public class TestServiceImpl implements TestService{
    @Autowired
    private StudentRepo studentRepo;
    public void m1() {
        int result = getResult();
        EStudent eStudent=new EStudent(result,"Ranga");
        studentRepo.save(eStudent);
    }

    private int getResult() {
        Random r = new Random();
        int low = 10;
        int high = 100;
        int result = r.nextInt(high-low) + low;
        return result;
    }

   // @Transactional
    public void m2(EStudent eStudent) {
      Optional<EStudent> es=studentRepo.findById(eStudent.getId());
      es.ifPresent(e->m3(e));
    }

    @Transactional
    public void m3(EStudent s) {
        s.setName(s.getName()+getResult());
    }
}
