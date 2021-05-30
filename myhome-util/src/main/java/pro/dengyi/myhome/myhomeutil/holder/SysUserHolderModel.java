package pro.dengyi.myhome.myhomeutil.holder;

/**
 * @author DengYi
 * @version v1.0
 */
public class SysUserHolderModel {
    private String id;
    private Boolean isSuperAdmin;
    private Boolean isDeveloper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public Boolean getIsDeveloper() {
        return isDeveloper;
    }

    public void setIsDeveloper(Boolean developer) {
        isDeveloper = developer;
    }
}
