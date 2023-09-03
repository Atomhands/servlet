package com.niehao.pojo;

/**
 * ClassName: HeTu
 * Package: com.niehao.pojo
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/16 1:19
 * @Version 1.0
 */
public class HeTu {
    private int yang;
    private int yin;

    public HeTu() {
    }

    public HeTu(int yang, int yin) {
        this.yang = yang;
        this.yin = yin;
    }

    public int getYang() {
        return yang;
    }

    public void setYang(int yang) {
        this.yang = yang;
    }

    public int getYin() {
        return yin;
    }

    public void setYin(int yin) {
        this.yin = yin;
    }
    @Override
    public String toString() {
        return "HeTu{" +
                "yang=" + yang +
                ", yin=" + yin +
                '}';
    }
}
