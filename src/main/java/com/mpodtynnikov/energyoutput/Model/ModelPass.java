package com.mpodtynnikov.energyoutput.Model;

public class ModelPass {
    private final Dao Generator;
    public ModelPass()
    {
        switch (MainBase.connectionCheck())
        {
            case 1:Generator=new MainBase();
                break;
            default:Generator=new LocalBase();
                break;
        }
        Generator.getDBConnection();
    }

    public Dao getGenerator() {
        return Generator;
    }
}
