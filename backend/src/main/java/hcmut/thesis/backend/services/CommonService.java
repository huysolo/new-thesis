package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.models.Specialize;
import hcmut.thesis.backend.modelview.ProfInfo;

import java.util.List;

public interface CommonService {
    List<Semester> getListSemester();
    List<Integer> getAllSemester();
    List<ProfInfo> getListProf();
    String getFullName(String fName, java.lang.String lName);
    Integer getCurrentApplySem();
    Integer getCurrentSem();
    Semester getSemOpen();
    List<Specialize> getAllByIdFaculty(Integer idFal);
    String getSpecByID(Integer idSpec);
}
