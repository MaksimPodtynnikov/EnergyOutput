package com.mpodtynnikov.energyoutput;

import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;

public class People extends TreeItem<String> implements Energy {
    int count;
    private String title;
    Age age;
    Sex sex;
    private IMB imb;
    private double mass;

    public People(Sex sex, Age age, IMB imb,String title, int count)
    {
        super("",new Text(title));
        this.sex = sex;
        this.age = age;
        this.title = title;
        this.imb = imb;
        this.mass = age.getMidMass(sex,imb);
        this.count = count;
    }

    public IMB getImb() {
        return imb;
    }

    public void setImb(IMB imb) {
        this.imb = imb;
        this.mass = age.getMidMass(sex,imb);
    }

    /**
     * @return величина основного обмена в мДж
     */
    public double getVMT_MDj() {
        return age.getMultiplier(sex) * mass + age.getSummand(sex);
    }

    public void setTitle(String title) {
        this.title = title;
        ((Text)getGraphic()).setText(title);
    }

    /**
     * @param easyWork если True, то при легкой работе, иначе умственная деятельность
     * @return количество энергии
     */
    public double getWork(boolean easyWork)
    {
        double work = 1.4;
        if(easyWork) work = 1.6;
        return 1.163 * getVMT_MDj() * work * count;
    }
    public double getO2(boolean easyWork)
    {
        return 5.889 * getWork(easyWork);
    }

    @Override
    public String getTitle() {
        return title;
    }

    public double getCO2(boolean easyWork)
    {
        return 0.83 * getO2(easyWork);
    }
}
