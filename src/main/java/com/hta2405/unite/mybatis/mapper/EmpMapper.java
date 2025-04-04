package com.hta2405.unite.mybatis.mapper;

import com.hta2405.unite.domain.Birthday;
import com.hta2405.unite.domain.Dept;
import com.hta2405.unite.domain.Emp;
import com.hta2405.unite.dto.EmpListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    Emp getEmpById(String empId);

    int updateEmp(Emp emp);

    String getImgOriginal(String fileUUID);

    List<EmpListDTO> getHiredEmpListByDeptId(Long deptId);

    List<EmpListDTO> getHiredEmpListByName(String ename);

    int insertEmp(Emp emp);

    int resignEmp(String empId);

    List<EmpListDTO> getHiredEmpListDTO(List<Dept> list);

    List<Map<String, Object>> getIdToENameMap();

    void updateVacationCount();

    int changePassword(String empId, String encodedPassword);

    List<EmpListDTO> getAllEmpListDTO();

    String findDeptManager(String empId);

    String findParentDeptManager(String empId);

    List<Birthday> findTodayBirthdays(int solMonth, int solDay, int lunMonth, int lunDay);
}
