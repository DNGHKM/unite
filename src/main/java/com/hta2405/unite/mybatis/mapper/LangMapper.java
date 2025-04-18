package com.hta2405.unite.mybatis.mapper;

import com.hta2405.unite.domain.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LangMapper {
    List<Lang> getLangByEmpId(String empId);

    void deleteAllLangByEmpId(String empId);

    void insertLang(@Param("list") List<Lang> langList);
}
