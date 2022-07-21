package com.mms.thp.repository;

import com.mms.thp.model.Box;
import com.mms.thp.model.Medicine;
import com.mms.thp.model.MedicineBoxes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineBoxRepository extends JpaRepository<MedicineBoxes, Long> {
    MedicineBoxes findMedicineBoxesByMedicineAndBox(Medicine medicine, Box box);
    List<MedicineBoxes> findByBox(Box box, Pageable pageable);
    List<MedicineBoxes> findByBox(Box box);
}
