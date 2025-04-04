package com.hta2405.unite.mybatis.mapper;

import com.hta2405.unite.domain.*;
import com.hta2405.unite.dto.DocListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DocMapper {
    void insertGeneralDoc(Doc doc);

    void insertTripDoc(Long docId, DocTrip docTrip);

    void insertBuyDoc(DocBuy docBuy);

    void insertBuyItem(Long docBuyId, List<BuyItem> items);

    void insertVacationDoc(Long docId, DocVacation docVacation);

    void updateGeneralDoc(Doc doc);

    void updateTripDoc(DocTrip docTrip);

    void updateVacationDoc(DocVacation docVacation);

    void insertSign(List<Sign> list);

    void deleteSign(Long docId);

    List<DocListDTO> getInProgressDocsByEmpId(String empId);

    Doc getDocById(Long docId);

    List<Sign> getSignListByDocId(Long docId);

    List<BuyItem> getBuyItemListByDocId(Long docId);

    DocTrip getDocTripByDocId(Long docId);

    DocVacation getDocVacationByDocId(Long docId);

    List<DocListDTO> getWaitingDocsByEmpId(String empId);

    String getNowSigner(Long docId);

    int signDoc(Long docId, String empId);

    boolean isDocSignedByEmpId(Long docId, String empId);

    int revokeDoc(Long docId, String empId);

    int deleteDoc(Long docId);

    boolean checkSignFinished(Long docId);

    int setSignFinished(Long docId);

    DocBuy getDocBuyByDocId(Long docId);

    void deleteBuyItem(Long docBuyId);

    List<DocListDTO> getDeptDocListByEmpId(String empId);

    List<DocListDTO> getSignedDocListByEmpId(String empId);
}
