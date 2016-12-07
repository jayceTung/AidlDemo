package com.asuper.aidldemo.designmode.build;

/**
 * Created by Super on 2016/12/7.
 */

public class Director {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static class Build {
        private String name;
        private int age;

        public Build(String name) {
            this.name = name;
        }

        public Build setName(String name) {
            this.name = name;
            return this;
        }

        public Build setAge(int age) {
            this.age = age;
            return this;
        }

        public Director build() {
            return new Director(this);
        }
    }

    private Director(Build build) {
        this.name = build.name;
        this.age = build.age;
    }
}
