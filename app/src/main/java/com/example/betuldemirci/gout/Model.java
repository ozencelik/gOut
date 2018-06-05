package com.example.betuldemirci.gout;

public class Model {

    public static final int STACK_TYPE=0;
    public static final int RUNNING_TYPE=1;
    public static final int WATER_TYPE=2;
    public static final int WEIGHT_TYPE=3;

    public int type;
    public int dailyStep, goalStep, calori; // Stack Type
    public int typeNumber;public String typeName;// 27 mins Running Type
    public int dailyWater, goalWater;public String waterType; // 5 bottles Water Type
    public int imageWeight; public double weight; // 80 kg Weight Type


    public Model(int type, int dailyStep, int goalStep, int calori)// Stack Type
    {
        this.type = type;
        this.dailyStep = dailyStep;
        this.goalStep = goalStep;
        this.calori = calori;
    }

    public Model(int type, int typeNumber, String typeName)// Running Type
    {
        this.type = type;
        this.typeNumber = typeNumber;
        this.typeName = typeName;
    }

    public Model(int type, int dailyWater, int goalWater, String waterType)// Water Type
        {
        this.type = type;
        this.dailyWater = dailyWater;
        this.goalWater = goalWater;
        this.waterType = waterType;
    }

    public Model(int type, int imageWeight, double weight)// Weight Type
    {
        this.type = type;
        this.imageWeight = imageWeight;
        this.weight = weight;
    }

}
