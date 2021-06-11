package pro.dengyi.myhome.servicefrontend.service;


import pro.dengyi.myhome.myhomemodel.business.frontend.Family;

import java.util.List;

/**
 * @author BLab
 */
public interface FamilyService {
    /**
     * 新增家庭
     *
     * @param family
     */
    void addOrUpdate(Family family);

    /**
     * 查询用户的所有家庭列表
     *
     * @return
     */
    List<Family> familyList();

    /**
     * 同意加入家庭
     *
     * @param messageId
     */
    void confirmJoin(String messageId);

    /**
     * 查询用户是否是家庭的户主
     *
     * @param familyId 家庭ID
     * @return
     */
    Boolean checkIsHolder(String familyId);
}
