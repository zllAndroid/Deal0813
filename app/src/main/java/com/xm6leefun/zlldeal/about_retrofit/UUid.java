package com.xm6leefun.zlldeal.about_retrofit;

/**
 * 创建人：zll .
 * 创建时间：2018/3/29 0029.
 * 作用：
 */

public class UUid<T> {
    /**
     * info : 请核对参数！
     * success : 0
     */

    private String info;
    private String success;
    private T user;

    @Override
    public String toString() {
        return "ResponseParent{" +
                "info='" + info + '\'' +
                ", success='" + success + '\'' +
                ", user=" + user +
                '}';
    }

    public UUid(String info, String success, T user) {
        this.info = info;
        this.success = success;
        this.user = user;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
